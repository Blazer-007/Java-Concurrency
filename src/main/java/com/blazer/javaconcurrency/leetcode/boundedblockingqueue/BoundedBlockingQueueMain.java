package com.blazer.javaconcurrency.leetcode.boundedblockingqueue;

import java.util.Random;

public class BoundedBlockingQueueMain {
    public static void main(String[] args) {
//        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(3);
        BoundedBlockingQueueSemaphore boundedBlockingQueue = new BoundedBlockingQueueSemaphore(3);
        Random random = new Random();

        // 3 producers
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(100);
                        boundedBlockingQueue.enqueue(random.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        };

        // 3 consumers
        for (int i = 0; i< 3; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(100);
                        int num = boundedBlockingQueue.dequeue();
                        System.out.println(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
