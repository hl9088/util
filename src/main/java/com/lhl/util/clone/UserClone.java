package com.lhl.util.clone;

import com.lhl.util.bean.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用clone方法浅克隆
 * Created by lihongli on 2019/4/12
 */
public class UserClone extends User implements Cloneable {

    private static Logger logger = LoggerFactory.getLogger(UserClone.class);

    private User child;

    public User getChild() {
        return child;
    }

    public void setChild(User child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "{user: age = " + getAge() + ", name = \"" + getName() + "\", child = \"{age = " + getChild().getAge()
                + ", name = " + getChild().getName() + "}\"}";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

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
