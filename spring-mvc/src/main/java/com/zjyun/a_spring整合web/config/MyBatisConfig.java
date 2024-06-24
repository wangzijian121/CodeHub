package com.zjyun.a_spring整合web.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author : longChuanJiang
 * @date : Created in 2020/8/24 14:07
 */
public class MyBatisConfig {

    /**
     * 获取数据源
     *
     * @param dataSource 数据源
     * @return 数据源
     */
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean(@Autowired DataSource dataSource) {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        // 设置实体类的别名路径
        ssfb.setTypeAliasesPackage("com.zjyun.spring官方文档.Aop.AOP的应用.事务控制.model");
        return ssfb;
    }

    /**
     * 注册扫描器: 扫描包下面的mapper接口
     */
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        // 设置MyBatis的Mapper接口路径
        msc.setBasePackage("com.zjyun.spring官方文档.Aop.AOP的应用.事务控制.mapper");
        return msc;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
        DataSourceTransactionManager dtxm = new DataSourceTransactionManager();
        dtxm.setDataSource(dataSource);
        return dtxm;
    }
}