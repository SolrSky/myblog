package com.mtrhz.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mtrhz.datasource.DynamicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Solrsky
 * @date 2019/1/15
 */
//@Configuration
//@PropertySource("classpath:application.properties")
public class JdbcConfig {

    private static final Logger logger = LoggerFactory.getLogger(JdbcConfig.class);

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${dbcp.initialSize}")
    private int druidInitialSize;

    @Value("${dbcp.maxActive}")
    private int druidMaxActive;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "mysqldb_master")
    public DruidDataSource mysqlMasterDataSource(){
        logger.info("开始加载数据源--mysqlMasterDataSource...");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        dataSource.setInitialSize(druidInitialSize);
        dataSource.setMaxActive(druidMaxActive);
        return dataSource;
    }

    @Bean(name = "mysqldb_slave")
    public DruidDataSource mysqlSlaveDataSource(){
        logger.info("开始加载数据源--mysqlSlaveDataSource...");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        dataSource.setInitialSize(druidInitialSize);
        dataSource.setMaxActive(druidMaxActive);
        return dataSource;
    }

    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("mysqldb_master") DruidDataSource mysqlDBMaster,
                                               @Qualifier("mysqldb_slave") DruidDataSource mysqlDBSlave){
        logger.info("开始配置动态数据源DynamicDataSource...");
        DynamicDataSource source = new DynamicDataSource();
        Map<Object, Object> map = new HashMap<>();
        map.put("mysqldb_master", mysqlDBMaster);
        map.put("mysqldb_slave", mysqlDBSlave);
        source.setTargetDataSources(map);
        return source;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DynamicDataSource dynamicDataSource) {
        logger.info("开始注册jdbcTemplate...");
        return new JdbcTemplate(dynamicDataSource);
    }
}
