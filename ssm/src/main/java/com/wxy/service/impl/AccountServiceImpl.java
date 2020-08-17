package com.wxy.service.impl;

import com.wxy.dao.IAccountDao;
import com.wxy.domain.Account;
import com.wxy.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao iAccountDao;

    @Override
    public List<Account> findAll() {
        System.out.println("业务层查询所有");
        return iAccountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {

        System.out.println("业务层保存账户");
        iAccountDao.saveAccount(account);
    }
}
