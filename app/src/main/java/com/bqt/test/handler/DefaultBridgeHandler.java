package com.bqt.test.handler;

import android.util.Log;

import com.bqt.test.model.JBRespBean;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;

/**
 * Desc：You can set a default handler in Java, so that js can send message to Java without assigned handlerName
 *
 * @author 白乾涛 <p>
 * @tag 默认的Handle<p>
 * @date 2018/5/8 00:42 <p>
 */
public class DefaultBridgeHandler implements BridgeHandler {
	
	/**
	 * @param data     js调用原生所传递的参数
	 * @param function js调用原生所传递的回调接口
	 */
	@Override
	public void handler(String data, CallBackFunction function) {
		Log.i("bqt", "【默认的Handle，参数】" + data);
		function.onCallBack(new Gson().toJson(JBRespBean.newBuilder().status(JBRespBean.STATUS_SUCCESS).build()));
	}
}