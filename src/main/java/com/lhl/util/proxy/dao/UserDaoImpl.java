package com.lhl.util.proxy.dao;

import com.lhl.util.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lihongli on 2019/4/15
 */
public class UserDaoImpl implements IUserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void save(User user) {
        logger.info("保存用户信息{}", user);
    }
}
