package com.example.administrator.myxi;

import android.os.Handler;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/7/9.
 */

public class MapReader extends Thread {
    private String url;

    private Handler handler;
    WebView webView;

    public MapReader(String url,WebView webView,Handler handler){
        this.url=url;
        this.handler=handler;
        this.webView=webView;
    }

    @Override
    public void run() {

        try {
            URL httpURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) httpURL.openConnection();
            con.setReadTimeout(5000);
            con.setRequestMethod("GET");
            final StringBuffer sb = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String str;
            while((str=reader.readLine())!=null){
                sb.append(str);
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadData(sb.toString(),"text/html;charset=utf-8",null);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
