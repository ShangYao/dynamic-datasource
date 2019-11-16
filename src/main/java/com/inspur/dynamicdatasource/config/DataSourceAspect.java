package com.inspur.dynamicdatasource.config;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DataSourceAspect {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Pointcut("@annotation(com.inspur.dynamicdatasource.config.DynamicSwitchDataSource)")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    public void beforeExecute() {
        String tenantId = getTenantIdFromSession();
        if (tenantId != null) {
            dynamicDataSource.setCurrentThreadDataSource(tenantId);
        }

    }

    @After("dataSourcePointCut()")
    public void afterExecute() {
        DynamicDataSource.clearCurrentDataSourceKey();
    }

    //TODO 从session中获取租客id
    private String getTenantIdFromSession() {
        return "2";
    }
}
