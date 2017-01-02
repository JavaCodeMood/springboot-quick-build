
package org.sharding.jdbc.demo;

import com.dangdang.ddframe.rdb.sharding.id.generator.self.IPIdGenerator;

/**
 * ProjectName: sharding-jdbc-demo <br/>
 * ClassName: IdTest <br/>
 * Date: 2017年1月2日 下午1:33:53 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
public class IdTest {
	public static void main(String[] args){
        final IPIdGenerator idGenerator = new IPIdGenerator();
        System.err.println(idGenerator.generateId());
	}
}
