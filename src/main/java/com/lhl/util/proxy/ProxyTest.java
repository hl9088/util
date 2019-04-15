package com.lhl.util.proxy;

import com.lhl.util.bean.User;
import com.lhl.util.proxy.cglib.CglibProxyFactory;
import com.lhl.util.proxy.cglib.CglibProxyFactory2;
import com.lhl.util.proxy.cglib.CglibUserDao;
import com.lhl.util.proxy.dao.IUserDao;
import com.lhl.util.proxy.dao.UserDaoImpl;
import com.lhl.util.proxy.jdk.Handler;
import com.lhl.util.proxy.jdk.ProxyFactory;
import com.lhl.util.proxy.jdk.ProxyFactory2;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态代理
 * （静态代理类似于新建一个类，引入要代理对象的实例，然后在代理类方法中调用代理对象的方法）
 * 代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理
 * Created by lihongli on 2019/4/15
 */
public class ProxyTest {

    private static Logger logger = LoggerFactory.getLogger(ProxyTest.class);

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        List<String> proxy = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(list, args);
            }
        });
        proxy.add("test");
        logger.info("{}", list);
        logger.info("{}", proxy.get(0));
        proxy.remove(0);
        logger.info("{}", list);
    }

    @Test
    public void factoryTest() {
        IUserDao userDao = new UserDaoImpl();
        IUserDao proxy = (IUserDao) new ProxyFactory(userDao).getProxyInstance();
        /*IUserDao proxy = (IUserDao) Proxy.newProxyInstance(IUserDao.class.getClassLoader(), new Class[]{IUserDao.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(userDao, args);
            }
        });*/
        proxy.save(new User(111, "test"));
    }

    /*@Test
    public void factoryTest2() {
        IUserDao proxy = (IUserDao) new ProxyFactory2<IUserDao>().getProxyInstance(IUserDao.class);
        *//*IUserDao proxy = (IUserDao) Proxy.newProxyInstance(IUserDao.class.getClassLoader(), new Class[]{IUserDao.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(userDao, args);
            }
        });*//*
        proxy.save(new User(111, "test"));
    }*/

    @Test
    public void handlerTest() {
        IUserDao userDao = new UserDaoImpl();
        Handler handler = new Handler(userDao);
        IUserDao proxy = (IUserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),
                userDao.getClass().getInterfaces(), handler);
        //IUserDao proxy = (IUserDao) Proxy.newProxyInstance(IUserDao.class.getClassLoader(), new Class[]{IUserDao.class}, handler);
        proxy.save(new User(111, "test"));
    }

    @Test
    public void cglibTest(){
        CglibUserDao dao = new CglibUserDao();
        CglibUserDao proxy = (CglibUserDao)new CglibProxyFactory(dao).getProxyInstance();
        proxy.save(new User(111, "test"));
    }

    @Test
    public void cglibTest2(){
        CglibUserDao proxy = new CglibProxyFactory2<CglibUserDao>().getProxyInstance(CglibUserDao.class);
        proxy.save(new User(111, "test"));
    }
}
