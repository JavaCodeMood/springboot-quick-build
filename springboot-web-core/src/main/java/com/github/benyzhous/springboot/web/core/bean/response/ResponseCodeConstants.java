
package com.github.benyzhous.springboot.web.core.bean.response;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName: web-core-base <br/>
 * ClassName: ResponseCodeConstants <br/>
 * Date: 2016年12月9日 下午5:53:19 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
public class ResponseCodeConstants {
	// 请求成功
	public static int SUCCESS = 0;
	// 请求发生错误
	public static int FAIL = -1;
	// 请求地址不存在
	public static int URL_NOT_FOUND = 404;
	// 服务器错误
	public static int INTERNAL_SERVER_ERROR = 500;
	// 参数非法为空或者格式不正确
	public static int PARAMETER_ILLEGAL = 40001;
	
	/**
	 * appid为空
	 */
	public static int APPID_IS_EMPTY = 41000;
	/**
	 * appid无效或者不存在
	 */
	public static int APPID_INVALID = 41001;
	/**
	 * JSAPI 签名地址为空
	 */
	public static int JSAPI_SIGINURL_IS_EMPTY = 41002;
	/**
	 * access_token为空
	 */
	public static int ACCESS_TOKEN_IS_EMPTY = 41003;
	
	// 存储code和msg对应关系
	static Map<Integer, String> codemap = new HashMap<Integer, String>();

	static {
		codemap.put(SUCCESS, "");
		codemap.put(FAIL, "system error");
		codemap.put(URL_NOT_FOUND, "request uri is not found");
		codemap.put(INTERNAL_SERVER_ERROR, "internal server error");
		codemap.put(PARAMETER_ILLEGAL, "参数非法，为空或者格式不正确");
		codemap.put(APPID_IS_EMPTY, "appid is empty");
		codemap.put(APPID_INVALID, "invalid appid");
		codemap.put(JSAPI_SIGINURL_IS_EMPTY, " jsapi signurl is empty");
		codemap.put(ACCESS_TOKEN_IS_EMPTY, " access_token is empty");
	}

	// 根据code获取msg信息
	public static String getErrmsg(int errcode) {
		return codemap.get(errcode);
	}
}
