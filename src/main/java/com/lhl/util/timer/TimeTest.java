package com.lhl.util.timer;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 模拟双重定时器
 * 使用定时器,间隔 4 秒执行一次，再间隔 2 秒执行一次，以此类推执行。
 * Created by lihongli on 2019/4/12
 */
public class TimeTest {

    private static volatile int count = 0;

    @Test
    public void test() {
        Timer timer = new Timer();
        timer.schedule(new Task(), 2000 + 2000 * count);

        // 不让主线程死掉
        try {
            Thread.sleep(1000 * 60 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Task extends TimerTask {
        @Override
        public void run() {
            count = (count + 1) % 2;
            System.out.println("Boob boom" + count);
            new Timer().schedule(new Task(), 2000 + 2000 * count);
        }
    }
}
