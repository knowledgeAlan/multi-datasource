package com.druid.aop;


import com.druid.config.DynamicDataSource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 数据源的切入面
 *
 */
@Aspect
@Component
public class DataSourceAOP {

    @Before("(@annotation(com.druid.annotation.Master) || execution(* com.druid..service..*.insert*(..)) || " +
            "execution(* com.druid.service..*.update*(..)) || execution(* com.druid.service..*.delete*(..)) || " +
            "execution(* com.druid.service..*.add*(..))) && !@annotation(com.druid.annotation.Slave) -")
    public void setWriteDataSourceType() {
        DynamicDataSource.master();
//        log.info("dataSource切换到：master");
    }

    @Before("(@annotation(com.druid.annotation.Slave) || execution(* com.druid.service..*.select*(..)) || execution(* com.druid.service..*.get*(..))) && !@annotation(com.druid.annotation.Master)")
    public void setReadDataSourceType() {
        DynamicDataSource.slave();
//        log.info("dataSource切换到：slave");
    }


}
