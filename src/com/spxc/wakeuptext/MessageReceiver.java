package com.spxc.wakeuptext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;


public class MessageReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
             Bundle pudsBundle = intent.getExtras();
             Object[] pdus = (Object[]) pudsBundle.get("pdus");
             SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);    
                 if(messages.getMessageBody().contains("Hi")) {
                	 //Crouton.makeText(null, "SMS READ", Style.ALERT).show();
                	 Intent in = new Intent("SmsMessage.intent.MAIN").
                	         putExtra("get_msg", messages.getMessageBody());
                	 context.sendBroadcast(in);
                	 Log.d("Received", messages.getMessageBody());
                     abortBroadcast();
             }
    }
}