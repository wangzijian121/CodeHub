package com.zjyun;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 查询分页
 */
public class PageSql {
    public static void main(String[] args) throws IOException, InterruptedException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {


            AccountMapper mapper = session.getMapper(AccountMapper.class);
            PageHelper.startPage(2, 3);
            List<Account> accounts = mapper.selectAllAccounts();
            accounts.forEach(System.out::println);
        }
    }
}