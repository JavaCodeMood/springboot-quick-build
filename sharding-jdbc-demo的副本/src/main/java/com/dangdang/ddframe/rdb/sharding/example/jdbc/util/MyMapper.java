
package com.dangdang.ddframe.rdb.sharding.example.jdbc.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/** 
 * ProjectName:	springboot-quick-test-autoconfigure
 * ClassName:  	MyMapper <br/> 
 * Function: 	TODO ADD FUNCTION. <br/> 
 * Reason:   	TODO ADD REASON. <br/> 
 * Date:     	2016年10月23日 下午2:19:57 <br/> 
 * @author   	chababa 
 * @version   	
 * @since    	JDK 1.7
 * @see       	
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}