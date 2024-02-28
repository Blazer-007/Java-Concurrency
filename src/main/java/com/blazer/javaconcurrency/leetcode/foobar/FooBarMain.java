package com.blazer.javaconcurrency.leetcode.foobar;

/**
 * Leetcode - FooBar
 * Link - https://leetcode.com/problems/print-foobar-alternately/
 */
public class FooBarMain {
    public static void main(String[] args) throws InterruptedException {
//        FooBar fooBar = new FooBar(10);

        FooBarSynchronized fooBar = new FooBarSynchronized(5);
        Thread foo = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("Foo"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread bar = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar\n"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        foo.start();
        bar.start();
        foo.join();
        bar.join();
    }
}
