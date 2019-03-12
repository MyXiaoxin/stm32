package com.example.administrator.myxi;

import android.os.Handler;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class GetLocantionThread extends Thread {

    private String url;
    private AMap map;
    private StringBuffer stringBuffer;
    private Handler handler;

    private int J =0;


    public GetLocantionThread(String url, AMap map, Handler handler){
        this.url=url;
        this.map=map;
        this.handler = handler;
    }


    @Override
    public void run() {
        while (true) {
            doTask();
            try {
                this.sleep(1000);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(stringBuffer);
                        String[] loc = stringBuffer.toString().split("-");
                        map.clear();
                        double a = Double.parseDouble(loc[0]);
                        double b = Double.parseDouble(loc[1]);
                        System.out.println(loc[0]);
                        System.out.println(loc[1]);
                        LatLng latLng = new LatLng(a, b);
                        if (J==0){
                            map.clear();
                            J=1;
                        }else{
                            Marker marker = map.addMarker(new MarkerOptions().position(latLng).title("垃圾箱位置").snippet("LocationMarker"));
                            J=0;
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    private void doTask() {
        URL httpURL = null;
        try {
            httpURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            stringBuffer = new StringBuffer();
            while ((str = bf.readLine()) != null) {
                stringBuffer.append(str);
            }
            System.out.println("Hello"+ stringBuffer.toString());
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
