package com.zjl.test;

import com.zjl.test.service.TestService;
import com.zjl.test.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMerge {

    ClassPathXmlApplicationContext ctx;

    @Before
    public void testSpring() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        TestService ts = (TestService) ctx.getBean("testService");
//        ts.say();
    }

    @Test
    public void testHibernate() {
        SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.save(new Person("人员1"));

        tx.commit();
        session.close();
    }

    @Test
    public void testSerivceAndDao() {
        TestService testService = (TestService) ctx.getBean("testService");
//        testService.save(new Person("人员2"));
        System.out.println(testService.findPerson("3420a6885e365eb6015e365eb8a80000").getName());
    }

    @Test
    public void testReadOnly() {
        //如果只读事务中出现更新操作则回滚
        TestService testService = (TestService) ctx.getBean("testService");
        System.out.println(testService.findPerson("3420a6885e365eb6015e365eb8a80000").getName());
    }

    @Test
    public void testRollBack() {
        //如果更新操作中出现任何异常则回滚
        TestService testService = (TestService) ctx.getBean("testService");
        testService.save(new Person("人员4"));
    }
}
