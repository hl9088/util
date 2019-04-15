package com.lhl.util.proxy.cglib;

import com.lhl.util.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lihongli on 2019/4/15
 */
public class CglibUserDao {

    private static Logger logger = LoggerFactory.getLogger(CglibUserDao.class);

    public void save(User user){
        logger.info("用户信息{}", user);
    }
}
