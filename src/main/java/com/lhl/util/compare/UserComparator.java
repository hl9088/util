package com.lhl.util.compare;

import com.lhl.util.bean.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comparator是比较接口，我们如果需要控制某个类的次序，而该类本身不支持排序(即没有实现Comparable接口)，那么我们就可以建立一个“该类的比较器”来进行排序，这个“比较器”只需要实现Comparator接口即可。
 * 也就是说，我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序。
 * Created by lihongli on 2019/4/12
 */
public class UserComparator implements Comparator<User> {

    private static Logger logger = LoggerFactory.getLogger(UserComparator.class);

    @Override
    public int compare(User o1, User o2) {
        return o1.getAge() - o2.getAge();
    }

    @Test
    public void test(){
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setAge((int) (Math.random() * 100));
            list.add(user);
        }
        logger.info("====排序前{}", list);
        Collections.sort(list, new UserComparator());
        logger.info("====排序后{}", list);
    }
}
