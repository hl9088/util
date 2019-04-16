package com.lhl.util.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lihongli on 2019/4/16
 */
public class TaskQueue {

    private static Logger logger = LoggerFactory.getLogger(TaskQueue.class);

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    public BlockingQueue<String> getQueue() {
        return queue;
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(new taskRunnable(queue));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (2 > 1) {
                    try {
                        logger.info("take size--tb {}", queue.size());
                        String take = queue.take();
                        logger.info("take size--ta {}", queue.size());
                        logger.info("take--- {}", take);
                    } catch (InterruptedException e) {
                        logger.error("", e);
                    }
                }
            }
        }).start();
    }
}

class taskRunnable implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(taskRunnable.class);

    private BlockingQueue<String> queue;

    private static volatile int num = 0;

    public taskRunnable(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (2 > 1) {
            try {
                logger.info("线程 put--- {}", num);
                logger.info("线程 put size--b {}", queue.size());
                queue.put(num++ + "");
                logger.info("线程 put size--a {}", queue.size());
            } catch (InterruptedException e) {
                logger.error("", e);
            }
        }
    }
}
