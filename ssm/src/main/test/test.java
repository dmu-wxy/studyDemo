import com.wxy.dao.IAccountDao;
import com.wxy.domain.Account;
import com.wxy.service.IAccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.util.List;

public class test {

    @Test
    public void run1(){
        //加载
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAccountService as = applicationContext.getBean("accountService",IAccountService.class);
        as.findAll();
    }

    @Test
    public void testMybatis() throws Exception{
        InputStream asStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(asStream);
        SqlSession sqlSession = factory.openSession();
        IAccountDao dao = sqlSession.getMapper(IAccountDao.class);
        Account account = new Account();
        account.setMoney(200);
        account.setName("mm");
        dao.saveAccount(account);
        sqlSession.commit();
//        List<Account> accounts = dao.findAll();
//        for(Account a : accounts){
//            System.out.println(a);
//        }
        sqlSession.close();
        asStream.close();
    }
}
