package com.lhl.util.clone;

import com.lhl.util.bean.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 使用序列化的方法深度clone对象
 * Created by lihongli on 2019/4/12
 */
public class DeepCloneBySerialize {

    private static Logger logger = LoggerFactory.getLogger(DeepCloneBySerialize.class);

    @Test
    public void test() {
        User user = new User();
        user.setAge(1);
        user.setName("test");
        logger.info("初始值{}", user);

        ObjectInputStream ois = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(user);

            ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            User newUser = (User) ois.readObject();
            logger.info("clone之后的{}", newUser);

            newUser.setName("张三");
            logger.info("修改clone之后的{}", newUser);
            logger.info("原始值{}", user);
        } catch (Exception e) {
            logger.error("error:", e);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    logger.error("error:", e);
                }
            }
        }
    }
}
