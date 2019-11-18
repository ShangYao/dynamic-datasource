package com.inspur.dynamicdatasource.config.annotation;

import java.lang.annotation.*;

/**
 * 根据session中的租户id，动态切换数据源，无租户id时，使用默认数据源
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSource {
}
