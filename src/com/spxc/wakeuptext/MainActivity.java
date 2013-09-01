package com.spxc.wakeuptext;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.spxc.wakeuptext.adapter.TabsAdapter;
import com.spxc.wakeuptext.frag.Fragment_1;
import com.spxc.wakeuptext.frag.Fragment_2;
import com.spxc.wakeuptext.sql.DatabaseHandler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

public class MainActivity extends SherlockFragmentActivity {

	private BroadcastReceiver mIntentReceiver;
	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;
	String TAG = "Wake Up Text";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);
		
		final ActionBar abs = getSupportActionBar();
		abs.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mTabsAdapter = new TabsAdapter(this, mViewPager);
		mTabsAdapter.addTab(abs.newTab().setText("Received Texts"), Fragment_1.class, null);
		mTabsAdapter.addTab(abs.newTab().setText("Whitelist"), Fragment_2.class, null);	
		
		DatabaseHandler db = new DatabaseHandler(this);
        db.exists("45482764");
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
    }
	
	@Override
    protected void onResume() {
        super.onResume();
 
        IntentFilter intentFilter = new IntentFilter("SmsMessage.intent.MAIN");
        mIntentReceiver = new MessageReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("get_msg");
            Log.d(TAG, msg);
        }
    };
 
    this.registerReceiver(mIntentReceiver, intentFilter);
    }
 
    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(this.mIntentReceiver);
    }
    
    @Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Options");
		MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.context_menu, menu);
	}
    
    @Override
	public boolean onContextItemSelected(android.view.MenuItem item) {
    	switch(item.getItemId()){
    	case R.id.info:
    		
    		break;
    	case R.id.delete:
    		
    		break;
    	case R.id.share:
    		
    		break;
    	}
		return super.onContextItemSelected(item);
	}
}