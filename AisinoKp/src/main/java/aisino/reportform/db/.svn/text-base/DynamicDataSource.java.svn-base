package aisino.reportform.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 
 * 动态数据源
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseContextHolder.getCustomerType();
	}
}
