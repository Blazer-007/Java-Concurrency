package com.blazer.javaconcurrency.leetcode.fizzbuzz;

/**
 * Leetcode - FizzBuzz Multi threaded
 * Link - https://leetcode.com/problems/fizz-buzz-multithreaded/description/
 */
public class FizzBuzzMain {
    public static void main(String[] args) throws InterruptedException {
        FizzBuzzSynchronized data = new FizzBuzzSynchronized(15);

        Thread thread1 = new Thread(() -> {
            try {
                data.fizz(() -> System.out.print("fizz"));
            } catch (Exception ignored) {
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                data.buzz(() -> System.out.print("buzz"));
            } catch (Exception ignored) {
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                data.fizzbuzz(() -> System.out.print("fizzbuzz"));
            } catch (Exception ignored) {
            }
        });
        Thread thread4 = new Thread(() -> {
            try {
                data.number(value -> System.out.print("" + value));
            } catch (Exception ignored) {
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

    }
}
