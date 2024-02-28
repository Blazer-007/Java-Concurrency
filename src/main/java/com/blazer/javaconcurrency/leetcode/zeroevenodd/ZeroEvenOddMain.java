package com.blazer.javaconcurrency.leetcode.zeroevenodd;

/**
 * Leetcode - Print Zero Even Odd
 * Link - https://leetcode.com/problems/print-zero-even-odd
 */
public class ZeroEvenOddMain {
    public static void main(String[] args) throws InterruptedException {
//        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(9);

        ZeroEvenOddSemaphore zeroEvenOdd = new ZeroEvenOddSemaphore(9);
        Thread thread1 = new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
