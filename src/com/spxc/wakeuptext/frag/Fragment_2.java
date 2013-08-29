package com.spxc.wakeuptext.frag;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.spxc.wakeuptext.R;

public class Fragment_2 extends SherlockFragment{
	
	ListView lv1;
    ProgressDialog ShowProgress;
    TextView tv1;
    Button button1;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_2, container, false);
		
		lv1 = (ListView) view.findViewById(R.id.listView1);
		
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
             "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2" };

        ArrayAdapter<String> listview = new ArrayAdapter<String>(getActivity(), 
                 android.R.layout.simple_list_item_1, 
                 values);

         lv1.setAdapter(listview);
         return view;
	}

}
