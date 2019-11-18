package com.inspur.dynamicdatasource.config;

import com.inspur.dynamicdatasource.entity.DatabaseDetail;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DynamicDataSourceConfig {
    private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/tenant001?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DEFAULT_USER_NAME = "root";
    private static final String DEFAULT_PASSWORD = "root";

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

    static DataSource createDataSourceByTenantId(DatabaseDetail dbDetail) {
        return DataSourceBuilder.create().url(dbDetail.getUrl())
                .driverClassName(dbDetail.getDriverClassName())
                .username(dbDetail.getUsername())
                .password(dbDetail.getPassword()).build();
    }

}
