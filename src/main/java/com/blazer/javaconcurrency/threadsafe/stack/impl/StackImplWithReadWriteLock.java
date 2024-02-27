package com.blazer.javaconcurrency.threadsafe.stack.impl;

import com.blazer.javaconcurrency.threadsafe.stack.Stack;
import com.blazer.javaconcurrency.threadsafe.stack.util.StackNode;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StackImplWithReadWriteLock<T> implements Stack<T> {

    StackNode<T> head;

    private final ReadWriteLock lock;

    public StackImplWithReadWriteLock() {
        this.head = null;
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public void push(T val) {
        lock.writeLock().lock();
        try {
            StackNode<T> node = new StackNode<>(val);
            node.setPrev(head);
            head = node;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void pop() {
        lock.writeLock().lock();
        try {
            if (head != null) {
                head = head.getPrev();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public T top() {
        lock.readLock().lock();
        try {
            return head == null ? null : head.getVal();
        } finally {
            lock.readLock().unlock();
        }
    }
}
