package com.blazer.javaconcurrency.leetcode.h2o;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

class H2OBarrier {

    private final Semaphore hydro, oxy;

    private final CyclicBarrier barrier;

    public H2OBarrier() {
        this.hydro = new Semaphore(2);
        this.oxy = new Semaphore(1);
        this.barrier = new CyclicBarrier(3);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydro.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {

        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        hydro.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxy.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {

        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        oxy.release();
    }
}
