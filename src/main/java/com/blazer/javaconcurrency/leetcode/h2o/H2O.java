package com.blazer.javaconcurrency.leetcode.h2o;

import java.util.concurrent.Semaphore;

/**
 * This solution though gives correct output but is not 100% correct.
 * Check phaser implementation and Leetcode link for more explanation.
 *
 * Problem Link - https://leetcode.com/problems/building-h2o/description/
 */
public class H2O {

    private final Semaphore hydro, oxy;

    public H2O() {
        /* This initialization failed at test case 9
        this.hydro = new Semaphore(0);
        this.oxy = new Semaphore(1);
         */
        this.hydro = new Semaphore(2);
        this.oxy = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydro.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        if (hydro.availablePermits() == 0)
            oxy.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxy.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hydro.release(2);
    }
}
