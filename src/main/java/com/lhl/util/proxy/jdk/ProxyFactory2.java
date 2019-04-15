package com.lhl.util.proxy.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建一个代理工厂
 * Created by lihongli on 2019/4/15
 */
public class ProxyFactory2<T> {

    private static Logger logger = LoggerFactory.getLogger(ProxyFactory2.class);

    public Object getProxyInstance(Class<T> c) {
        return Proxy.newProxyInstance(
                c.getClassLoader(),
                new Class[]{c},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        logger.info("代理开始");
                        Object value = method.invoke(proxy, args);
                        logger.info("代理结束");
                        return value;
                    }
                }
        );
    }
}
