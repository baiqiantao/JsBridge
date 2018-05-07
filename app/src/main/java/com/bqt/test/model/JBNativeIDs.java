package com.bqt.test.model;

/**
 * Desc：被动响应JS时给JS传递的数据的数据结构
 *
 * @author 白乾涛 <p>
 * @tag 响应<p>
 * @date 2018/5/8 00:28 <p>
 */
public class JBNativeIDs {
	/**
	 * 点击网页中的返回按钮时结束当前Activity
	 */
	public static final String BRIDGE_ID_BACKNATIVE = "backNative";
	/**
	 * 获取设备信息
	 */
	public static final String BRIDGE_ID_DEVICEINFO = "deviceInfo";
	/**
	 * 显示原生toast或弹窗
	 */
	public static final String BRIDGE_ID_SHOWTIPS = "showTips";
}
