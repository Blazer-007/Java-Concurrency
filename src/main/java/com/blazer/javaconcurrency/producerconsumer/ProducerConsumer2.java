package com.blazer.javaconcurrency.producerconsumer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Producer Consumer Example-2 (Fixed Queue Size)
 * Producer produces upto K numbers using multiple threads
 * Consumer consumes and print those numbers using multiple threads
 */
public class ProducerConsumer2 {
    static int K = 15;
    static Deque<Integer> deque = new ArrayDeque<>(5);

    static int maxDequeSize = 5;
    static int curDequeSize = 0;

    static class Producer implements Runnable {

        private int prodCount;

        public Producer() {
            this.prodCount = 0;
        }

        @Override
        public void run() {
            System.out.println("Producing Started");
            while (prodCount < K) {
                synchronized (deque) {
                    while (curDequeSize == maxDequeSize) {
                        try {
                            deque.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (prodCount < K) {
                        deque.addLast((int) (Math.random()*100));
                        prodCount++;
                        curDequeSize++;
                        deque.notifyAll();
                    }
                }
            }
            System.out.println("Producing Completed");
        }
    }

    static class Consumer implements Runnable {

        private int consCount;

        public Consumer() {
            this.consCount = 0;
        }

        @Override
        public void run() {
            System.out.println("Consuming Started");
            while (consCount < K) {
                synchronized (deque) {
                    while (curDequeSize == 0 && consCount < K) {
                        try {
                            deque.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (consCount >= K) {
                        break;
                    }
                    System.out.println("Element Consumed : " + deque.pollFirst());
                    consCount++;
                    curDequeSize--;
                    deque.notifyAll();
                }
            }
            System.out.println("Consuming Completed");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Producer producer = new Producer();
        Thread producerThread1 = new Thread(producer);
        Thread producerThread2 = new Thread(producer);
        Thread producerThread3 = new Thread(producer);

        Consumer consumer = new Consumer();
        Thread consumerThread1 = new Thread(consumer);
        Thread consumerThread2 = new Thread(consumer);
        Thread consumerThread3 = new Thread(consumer);

        producerThread1.start();
        producerThread2.start();
        producerThread3.start();

        Thread.sleep(2000);

        consumerThread1.start();
        consumerThread2.start();
        consumerThread3.start();

        producerThread1.join();
        producerThread2.join();
        producerThread3.join();

        consumerThread1.join();
        consumerThread2.join();
        consumerThread3.join();
    }
}