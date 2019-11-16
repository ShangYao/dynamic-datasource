package com.inspur.dynamicdatasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inspur.dynamicdatasource.entity.DatabaseDetail;
import com.inspur.dynamicdatasource.entity.User;
import org.apache.ibatis.annotations.Select;

public interface DatabaseDetailMapper extends BaseMapper<DatabaseDetail> {

    @Select("select * from database_detail where tenant_id=#{tenantId} limit 1 ")
    DatabaseDetail selectOneByTenantId(String tenantId);
}
