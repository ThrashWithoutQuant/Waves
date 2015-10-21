package com.example.foleakey.waves;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;


/**
 * Created by LFJ on 2015/10/13.
 */
public class getName {

    /*
       * 根据电话号码取得联系人姓名
       */
    public static String getContactNameByPhoneNumber(Context context, String address) {
        String[] projection = {ContactsContract.PhoneLookup.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        // 将自己添加到 msPeers 中
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, // Which columns to return.
                ContactsContract.CommonDataKinds.Phone.NUMBER + " = '"
                        + address + "'", // WHERE clause.
                null, // WHERE clause value substitution
                null); // Sort order.

        if (cursor == null) {
            return address;//如果通讯录中没有这个人中
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            // 取得联系人名字
            int nameFieldColumnIndex = cursor
                    .getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            String name = cursor.getString(nameFieldColumnIndex);
            return name;
        }
        return null;
    }

    public static String getContacts(Context context) {
        StringBuilder sb = new StringBuilder();

        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String contactId = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor
                        .getString(cursor
                                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //第一条不用换行
                if (sb.length() == 0) {
                    sb.append(name);
                } else {
                    sb.append("\n" + name);
                }

                Cursor phones = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = " + contactId, null, null);
                while (phones.moveToNext()) {
                    String phoneNumber = phones
                            .getString(phones
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    // 添加Phone的信息
                    sb.append("\t").append(phoneNumber);

                }
                phones.close();

            } while (cursor.moveToNext());
        }
        cursor.close();
        return sb.toString();
    }
}
