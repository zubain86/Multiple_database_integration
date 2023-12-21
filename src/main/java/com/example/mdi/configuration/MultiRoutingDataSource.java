package com.example.mdi.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

class MultiRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.getCurrentDb();
    }
}
