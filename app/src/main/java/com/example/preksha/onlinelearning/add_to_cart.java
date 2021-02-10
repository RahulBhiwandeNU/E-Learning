package com.example.preksha.onlinelearning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class add_to_cart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        final ListView lv = (ListView) findViewById(R.id.LV);
        Button b1=(Button) findViewById(R.id.go);

        String [] stars = getResources().getStringArray(R.array.List);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_multiple_choice, stars);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);
        b1.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {SparseBooleanArray check = lv.getCheckedItemPositions();

                ArrayList<String> aa = new ArrayList<>();

                for (int i = 0; i < check.size(); i++)
                {
                    int pos = check.keyAt(i);
                    if (check.valueAt(i))
                    {
                        aa.add(adapter.getItem(pos));
                    }
                }
                int n=lv.getCheckedItemCount();
                String f = "data.txt";
                FileOutputStream fout = null;
                try {
                    fout = openFileOutput(f, Context.MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fout);

                    for (int k = 0; k < aa.size(); k++)
                    {
                        osw.write(aa.get(k) + ",");
                    }
                    osw.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                Intent i=new Intent(getBaseContext(),Checkout.class);
                startActivity(i);
            }
        });

    }
}
