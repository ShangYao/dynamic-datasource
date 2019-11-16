package com.inspur.dynamicdatasource.entity;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private String tenantId;
}
