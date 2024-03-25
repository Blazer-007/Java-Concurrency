package com.blazer.javaconcurrency.leetcode.boundedblockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class BoundedBlockingQueue {

    private final int capacity;

    private AtomicInteger curCapacity;

    private Lock lock;

    private Condition full;
    private Condition empty;

    private List<Integer> list;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.curCapacity = new AtomicInteger(0);
        this.lock = new ReentrantLock();
        this.list = new ArrayList<>();
        this.full = lock.newCondition();
        this.empty = lock.newCondition();
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (curCapacity.get() >= this.capacity) {
                full.await();
            }
            list.add(element);
            curCapacity.incrementAndGet();
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (curCapacity.get() <= 0) {
                empty.await();
            }
            curCapacity.decrementAndGet();
            full.signalAll();
            return list.remove(0);
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return this.curCapacity.get();
    }
}
