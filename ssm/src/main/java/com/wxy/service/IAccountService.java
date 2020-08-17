package com.wxy.service;

import com.wxy.domain.Account;

import java.util.List;

/**
 * 业务层接口
 */
public interface IAccountService {
    /**
     * 查询所有
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存用户
     */
    public void saveAccount(Account account);
}
