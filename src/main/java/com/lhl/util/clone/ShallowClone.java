package com.lhl.util.clone;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用clone方法浅克隆
 * Created by lihongli on 2019/4/12
 */
public class ShallowClone {

    private static Logger logger = LoggerFactory.getLogger(ShallowClone.class);

    @Test
    public void c() throws CloneNotSupportedException {
        UserClone user = new UserClone();
        user.setAge(1);
        user.setName("test");
        user.setChild(user);
        logger.info("初始值{}", user);

        UserClone newUser = (UserClone) user.clone();
        logger.info("clone值{}", user);

        newUser.setAge(2);
        newUser.setName("test2");
        newUser.getChild().setName("test2");
        logger.info("修改clone值{}", newUser);
        logger.info("修改clone后初始值{}", user);
    }
}
