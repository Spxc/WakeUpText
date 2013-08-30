package com.spxc.wakeuptext.frag;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.internal.widget.IcsAdapterView.AdapterContextMenuInfo;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.spxc.wakeuptext.R;

public class Fragment_2<ContextMenu> extends SherlockFragment{
	
	ListView lv1;
	private String selectedName;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		setHasOptionsMenu(true);
		
		View view = inflater.inflate(R.layout.fragment_2, container, false);
		
		lv1 = (ListView) view.findViewById(R.id.listView1);
		
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
             "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2" };

        ArrayAdapter<String> listview = new ArrayAdapter<String>(getActivity(), 
                 android.R.layout.simple_list_item_1, 
                 values);

         lv1.setAdapter(listview);
         lv1.setOnItemClickListener(new OnItemClickListener() {
     	    public void onItemClick(AdapterView<?> parent, View view,
     	        int position, long id) { 
     	    	Log.d("Press", "Pressed listview item" + position);
     	    	registerForContextMenu(lv1);
     	    }
     	 });
         return view;
	}
	
	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem search = menu.add("Add");
        search.setIcon(android.R.drawable.ic_menu_add);
        search.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }
	
	public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
		getSherlockActivity().getSupportMenuInflater().inflate(R.menu.context_menu, menu);
    }
	
	 public boolean onContextItemSelected(MenuItem item) {

	        AdapterContextMenuInfo adapInfo = (AdapterContextMenuInfo) item
	                .getMenuInfo();
	        //selectedName = nameList[(int) adapInfo.id];

	        switch (item.getItemId()) {
	        case R.id.view:
	            
	            return true;
	        case R.id.save:
	            
	            return true;
	        case R.id.edit:
	            
	            return true;
	        case R.id.delete:
	            
	            return true;
	        }
	        return false;
	    }
}
