package com.lidiwo.princeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrinceView pv_aaa=findViewById(R.id.pv_aaa);

        pv_aaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("@@@","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            }
        });


    }



}
