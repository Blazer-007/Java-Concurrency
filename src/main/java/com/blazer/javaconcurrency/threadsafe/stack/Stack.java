package com.blazer.javaconcurrency.threadsafe.stack;

public interface Stack<T> {
    void push(T val);
    void pop();
    T top();
}
