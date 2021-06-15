package com.example.onlineshopping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSreceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        try{
            if(bundle!=null)
            {
                Object[] pdusObj=(Object[])bundle.get("pdus");
                for(int i=0;i<pdusObj.length;i++)
                {
                    SmsMessage currentMessage=SmsMessage.createFromPdu((byte[])pdusObj[i]);
                    String number=currentMessage.getOriginatingAddress();
                    String message=currentMessage.getDisplayMessageBody();

                    String text="Sender Number: "+number+" Message: "+message;
                    Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(Exception e)
        {

        }
    }
}

