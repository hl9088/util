package com.lhl.util.compare;

import com.lhl.util.bean.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by lihongli on 2019/4/12
 */
public class HashMapSort {

    private static Logger logger = LoggerFactory.getLogger(HashMapSort.class);

    /**
     * 当user没有实现比较接口时，sort过程中直接实现一个接口类进行对比
     */
    @Test
    public void test() {
        HashMap<Integer, User> map = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setAge((int) (Math.random() * 100));
            map.put(i, user);
        }
        logger.info("====排序前{}", map);
        Set<Map.Entry<Integer, User>> entrySet = map.entrySet();
        List<Map.Entry<Integer, User>> list = new ArrayList<>(entrySet);
        /*
        // 完整写法
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o1.getValue().getAge() - o2.getValue().getAge();
            }
        });
        // 简写
        Collections.sort(list, (o1, o2) -> {return o1.getValue().getAge() - o2.getValue().getAge();});
        Collections.sort(list, (o1, o2) -> o1.getValue().getAge() - o2.getValue().getAge());
        */
        Collections.sort(list, Comparator.comparingInt((Map.Entry<Integer, User> o) -> o.getValue().getAge()));
        LinkedHashMap<Integer, User> map2 = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> entry : list) {
            map2.put(entry.getKey(), entry.getValue());
        }
        logger.info("====排序后{}", map2);
    }
}
