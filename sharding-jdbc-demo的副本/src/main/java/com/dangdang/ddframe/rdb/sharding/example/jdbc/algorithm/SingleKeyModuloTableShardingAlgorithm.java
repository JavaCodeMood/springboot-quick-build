
package com.dangdang.ddframe.rdb.sharding.example.jdbc.algorithm;

/** 
 * ProjectName:	sharding-jdbc-demo <br/>
 * ClassName:  	SingleKeyModuloTableShardingAlgorithm <br/>
 * Date:     	2017年1月1日 下午9:35:41 <br/> 
 * @author   	chababa 
 * @version   	
 * @since    	JDK 1.7
 * @see       	
 */
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

public final class SingleKeyModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

	@Override
	public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
		for (String each : availableTargetNames) {
			if (each.endsWith(shardingValue.getValue() % 2 + "")) {
				return each;
			}
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		Collection<Long> values = shardingValue.getValues();
		for (Long value : values) {
			for (String each : availableTargetNames) {
				if (each.endsWith(value % 2 + "")) {
					result.add(each);
				}
			}
		}
		return result;
	}

	@Override
	public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		Range<Long> range = shardingValue.getValueRange();
		for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
			for (String each : availableTargetNames) {
				if (each.endsWith(i % 2 + "")) {
					result.add(each);
				}
			}
		}
		return result;
	}
}