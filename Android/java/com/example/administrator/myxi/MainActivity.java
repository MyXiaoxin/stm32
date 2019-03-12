package com.example.administrator.myxi;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.button2;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText age;
    Button button_submit;
    Button button_show;
    Button button_start;
    Button button_stop;
    String url;
    String status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.canshu1);
        age = (EditText) findViewById(R.id.canshu2);



        button_submit = (Button) findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "http://101.200.55.171:8080/ceshi/MyServlet";
                new HttpThread(url, name.getText().toString(), age.getText().toString()).start();
                Toast.makeText(MainActivity.this, "已成功提交", Toast.LENGTH_LONG).show();
            }
        });

        button_show = (Button) findViewById(R.id.button_show);
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse("http://101.200.55.171:8080/ceshi/index.jsp");
//                intent.setData(content_url);
//                startActivity(intent);

                Intent intent = new Intent(MainActivity.this,BasicMapActivity.class);
                startActivity(intent);
            }
        });

        button_start = (Button) findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "start";
                url = "http://101.200.55.171:8080/ceshi/CtrlServlet";
                new StatusHttpThread(url, status).start();
                Toast.makeText(MainActivity.this, "已成功开启设备", Toast.LENGTH_SHORT).show();
            }
        });

        button_stop = (Button) findViewById(R.id.button_stop);
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "stop";
                url = "http://101.200.55.171:8080/ceshi/CtrlServlet";
                new StatusHttpThread(url, status).start();
                Toast.makeText(MainActivity.this, "已成功停止设备", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

