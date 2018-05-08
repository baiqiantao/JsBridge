package com.bqt.test.handler;

import android.app.Activity;
import android.util.Log;

import com.bqt.test.model.JBRespBean;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;

/**
 * Desc：点击网页中的返回按钮时结束当前Activity
 *
 * @author 白乾涛 <p>
 * @tag 结束当前Activity<p>
 * @date 2018/5/8 00:02 <p>
 */
public class BackNativeBridgeHandler implements BridgeHandler {
	private Activity mActivity;
	
	public BackNativeBridgeHandler(Activity mActivity) {
		this.mActivity = mActivity;
	}
	
	/**
	 * @param data     js调用原生所传递的参数
	 * @param function js调用原生所传递的回调接口
	 */
	@Override
	public void handler(String data, CallBackFunction function) {
		Log.i("bqt", "【结束当前Activity，参数】" + data);
		
		mActivity.finish();
		JBRespBean bridgeRespBean = JBRespBean.newBuilder()
				.status(JBRespBean.STATUS_SUCCESS)
				.time(System.currentTimeMillis())
				.build();
		
		function.onCallBack(new Gson().toJson(bridgeRespBean));
	}
}
