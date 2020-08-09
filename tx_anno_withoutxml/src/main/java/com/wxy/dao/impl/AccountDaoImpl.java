package com.wxy.dao.impl;

import com.wxy.dao.IAccountDao;
import com.wxy.domain.account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * 账户的持久层实现类
 */
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public account findById(Integer id) {
        List<account> accountList =  jdbcTemplate.query("select * from account where id = ?",new BeanPropertyRowMapper<account>(account.class),id);
        return accountList.isEmpty() ? null : accountList.get(0);
    }

    public account findByName(String name) {
        List<account> accountList =  jdbcTemplate.query("select * from account where name = ?",new BeanPropertyRowMapper<account>(account.class),name);
        if(accountList.isEmpty()) {
            return null;
        } else if(accountList.size() > 1) {
            throw new RuntimeException("结果集不唯一");
        } else {
            return accountList.isEmpty() ? null : accountList.get(0);
        }
    }

    public void updateAccount(account a) {
        jdbcTemplate.update("update account set name = ?,money = ? where id = ?",a.getName(),a.getMoney(),a.getId());
    }
}
