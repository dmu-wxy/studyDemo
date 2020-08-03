package com.wxy.test;

import com.wxy.domain.Account;
import com.wxy.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试，测试配置
 */
public class AccountServiceTest {
    @Test
    public void testfindAll() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        List<Account> accountList = as.findAllAccount();
        for(Account account : accountList){
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        Account account = as.findAccountById(1);
        System.out.println(account.getName());
    }

    @Test
    public void testSave() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        Account account = new Account();
        account.setName("wxy");
        account.setMoney(12000f);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        Account account = new Account();
        account.setId(4);
        account.setName("wxy");
        account.setMoney(13000f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        as.deleteAccount(4);
    }
}
