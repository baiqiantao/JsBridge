package com.bqt.test.handler;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.bqt.test.model.JBRespBean;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;

/**
 * Desc：显示原生toast或弹窗
 *
 * @author 白乾涛 <p>
 * @tag 显示原生toast或弹窗<p>
 * @date 2018/5/8 00:18 <p>
 */
public class ShowTipsBridgeHandler implements BridgeHandler {
	private static final String TYPE_TOAST = "toast";
	private static final String TYPE_DIALOG = "dialog";
	
	private Activity mActivity;
	
	public ShowTipsBridgeHandler(Activity mActivity) {
		this.mActivity = mActivity;
	}
	
	/**
	 * @param data     js调用原生所传递的参数
	 * @param function js调用原生所传递的回调接口
	 */
	@Override
	public void handler(String data, CallBackFunction function) {
		Log.i("bqt", "【显示原生toast或弹窗，参数】" + data);
		ParameterBean parameterBean = new Gson().fromJson(data, ParameterBean.class);
		
		if (TYPE_TOAST.equals(parameterBean.type)) {
			Toast.makeText(mActivity, parameterBean.msg, Toast.LENGTH_SHORT).show();
			function.onCallBack(new Gson().toJson(JBRespBean.newBuilder().status(JBRespBean.STATUS_SUCCESS).build()));
		} else if (TYPE_DIALOG.equals(parameterBean.type)) {
			new AlertDialog.Builder(mActivity).setTitle(parameterBean.title).setMessage(parameterBean.msg).show();
			function.onCallBack(new Gson().toJson(JBRespBean.newBuilder().status(JBRespBean.STATUS_SUCCESS).build()));
		} else {
			function.onCallBack(new Gson().toJson(JBRespBean.newBuilder().status(JBRespBean.STATUS_FAILURE).build()));
		}
	}
	
	static class ParameterBean {
		String type;
		String title;
		String msg;
	}
}