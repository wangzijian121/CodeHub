package com.zjyun;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 一级缓存
 */
public class L1Cache {
    public static void main(String[] args) throws IOException, InterruptedException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountMapper mapper = session.getMapper(AccountMapper.class);
            Account account = mapper.selectAccount(3);
            System.out.println("----");

            //session.clearCache();
            //TimeUnit.SECONDS.sleep(5);//休眠时如果修改数据库查询的返回值还是一样的（脏数据😣）
            Account account2 = mapper.selectAccount(3);

            //Account account = session.selectOne("selectAccount", 3);
            session.commit();
            session.close();
            System.out.println(account);

            System.out.println(account2);
        }
    }
}