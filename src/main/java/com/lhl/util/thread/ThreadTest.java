package com.lhl.util.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 子线程运行执行 10 次后，主线程再运行 5 次。这样交替执行三遍
 * Created by lihongli on 2019/4/12
 */
public class ThreadTest {

    private static Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    @Test
    public void test() {
        Task task = new Task();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                task.subMethod(i);
            }
        }).start();

        for (int i = 0; i < 3; i++) {
            task.mainMethod(i);
        }
    }

    class Task {
        private boolean flag = true;

        public synchronized void mainMethod(int no) {
            while (flag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    logger.error(" ", e);
                    Thread.currentThread().interrupt();
                }
            }
            int i = 0;
            while (i < 5) {
                logger.info("{}  : main Thread running loop count -- {} {}", Thread.currentThread().getName(), i, no);
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    logger.error(" ", e);
                    Thread.currentThread().interrupt();
                }
                i++;
            }
            flag = true;
            notifyAll();
        }

        public synchronized void subMethod(int no) {
            while (!flag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    logger.error(" ", e);
                    Thread.currentThread().interrupt();
                }
            }
            int i = 0;
            while (i < 10) {
                logger.info("{}  : sub Thread running loop count -- {} {}", Thread.currentThread().getName(), i, no);
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    logger.error(" ", e);
                    Thread.currentThread().interrupt();
                }
                i++;
            }
            flag = false;
            notifyAll();
        }
    }
}
