package com.bqt.test;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bqt.test.handler.BackNativeBridgeHandler;
import com.bqt.test.handler.DefaultBridgeHandler;
import com.bqt.test.handler.DeviceInfoBridgeHandler;
import com.bqt.test.handler.ShowTipsBridgeHandler;
import com.bqt.test.model.JBJsIDs;
import com.bqt.test.model.JBNativeIDs;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {
	private BridgeWebView mBridgeWebView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBridgeWebView = new BridgeWebView(this);
		registerHandler();
		mBridgeWebView.loadUrl("file:///android_asset/jsbridge.html");
		getListView().addFooterView(mBridgeWebView);
		
		String[] array = {"调用JS中的名为showInHtml的Handler",
				"调用JS中的默认Handler，没有回调",
				"调用JS中的默认Handler，有回调",};
		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList(array))));
	}
	
	private void registerHandler() {
		mBridgeWebView.setDefaultHandler(new DefaultBridgeHandler());//默认的Handle
		mBridgeWebView.registerHandler(JBNativeIDs.BRIDGE_ID_DEVICEINFO, new DeviceInfoBridgeHandler());//获取设备信息
		mBridgeWebView.registerHandler(JBNativeIDs.BRIDGE_ID_BACKNATIVE, new BackNativeBridgeHandler(this));//结束当前Activity
		mBridgeWebView.registerHandler(JBNativeIDs.BRIDGE_ID_SHOWTIPS, new ShowTipsBridgeHandler(this));//显示原生toast或弹窗
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				mBridgeWebView.callHandler(JBJsIDs.BRIDGE_ID_SHOWINHTML, "【包青天20094】", new SimpleCallBackFunction());
				break;
			case 1:
				mBridgeWebView.send("【包青天20095】");
				break;
			case 2:
				mBridgeWebView.send("【包青天20096】", new SimpleCallBackFunction());
				break;
		}
	}
	
	class SimpleCallBackFunction implements CallBackFunction {
		@Override
		public void onCallBack(String data) {
			Log.i("bqt", "【JS回调】，是否在主线程：" + (Looper.myLooper() == Looper.getMainLooper()) + "\n" + data);//true
			//也可以用Thread.currentThread() == Looper.getMainLooper().getThread()
		}
	}
}