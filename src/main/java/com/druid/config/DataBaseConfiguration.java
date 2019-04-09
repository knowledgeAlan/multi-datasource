package com.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid的DataResource配置类
 * @author Raye
 * @since 2016年10月7日14:14:18
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseConfiguration.class);
    @Value("${spring.master.datasource.url}")
    private String dbUrl;

    @Value("${spring.master.datasource.username}")
    private String username;

    @Value("${spring.master.datasource.password}")
    private String password;

    @Value("${spring.master.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.master.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.master.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.master.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.master.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.master.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.master.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.master.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.master.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.master.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.master.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.master.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.master.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.master.datasource.filters}")
    private String filters;

    @Value("${spring.master.datasource.connectionProperties}")
    private String connectionProperties;



    @Value("${spring.slave.datasource.url}")
    private String slaveDbUrl;

    @Value("${spring.slave.datasource.username}")
    private String slaveUsername;

    @Value("${spring.slave.datasource.password}")
    private String slavePassword;

    @Bean(name="masterDataSource")
    public DataSource master() {
        System.out.println("注入Master druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setDriverClassName(driverClassName);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle( minIdle);
        datasource.setMaxWait(Long.valueOf(maxWait));
        datasource.setMaxActive(Integer.valueOf(maxActive));
//        setMinEvictableIdleTimeMillis();
        try {
            datasource.setFilters("stat,wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return datasource;
    }

    @Bean(name = "slaveDataSource")
    public DataSource slave() {
        System.out.println("Slave druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(slaveDbUrl);
        datasource.setDriverClassName(driverClassName);
        datasource.setUsername(slaveUsername);
        datasource.setPassword(slavePassword);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxWait(maxWait);
        datasource.setMaxActive(maxActive);
//        setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver2.getProperty("min-evictable-idle-time-millis")));
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean
	public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("slaveDataSource") DataSource slaveDataSource) {
 		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put(DynamicDataSource.DatabaseType.Master, masterDataSource);
		targetDataSources.put(DynamicDataSource.DatabaseType.Slave, slaveDataSource);

		DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(masterDataSource);
		return dataSource;
	}


}
