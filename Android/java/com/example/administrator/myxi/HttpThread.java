package com.example.administrator.myxi;

import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * Created by Administrator on 2017/7/7.
 */

public class HttpThread extends Thread {

    String url;
    String name;
    String age;

    public HttpThread(String url,String name,String nam){
        this.url=url;
        this.name= name;
        this.age=nam;
    }
    private void doGet(){
        try {
            url = url +"?name="+name+"&age="+age;
            URL httpURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer stringBuffer = new StringBuffer();
            while((str=bf.readLine())!=null){
                stringBuffer.append(str);
            }
           System.out.println(stringBuffer.toString()+"Hello");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void run() {
        doGet();
    }
}
