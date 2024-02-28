package com.blazer.javaconcurrency.leetcode.fizzbuzz;

import java.util.function.IntConsumer;

public class FizzBuzzSynchronized {
    private int n;

    private int counter = 1;

    public FizzBuzzSynchronized(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized(this) {
            while (counter <= n) {
                if (counter % 3 == 0 && counter % 5 != 0) {
                    printFizz.run();
                    counter++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized(this) {
            while (counter <= n) {
                if (counter % 3 != 0 && counter % 5 == 0) {
                    printBuzz.run();
                    counter++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized(this) {
            while (counter <= n) {
                if (counter % 3 == 0 && counter % 5 == 0) {
                    printFizzBuzz.run();
                    counter++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized(this) {
            while (counter <= n) {
                if (counter % 3 != 0 && counter % 5 != 0) {
                    printNumber.accept(counter);
                    counter++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }
}
