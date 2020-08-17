package com.wxy.dao;

import com.wxy.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao接口
 */
@Repository
public interface IAccountDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from account;")
    public List<Account> findAll();

    /**
     * 保存用户
     */
    @Insert("insert into account (name,money) values (#{name},#{money});")
    public void saveAccount(Account account);
}
