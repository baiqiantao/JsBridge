package com.bqt.test.handler;

import android.os.SystemClock;
import android.util.Log;

import com.bqt.test.model.JBRespBean;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;

/**
 * Desc：获取设备信息
 *
 * @author 白乾涛 <p>
 * @tag 获取设备信息<p>
 * @date 2018/5/8 00:02 <p>
 */
public class DeviceInfoBridgeHandler implements BridgeHandler {
	
	/**
	 * @param data     js调用原生所传递的参数
	 * @param function js调用原生所传递的回调接口
	 */
	@Override
	public void handler(String data, final CallBackFunction function) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				SystemClock.sleep(5000);//模拟耗时操作
				DeviceInfo deviceInfo = new DeviceInfo("小米6", "8.0");
				JBRespBean bridgeRespBean = JBRespBean.newBuilder()
						.status(JBRespBean.STATUS_SUCCESS)
						.msg("成功了")
						.data(deviceInfo)
						.build();
				
				String response = new Gson().toJson(bridgeRespBean);
				Log.i("bqt", "【给JS的响应】" + response);
				function.onCallBack(response);
			}
		}).start();
	}
	
	static class DeviceInfo {
		String phoneName;
		String sysVersion;
		
		DeviceInfo(String phoneName, String sysVersion) {
			this.phoneName = phoneName;
			this.sysVersion = sysVersion;
		}
	}
}
