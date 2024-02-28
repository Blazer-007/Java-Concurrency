package com.blazer.javaconcurrency.leetcode.zeroevenodd;

import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;

    private boolean zero = true;
    private boolean even = false;
    private boolean odd = true;


    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0 ; i < n ; i++) {
            synchronized (this) {
                while (!zero) {
                    this.wait();
                }
                printNumber.accept(0);
                zero = false;
                this.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int num = 2 ; num <= n ; num += 2) {
            synchronized (this) {
                while (zero || !even) {
                    this.wait();
                }
                printNumber.accept(num);
                even = false;
                odd = true;
                zero = true;
                this.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int num = 1 ; num <= n ; num += 2) {
            synchronized (this) {
                while (zero || !odd) {
                    this.wait();
                }
                printNumber.accept(num);
                odd = false;
                even = true;
                zero = true;
                this.notifyAll();
            }
        }
    }
}
