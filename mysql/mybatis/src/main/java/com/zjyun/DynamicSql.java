package com.zjyun;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 动态SQL
 */
public class DynamicSql {
    public static void main(String[] args) throws IOException, InterruptedException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        AccountMapper mapper = session.getMapper(AccountMapper.class);
        mapper.deleteAccount(674);
        session.close();
    }
}