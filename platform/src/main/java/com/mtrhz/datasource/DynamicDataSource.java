package com.mtrhz.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Solrsky
 * @date 2019/1/9
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String DATA_SOURCE_MYSQL_MASTER = "mysqldb_master";
    public static final String DATA_SOURCE_MYSQL_SLAVE = "mysqldb_slave";

    public static final String DATA_SOURCE_ORACLE_MASTER = "oracledb_master";
    public static final String DATA_SOURCE_ORACLE_SLAVE = "oracledb_slave";


    public static final String DATA_SOURCE_SQLSERVER_MASTER = "sqlserverdb_master";
    public static final String DATA_SOURCE_SQLSERVER_SLAVE = "sqlserverdb_slave";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }
}
