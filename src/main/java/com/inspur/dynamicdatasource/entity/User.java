package com.inspur.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
@KeySequence("seq_user")
public class User {

    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    private String name;
    private String tenantId;
}
