package com.zjyun;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/7/22
 */
//@CacheNamespace(implementation = PerpetualCache.class)
public interface AccountMapper {
    String sql = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, " +
            "P.LAST_NAME,P.CREATED_ON, P.UPDATED_ON " +
            "FROM PERSON P, ACCOUNT A " +
            "INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID " +
            "INNER JOIN COMPANY C on D.COMPANY_ID = C.ID " +
            "WHERE (P.ID = A.ID AND P.FIRST_NAME like ?) " +
            "OR (P.LAST_NAME like ?) " +
            "GROUP BY P.ID " +
            "HAVING (P.LAST_NAME like ?) " +
            "OR (P.FIRST_NAME like ?) " +
            "ORDER BY P.ID, P.FULL_NAME";

    //使用SQL对象构造SQL语句
    String sql2 = new SQL() {{
        SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
        SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
        FROM("PERSON P");
        FROM("ACCOUNT A");
        INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
        INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
        WHERE("P.ID = A.ID");
        WHERE("P.FIRST_NAME like ?");
        OR();
        WHERE("P.LAST_NAME like ?");
        GROUP_BY("P.ID");
        HAVING("P.LAST_NAME like ?");
        OR();
        HAVING("P.FIRST_NAME like ?");
        ORDER_BY("P.ID");
        ORDER_BY("P.FULL_NAME");
    }}.toString();

    Account selectAccount(int id);

    Account selectAccountDynamic(int id);

    List<Account> selectAllAccounts();

    void deleteAccount(int id);

    @Update("SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, " +
            "P.LAST_NAME,P.CREATED_ON, P.UPDATED_ON " +
            "FROM PERSON P, ACCOUNT A " +
            "INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID " +
            "INNER JOIN COMPANY C on D.COMPANY_ID = C.ID " +
            "WHERE (P.ID = A.ID AND P.FIRST_NAME like ?) " +
            "OR (P.LAST_NAME like ?) " +
            "GROUP BY P.ID " +
            "HAVING (P.LAST_NAME like ?) " +
            "OR (P.FIRST_NAME like ?) " +
            "ORDER BY P.ID, P.FULL_NAME")
    void updateAccount(Account account);
}
