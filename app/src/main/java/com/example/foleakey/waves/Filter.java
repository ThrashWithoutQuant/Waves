package com.example.foleakey.waves;

import android.telephony.SmsMessage;

import com.example.foleakey.waves.MainActivity;

/**
 * Created by FoLeakey on 2015/10/12.
 */
public class Filter {
    public static String numberfilter(SmsMessage[] msg) {
        StringBuilder sbBuilder = new StringBuilder();
        for (SmsMessage smsMessage : msg) {
            sbBuilder.append("");
            sbBuilder.append(smsMessage.getDisplayOriginatingAddress());
        }
        MainActivity.value1.setText(sbBuilder);
        String str = sbBuilder.toString();
        String tmp = "+8618227690599";
        if (str.compareTo(tmp) == 0) {
            return "FoLeakey";
        } else {
            return "+8618227690599";
        }
    }

    public static void textfilter(SmsMessage[] msg) {
        StringBuilder sbBuilder = new StringBuilder();
        for (SmsMessage smsMessage : msg) {
            sbBuilder.append("");
            sbBuilder.append(smsMessage.getDisplayMessageBody());
        }
        MainActivity.value2.setText(sbBuilder);
    }
}


/*
       SmsMessage[] message = new SmsMessage[pdus.length]; //create a dynamic array to push the sms.
       for (int i = 0; i < pdus.length; i++) {
       message[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
       }
       tmsg=message;
       // format the sms and send.
       for (SmsMessage smsMessage : message) {
       sbBuilder.append("From：");
       sbBuilder.append(smsMessage.getDisplayOriginatingAddress());//get the original number.
       sbBuilder.append("\n");
       sbBuilder.append(smsMessage.getDisplayMessageBody());//get the text.
}
}*/
