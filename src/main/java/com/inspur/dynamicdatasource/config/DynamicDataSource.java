package com.inspur.dynamicdatasource.config;

import com.inspur.dynamicdatasource.entity.DatabaseDetail;
import com.inspur.dynamicdatasource.mapper.DatabaseDetailMapper;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 缓存当前线程数据源的key（租户id）
     */
    private static final ThreadLocal<String> CURRENT_DATASOURCE_KEY = new ThreadLocal<>();
    /**
     * 缓存租户对应的数据源
     * ConcurrentHashMap<租户id，数据源>
     */
    private ConcurrentHashMap<Object, Object> targetDataSources = new ConcurrentHashMap<>();

    private DatabaseDetailMapper databaseDetailMapper = null;

    public DynamicDataSource(DataSource defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
    }

    /**
     * 选择当前线程数据源的key
     */
    @Override
    public Object determineCurrentLookupKey() {
        return CURRENT_DATASOURCE_KEY.get();
    }

    /**
     * 清除当前线程数据源key
     */
    public static void clearCurrentDataSourceKey() {
        CURRENT_DATASOURCE_KEY.remove();
    }

    /**
     * 设置当前线程的数据源
     */
    public void setCurrentThreadDataSource(String dataSourceKey) {
        if (!targetDataSources.containsKey(dataSourceKey)) {
            addNewDataSource(dataSourceKey);
        }
        CURRENT_DATASOURCE_KEY.set(dataSourceKey);
    }

    private synchronized void addNewDataSource(String dataSourceKey) {
        if (targetDataSources.containsKey(dataSourceKey)) {
            return;
        }
        DataSource datasource = createDataSource(dataSourceKey);
        targetDataSources.put(dataSourceKey, datasource);
        super.afterPropertiesSet();
    }


    private DataSource createDataSource(String dataSourceKey) {
        DatabaseDetail dbDetail = getDatabaseDetail(dataSourceKey);
        return DynamicDataSourceConfig.createDataSource(dbDetail);
    }

    // 数据库信息动态获取
    private DatabaseDetail getDatabaseDetail(String dataSourceKey) {
        if (null == databaseDetailMapper) {
            getDatabaseDetailMapper();
        }
        return databaseDetailMapper.selectOneByTenantId(dataSourceKey);
    }

    private synchronized void getDatabaseDetailMapper() {
        if (null == databaseDetailMapper) {
            databaseDetailMapper = SpringContextHolder.getBean(DatabaseDetailMapper.class);
        }
    }


}
