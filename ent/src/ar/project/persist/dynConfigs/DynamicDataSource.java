package ar.project.persist.dynConfigs;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		DataSourceType key = ContextHolder.getDSType();
		return key;
	}

}
