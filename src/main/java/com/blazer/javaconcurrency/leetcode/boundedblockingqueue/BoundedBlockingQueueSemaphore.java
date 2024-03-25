package com.blazer.javaconcurrency.leetcode.boundedblockingqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class BoundedBlockingQueueSemaphore {

    private Semaphore canAdd, canRemove, mutex;
    private Deque<Integer> deque;
    public BoundedBlockingQueueSemaphore(int capacity) {
        this.canAdd = new Semaphore(capacity);
        this.canRemove = new Semaphore(0);
        this.mutex = new Semaphore(1); // This is used so that only one thread can either add or remove at single point of time
        // This mutex semaphore can be removed if we use thread safe deque
        this.deque = new LinkedList<>();

    }

    public void enqueue(int element) throws InterruptedException {
        canAdd.acquire();
        mutex.acquire();
        deque.addLast(element);
        mutex.release();
        canRemove.release();
    }

    public int dequeue() throws InterruptedException {
        canRemove.acquire();
        mutex.acquire();
        int ele = deque.pollFirst();
        mutex.release();
        canAdd.release();
        return ele;
    }

    public int size() {
        return deque.size();
    }
}
