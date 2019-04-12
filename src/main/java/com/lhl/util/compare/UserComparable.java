package com.lhl.util.compare;

import com.lhl.util.bean.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Serializable 序列化
 * Comparable是排序接口。若一个类实现了Comparable接口，就意味着该类支持排序。
 * 实现了Comparable接口的类的对象的列表或数组可以通过Collections.sort或Arrays.sort进行自动排序。
 * Created by lihongli on 2019/4/12
 */
public class UserComparable extends User implements Comparable<UserComparable> {

    private static Logger logger = LoggerFactory.getLogger(UserComparable.class);

    /**
     * Comparable此接口只有一个方法compare，比较此对象与指定对象的顺序，
     * 如果该对象小于、等于或大于指定对象，则分别返回负整数、零或正整数。
     *
     * @param user
     * @return
     */
    @Override
    public int compareTo(UserComparable user) {
        return getAge() - user.getAge();
    }

    @Test
    public void test() {
        List<UserComparable> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            UserComparable user = new UserComparable();
            user.setAge((int) (Math.random() * 100));
            list.add(user);
        }
        logger.info("====排序前{}", list);
        Collections.sort(list);
        logger.info("====排序后{}", list);
    }
}
