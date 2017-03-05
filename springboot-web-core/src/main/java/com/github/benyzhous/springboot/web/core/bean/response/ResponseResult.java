
package com.github.benyzhous.springboot.web.core.bean.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * ProjectName: web-core-base <br/>
 * ClassName: ResponseResult <br/>
 * Date: 2016年12月9日 下午6:22:58 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
@SuppressWarnings("deprecation")
public class ResponseResult implements Serializable {
	private static final long serialVersionUID = 1L;
	// 返回状态码：0成功，-1失败
	private Integer errcode;
	
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String errmsg;
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Object result;

	public ResponseResult() {
	}

	public ResponseResult(Integer errcode, String errmsg) {
		this.errcode = errcode;
		setErrmsg(errmsg);
	}

	public ResponseResult(Integer errcode, String errmsg, Object result) {
		this.errcode = errcode;
		setErrmsg(errmsg);
		this.result = result;
	}

	public ResponseResult(Integer errcode, Object result) {
		this.errcode = errcode;
		this.result = result;
		setErrmsg("");
	}
	
	public ResponseResult(Object result) {
		this.errcode = ResponseCodeConstants.SUCCESS;
		this.result = result;
		setErrmsg("");
	}

	// 此处如果errcode不是success，就会将对应的errmsg设置到对象返回给客户端
	public ResponseResult(Integer errcode) {
		this.errcode = errcode;
		setErrmsg("");
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		if ("".equals(errmsg)) {
			if (errcode != ResponseCodeConstants.SUCCESS) {
				this.errmsg = ResponseCodeConstants.getErrmsg(errcode);
			}
		} else {
			this.errmsg = errmsg;
		}
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("abc", "bcccd");
		//ResponseResult res = new ResponseResult(ResponseCodeConstants.FAIL, "ss");
	}
}
