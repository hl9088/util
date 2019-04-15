package com.lhl.util.proxy.jdk;

import com.lhl.util.proxy.dao.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lihongli on 2019/4/15
 */
public class Handler implements InvocationHandler {

    private static Logger logger = LoggerFactory.getLogger(Handler.class);

    private IUserDao userDao;

    public Handler(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("代理开始");
        Object value = method.invoke(userDao, args);
        logger.info("代理结束");
        return value;
    }
}
