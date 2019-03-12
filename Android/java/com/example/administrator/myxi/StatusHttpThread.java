package com.example.administrator.myxi;

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

public class StatusHttpThread extends Thread {

    private String url;
    private String flag;

    public StatusHttpThread(String url, String flag){
        this.url=url;
        this.flag= flag;
    }
    private void doGet(){
        try {
            url = url +"?flag="+flag;
            URL httpURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer stringBuffer = new StringBuffer();
            while((str=bf.readLine())!=null) {
                stringBuffer.append(str);
            }
            System.out.print(stringBuffer.toString());
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
