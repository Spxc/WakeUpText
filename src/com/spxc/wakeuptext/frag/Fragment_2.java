package com.spxc.wakeuptext.frag;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.spxc.wakeuptext.R;
import com.spxc.wakeuptext.sql.DatabaseHandler;
import com.spxc.wakeuptext.sql.WhiteList;

public class Fragment_2 extends SherlockListFragment{
	
	private ArrayList<String> results = new ArrayList<String>();
    private String tableName = DatabaseHandler.TABLE_CONTACTS;
    private SQLiteDatabase newDB;
    private static final int ADD = 1;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_2, null);       
        return v;       
    }
    
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       
        openAndQueryDatabase();
        displayResultList();
        
        
        
    }
	
    private void openAndQueryDatabase() {
        try {
            DatabaseHandler dbHelper = new DatabaseHandler(getActivity().getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT name, phone_number FROM " +
                    tableName, null);
 
            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        String name = c.getString(c.getColumnIndex("name"));
                        int number = c.getInt(c.getColumnIndex("phone_number"));
                        results.add(name + "\n" + number);
                    }while (c.moveToNext());
                } 
            }           
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            if (newDB != null) 
                newDB.close();
        }
 
    }
    
	private void displayResultList() {         
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, results)); 
        
        getListView().setTextFilterEnabled(true);
         
    }
	
	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem search = menu.add(0, ADD, 0, "Refresh");
        search.setIcon(android.R.drawable.ic_menu_add);
        search.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case ADD:
	        	DatabaseHandler db = new DatabaseHandler(getActivity());
	            //db.exists("454827640000");
	        	db.exists("4548276400");
	        	//addNumber();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void addNumber(){
		LayoutInflater factory = LayoutInflater.from(getActivity());

	    final View textEntryView = factory.inflate(R.layout.text_entry, null);

	    final EditText name = (EditText) textEntryView.findViewById(R.id.editText1);
	    final EditText phone = (EditText) textEntryView.findViewById(R.id.editText2);
	    
	    name.setHint("Name");
	    phone.setHint("Phone Number");
	    
	    final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
	    alert.setTitle(
	      "Contact information").setView(
	      textEntryView).setPositiveButton("Save",
	      new DialogInterface.OnClickListener() {
	       public void onClick(DialogInterface dialog,
	         int whichButton) {
	    	   DatabaseHandler db = new DatabaseHandler(getActivity());
	    	   db.addContact(new WhiteList(name.getText().toString(), phone.getText().toString())); 
	    	   openAndQueryDatabase();
	           displayResultList();
	       }
	      }).setNegativeButton("Cancel",
	      new DialogInterface.OnClickListener() {
	       public void onClick(DialogInterface dialog,
	         int whichButton) {
	         /*
	         * User clicked cancel so do some stuff
	         */
	       }
	      });
	    alert.show();
    }
}
