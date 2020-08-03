package com.wxy.dao.impl;

import com.wxy.dao.IAccountDao;
import com.wxy.domain.Account;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner runner;

    public List<Account> findAllAccount() {
        try {
            return runner.query("select * from account", new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Account findAccountById(Integer accountId) {
        try {
            return runner.query("select * from account where id = ?", new BeanHandler<Account>(Account.class),accountId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void saveAccount(Account account) {
        try {
            runner.update("insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        try {
            runner.update("update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            runner.update("delete from account where id = ?",accountId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
