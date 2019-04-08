package com.druid.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({ MybatisConfiguration.class })
public class TransactionConfiguration extends DataSourceTransactionManagerAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(TransactionConfiguration.class);

    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        logger.info("事物配置");
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
