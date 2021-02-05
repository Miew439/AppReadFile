package com.example.appreadfile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyMenu extends Activity implements View.OnClickListener {
    Button buttonMonth, buttonXml, buttonExit;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.mymenu);
        init();

        buttonMonth.setOnClickListener(this);
        buttonXml.setOnClickListener(this);
        buttonExit.setOnClickListener(this);
    }

    private void init(){
        buttonMonth = (Button) findViewById(R.id.btnMonth);
        buttonXml = (Button) findViewById(R.id.btnXml);
        buttonExit = (Button) findViewById(R.id.btnExit);
    }

    @Override
    public  void onClick(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.btnMonth:
                intent = new Intent(getApplicationContext(), MyMonth.class);
                startActivity(intent);
                MyMenu.this.finish();
                break;

            case R.id.btnXml:
                intent = new Intent(getApplicationContext(), MyEmployee.class);
                startActivity(intent);
                MyMenu.this.finish();
                break;

            case R.id.btnExit:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                //set title
                alertDialogBuilder.setTitle("Confirm Exit Program");
                //set dialog message
                alertDialogBuilder
                        .setMessage("Click yes to exit!")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MyMenu.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
        }
    }

}
