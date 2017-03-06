
package com.dangdang.ddframe.rdb.sharding.example.jdbc.conf.sharding;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.example.jdbc.algorithm.ModuloDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.example.jdbc.algorithm.SingleKeyModuloTableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.jdbc.ShardingDataSource;

/**
 * ProjectName: sharding-jdbc-demo <br/>
 * ClassName: ShardingDataSource <br/>
 * Date: 2017年1月1日 下午1:14:34 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
@Configuration
public class ShardingDataSourceConfig {

	@Bean
	private static ShardingDataSource getShardingDataSource() {
		DataSourceRule dataSourceRule = new DataSourceRule(createDataSourceMap());
		TableRule orderTableRule = 
				TableRule.builder("t_order").
				actualTables(Arrays.asList("t_order_0", "t_order_1")).
				dataSourceRule(dataSourceRule).build();
		
		TableRule orderItemTableRule = 
				TableRule.builder("t_order_item").
				actualTables(Arrays.asList("t_order_item_0", "t_order_item_1")).
				dataSourceRule(dataSourceRule).build();
		
		ShardingRule shardingRule = 
				ShardingRule.builder().dataSourceRule(dataSourceRule).
				tableRules(Arrays.asList(orderTableRule, orderItemTableRule))
				.bindingTableRules(Collections.singletonList(new BindingTableRule(Arrays.asList(orderTableRule, orderItemTableRule))))
				.databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
				.tableShardingStrategy(new TableShardingStrategy("order_id", new SingleKeyModuloTableShardingAlgorithm())).build();
		return new ShardingDataSource(shardingRule);
	}

	private static Map<String, DataSource> createDataSourceMap() {
		Map<String, DataSource> result = new HashMap<>(2);
		result.put("ds_0", createDataSource("ds_0"));
		result.put("ds_1", createDataSource("ds_1"));
		return result;
	}

	private static DataSource createDataSource(final String dataSourceName) {
		BasicDataSource result = new BasicDataSource();
		result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
		result.setUrl(String.format("jdbc:mysql://xx:3306/%s", dataSourceName));
		result.setUsername("xx");
		result.setPassword("xx");
		return result;
	}
}
