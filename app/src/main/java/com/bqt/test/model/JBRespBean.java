package com.bqt.test.model;

/**
 * Desc：被动响应JS时给JS传递的数据的数据结构
 *
 * @author 白乾涛 <p>
 * @tag 响应<p>
 * @date 2018/5/8 00:28 <p>
 */
public class JBRespBean {
	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_FAILURE = "failure";
	
	public String status;
	public String msg;
	public Object data;
	
	private JBRespBean(Builder builder) {
		status = builder.status;
		msg = builder.msg;
		data = builder.data;
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static final class Builder {
		private String status;
		private String msg;
		private Object data;
		
		private Builder() {
		}
		
		public Builder status(String val) {
			status = val;
			return this;
		}
		
		public Builder msg(String val) {
			msg = val;
			return this;
		}
		
		public Builder data(Object val) {
			data = val;
			return this;
		}
		
		public JBRespBean build() {
			return new JBRespBean(this);
		}
	}
}
