package com.wxy.dao;

import com.wxy.domain.account;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {
    /**
     * 根据id查询账户
     * @param id
     * @return
     */
    account findById(Integer id);

    /**
     * 根据name查询账户
     * @param name
     * @return
     */
    account findByName(String name);

    /**
     * 更新账户
     * @param a
     */
    void updateAccount(account a);
}
