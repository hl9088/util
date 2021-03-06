package com.lhl.util.proxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理，当目标对象没有实现接口时使用此代理方式
 * Created by lihongli on 2019/4/15
 */
public class CglibProxyFactory implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(CglibProxyFactory.class);

    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        logger.info("代理开始");
        Object value = method.invoke(target, args);
        logger.info("代理结束");
        return value;
    }
}
