package com.zjyun;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 二级缓存
 */
public class L2Cache {
    public static void main(String[] args) throws IOException, InterruptedException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session1 = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();

        AccountMapper mapper1 = session1.getMapper(AccountMapper.class);
        AccountMapper mapper2 = session2.getMapper(AccountMapper.class);

        Account account1 = mapper1.selectAccount(3);
        session1.commit();//必须提交后才会存储到二级缓存
        session1.clearCache();//清理一级缓存，并不会影响二级缓存
        session1.close();

        Account account2 = mapper2.selectAccount(3);
        session2.commit();

        session2.close();

        System.out.println(account1);
        System.out.println(account2);
    }
}