package com.inspur.dynamicdatasource.config;

import com.inspur.dynamicdatasource.entity.DatabaseDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DynamicDataSourceConfig {

    @Value("${dynamic-datasource.default.url}")
    private String defaultUrl;
    @Value("${dynamic-datasource.default.driverClassName}")
    private String driverClassName;
    @Value("${dynamic-datasource.default.username}")
    private String defaultUsername;
    @Value("${dynamic-datasource.default.password}")
    private String defaultPassword;

    @Bean("defaultDataSource")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create().url(defaultUrl)
                .driverClassName(driverClassName)
                .username(defaultUsername)
                .password(defaultPassword).build();
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(DataSource defaultDataSource) {
        return new DynamicDataSource(defaultDataSource);
    }

    static DataSource createDataSource(DatabaseDetail dbDetail) {
        return DataSourceBuilder.create().url(dbDetail.getUrl())
                .driverClassName(dbDetail.getDriverClassName())
                .username(dbDetail.getUsername())
                .password(dbDetail.getPassword()).build();
    }

}
