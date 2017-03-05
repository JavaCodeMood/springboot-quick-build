
package com.github.benyzhous.apigateway.vo;

import java.io.Serializable;

/**
 * ProjectName: springboot-apigateway-sample <br/>
 * ClassName: JsonBodyVo <br/>
 * Date: 2017年3月6日 上午3:36:28 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
public class JsonBodyVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "JsonBodyVO [id=" + id + ", name=" + name + "]";
	}

}
