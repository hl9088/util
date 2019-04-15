package com.lhl.util.proxy.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理，当目标对象没有实现接口时使用此代理方式
 * 不创建目标对象的实例
 * Created by lihongli on 2019/4/15
 */
public class CglibProxyFactory2<T> implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(CglibProxyFactory2.class);

    public T getProxyInstance(Class<T> c) {
        Enhancer en = new Enhancer();
        en.setSuperclass(c);
        en.setCallback(this);
        return (T) en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        logger.info("代理开始");
        Object value = methodProxy.invokeSuper(o, args);
        logger.info("代理结束");
        return value;
    }
}
