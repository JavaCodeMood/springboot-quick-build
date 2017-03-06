
package com.dangdang.ddframe.rdb.sharding.example.jdbc.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

/**
 * ProjectName: sharding-jdbc-demo <br/>
 * ClassName: ModuloDatabaseShardingAlgorithm <br/>
 * Date: 2017年1月1日 下午1:17:12 <br/>
 * 
 * @author chababa
 * @version
 * @since JDK 1.7
 * @see
 */
public final class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		Range<Integer> range = shardingValue.getValueRange();
		for (Integer value = range.lowerEndpoint(); value <= range.upperEndpoint(); value++) {
			for (String each : availableTargetNames) {
				if (each.endsWith(value % 2 + "")) {
					result.add(each);
				}
			}
		}
		return result;
	}

	@Override
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
		for (String each : availableTargetNames) {
			if (each.endsWith(shardingValue.getValue() % 2 + "")) {
				return each;
			}
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		Collection<Integer> values = shardingValue.getValues();
		for (Integer value : values) {
			for (String each : availableTargetNames) {
				if (each.endsWith(value % 2 + "")) {
					result.add(each);
				}
			}
		}
		return result;
	}
}
