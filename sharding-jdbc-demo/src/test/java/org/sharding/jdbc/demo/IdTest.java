
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
	public static void main(String[] args) {
		final IPIdGenerator idGenerator = new IPIdGenerator();
		for (int i = 0; i < 100; i++) {
			int s = idGenerator.generateId().intValue() % 2;
			System.err.println(s);
		}
	}
}
