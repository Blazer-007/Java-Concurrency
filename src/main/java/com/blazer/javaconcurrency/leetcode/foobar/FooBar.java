package com.blazer.javaconcurrency.leetcode.foobar;

import java.util.concurrent.Semaphore;

class FooBar {
    private int n;

    private final Semaphore foo;
    private final Semaphore bar;

    public FooBar(int n) {
        this.n = n;
        this.foo = new Semaphore(1); // Giving one permit to Foo semaphore to start printing first
        this.bar = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire(); // it reduces permit to zero
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }
}
