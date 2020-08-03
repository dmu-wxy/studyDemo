package com.wxy.dao;

import com.wxy.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {
    public List<Account> findAllAccount();

    public Account findAccountById(Integer accountId);

    public void saveAccount(Account account);

    public void updateAccount(Account account);

    public void deleteAccount(Integer accountId);
}
