package com.blazer.javaconcurrency.threadsafe.stack.impl;

import com.blazer.javaconcurrency.threadsafe.stack.Stack;
import com.blazer.javaconcurrency.threadsafe.stack.util.StackNode;

public class SynchronizedStackImpl<T> implements Stack<T> {

    private StackNode<T> head;

    private final Object lock = new Object();

    public SynchronizedStackImpl() {
        this.head = null;
    }

    @Override
    public void push(T val) {
        synchronized (lock) {
            StackNode<T> node = new StackNode<T>(val);
            node.setPrev(head);
            head = node;
        }
    }

    @Override
    public void pop() {
        synchronized (lock) {
            if (head == null)
                return;
            head = head.getPrev();
        }
    }

    @Override
    public T top() {
        synchronized (lock) {
            return head == null ? null : head.getVal();
        }
    }
}
