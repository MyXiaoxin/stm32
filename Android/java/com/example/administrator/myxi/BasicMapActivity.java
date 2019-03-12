package com.example.administrator.myxi;
 

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;


public class BasicMapActivity extends Activity implements OnClickListener {

	private MapView mapView;
	private AMap aMap;
	private Button basicmap;
	private Button rsmap;
	private Handler handler;
	private Thread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basicmap_activity);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		handler = new Handler();
		
		init();
		LatLng latLng = new LatLng(39.906901,116.397972);
		Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("垃圾箱位置").snippet("LocationMarker"));
        thread = new GetLocantionThread("http://101.200.55.171:8080/ceshi/ShowLoc?key=98ddce6fc91f0e569f190a5981d198b7",aMap,handler);
		thread.start();
	}


    /**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			
		}
		basicmap = (Button)findViewById(R.id.basicmap);
		basicmap.setOnClickListener(this);
		rsmap = (Button)findViewById(R.id.rsmap);
		rsmap.setOnClickListener(this);

	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();

	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.basicmap:
			aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 矢量地图模式
			break;
		case R.id.rsmap:
			aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
			break;
		}
		
	}

}
