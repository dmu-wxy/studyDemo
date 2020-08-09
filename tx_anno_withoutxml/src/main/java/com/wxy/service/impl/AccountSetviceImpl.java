package com.wxy.service.impl;

import com.wxy.dao.IAccountDao;
import com.wxy.domain.account;
import com.wxy.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class AccountSetviceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    public account findAccountById(Integer accountId) {
        account a = accountDao.findById(accountId);
        return a;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void transfer(String sourceName, String targetName, float money) {
        account scourseAccount = accountDao.findByName(sourceName);
        //根据名称查询转入账户
        account targetAccount = accountDao.findByName(targetName);
        //转出账户减钱
        scourseAccount.setMoney(scourseAccount.getMoney() - money);
        //转入账户加钱
        targetAccount.setMoney(targetAccount.getMoney() + money);
        //更新转出账户
        accountDao.updateAccount(scourseAccount);
        //int a = 1/0;
        //更新转入账户
        accountDao.updateAccount(targetAccount);
    }
}
