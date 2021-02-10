package com.example.preksha.onlinelearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Checkout extends Activity {
    TextView tv1, tv2, tv3;
    private AutoCompleteTextView actv;
    String a = " ", b = " ", c = " ", d = " ";
    float cost = 4000;
    float aftdis;
    EditText ed;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        String[] bank = getResources().getStringArray(R.array.bank_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, bank);
        final AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        actv.setThreshold(0);
        actv.setAdapter(adapter);

        String readString = null;
        tv1 = (TextView) findViewById(R.id.tv3);
        tv2 = (TextView) findViewById(R.id.tv5);
        tv3 = (TextView) findViewById(R.id.tv7);
        button = (Button) findViewById(R.id.button1);
        ed = (EditText) findViewById(R.id.editText1);

        try {

            FileInputStream fr = openFileInput("data.txt");
            InputStreamReader isr = new InputStreamReader(fr);
            char[] ib = new char[100];
            int char_read;
            while ((char_read = isr.read(ib)) > 0) {
                readString = String.copyValueOf(ib, 0, char_read);
                ib = new char[100];
            }

            String arr[] = readString.split(",");
            int i = arr.length;
            if (i == 1) {
                a = arr[0];

                aftdis = cost - 400;
                aftdis = aftdis * i;
                cost = cost * i;
            } else if (i == 2) {
                a = arr[0];
                b = arr[1];

                aftdis = cost - 400;
                aftdis = aftdis * i;
                cost = cost * i;
            } else if (i == 3) {
                a = arr[0];
                b = arr[1];
                c = arr[2];

                aftdis = cost - 400;
                aftdis = aftdis * i;
                cost = cost * i;
            } else if (i == 4) {
                a = arr[0];
                b = arr[1];
                c = arr[2];
                d = arr[3];
                aftdis = cost - 400;
                aftdis = aftdis * i;
                cost = cost * i;

            } else {
                Toast.makeText(getBaseContext(), "No Course Selected", Toast.LENGTH_LONG).show();
            }

            tv1.setText(a + " " + b + " " + c + " " + d);
            tv2.setText(String.valueOf(cost));
            tv3.setText(String.valueOf(aftdis));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str3 = ed.getText().toString();
                String str4 = actv.getText().toString();

                if (str3.equalsIgnoreCase("")) {
                    ed.setError("please enter the details");
                } else if (str4.equalsIgnoreCase("")) {
                    actv.setError("please enter the details");
                } else {
                    Intent i = new Intent(getBaseContext(), Conformation.class);
                    Bundle b = new Bundle();

                    String str = (String) tv1.getText();
                    String str1 = (String) tv3.getText();
                    b.putString("course", str);
                    b.putString("cost", str1);
                    i.putExtras(b);
                    Log.d("checkout", str);
                    startActivity(i);
                }


            }

        });

    }
}