package com.inspur.dynamicdatasource.entity;

import lombok.Data;

@Data
public class DatabaseDetail {
    private long id;
    private String tenantId;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
