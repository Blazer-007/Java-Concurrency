package com.blazer.javaconcurrency.threadsafe.stack.util;

public class StackNode<T> {
    private final T val;
    private StackNode<T> prev;

    public StackNode(T val) {
        this.val = val;
        this.prev = null;
    }

    public T getVal() {
        return val;
    }

    public StackNode<T> getPrev() {
        return prev;
    }

    public void setPrev(StackNode<T> prev) {
        this.prev = prev;
    }
}
