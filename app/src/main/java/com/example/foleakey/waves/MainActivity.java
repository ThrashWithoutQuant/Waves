package com.example.foleakey.waves;

/**
 * Created by FoLeakey on 2015/10/07.
 */

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends Activity {

    public static String DestinationAddress = null;
    public static TextView value1,value2;
    private EditText phoneNumberEditText;
    private Button button;
    private Switch switch1;
    SharedPreferences sp = null;
    public static boolean isInterceptor = false;
    private CheckBox ck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberEditText = (EditText) findViewById(R.id.PNeText);
        value1=(TextView)findViewById(R.id.testText1);
        value2=(TextView)findViewById(R.id.testText2);
        //button = (Button) findViewById(R.id.Btn);
        switch1 = (Switch) findViewById(R.id.switch1);
        ck = (CheckBox) findViewById(R.id.isInterceptor);
        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                isInterceptor = buttonView.isChecked();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DestinationAddress = phoneNumberEditText.getText().toString();
                    if ("".equals(DestinationAddress)) {
                        Toast.makeText(MainActivity.this, "Phone number cannot be empty！",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    sp = getSharedPreferences("phonenumber", MODE_PRIVATE);
                    Editor et = sp.edit();
                    //et.putString("number", DestinationAddress);
                    et.putBoolean("isInterceptor", isInterceptor);
                    et.commit();
                    Receiver.switcher = 1;
                    Toast.makeText(MainActivity.this, "Done！", Toast.LENGTH_LONG)
                            .show();
                } else {
                    Receiver.switcher = 0;
                }
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DestinationAddress = phoneNumberEditText.getText().toString();
//                if ("".equals(DestinationAddress)) {
//                    Toast.makeText(MainActivity.this, "Phone number cannot be empty！",
//                            Toast.LENGTH_LONG).show();
//                    return;
//                }
//                sp = getSharedPreferences("phonenumber", MODE_PRIVATE);
//                Editor et = sp.edit();
//                et.putString("number", DestinationAddress);
//                et.putBoolean("isInterceptor", isInterceptor);
//                et.commit();
//                Toast.makeText(MainActivity.this, "Done！", Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
