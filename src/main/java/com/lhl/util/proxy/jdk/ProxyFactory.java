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
public class ProxyFactory {

    private static Logger logger = LoggerFactory.getLogger(ProxyFactory.class);

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        logger.info("代理开始");
                        Object value = method.invoke(target, args);
                        logger.info("代理结束");
                        return value;
                    }
                }
        );
    }
}
