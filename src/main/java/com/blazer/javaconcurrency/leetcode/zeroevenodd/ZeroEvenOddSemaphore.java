package com.blazer.javaconcurrency.leetcode.zeroevenodd;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOddSemaphore {
    private int n;

    private final Semaphore zero;
    private final Semaphore even;
    private final Semaphore odd;
    public ZeroEvenOddSemaphore(int n) {
        this.n = n;
        this.zero = new Semaphore(1);
        this.even = new Semaphore(0);
        this.odd = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1 ; i <= n ; i++) {
            zero.acquire();
            printNumber.accept(0);
            if (i % 2 == 0)
                even.release();
            else
                odd.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int num = 2 ; num <= n ; num += 2) {
            even.acquire();
            printNumber.accept(num);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int num = 1 ; num <= n ; num += 2) {
            odd.acquire();
            printNumber.accept(num);
            zero.release();
        }
    }
}
