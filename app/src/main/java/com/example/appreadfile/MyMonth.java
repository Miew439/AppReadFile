package com.example.appreadfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;

public class MyMonth extends Activity implements View.OnClickListener{
    Button buttonMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month);
        init();
        try {
            final ListView listView1 = (ListView) findViewById(R.id.listView1);
            String [] myData = getResources().getStringArray(R.array.listmonth);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, myData);
            listView1.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        buttonMenu.setOnClickListener(this);
    }

    private void init(){
        buttonMenu = (Button) findViewById(R.id.mbtnBack);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.mbtnBack:
                Intent intent = new Intent(getApplicationContext(), MyMenu.class);
                startActivity(intent);
                MyMonth.this.finish();
                break;
        }
    }
}
