package com.inspur.dynamicdatasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DynamicDataSourceConfig {
    private static final String DEFAULT_DB_URL = "jdbc:mysql://192.168.139.128:3306/tenant001?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DEFAULT_USER_NAME = "root";
    private static final String DEFAULT_PASSWORD = "12345678";

    @Bean("defaultDataSource")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create().url(DEFAULT_DB_URL)
                .driverClassName(DRIVER_CLASS_NAME)
                .username(DEFAULT_USER_NAME)
                .password(DEFAULT_PASSWORD).build();
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(DataSource defaultDataSource) {
        return new DynamicDataSource(defaultDataSource);
    }

    public static DataSource createDataSourceByTenantId(String tenantId, String password, String username) {
        return DataSourceBuilder.create().url(getDBUrl(tenantId))
                .driverClassName(DRIVER_CLASS_NAME)
                .username(username)
                .password(password).build();
    }

    private static String getDBUrl(String tenantId) {
        return DEFAULT_DB_URL.replace("001", tenantId);
    }
}
