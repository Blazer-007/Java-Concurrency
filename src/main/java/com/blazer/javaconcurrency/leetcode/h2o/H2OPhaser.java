package com.blazer.javaconcurrency.leetcode.h2o;

import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

/**
 * Check this link - https://leetcode.com/problems/building-h2o/solutions/342202/barrier-is-barrier-and-a-lot-of-java-solutions-in-discussion-are-wrong-by-test/
 */
public class H2OPhaser {
    private Semaphore semO;
    private Semaphore semH;
    private Phaser phaser;
    public H2OPhaser() {
        semO = new Semaphore(1);
        semH = new Semaphore(2);
        phaser = new Phaser(3);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semH.acquire();
        releaseHydrogen.run();
        phaser.arriveAndAwaitAdvance();
        semH.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semO.acquire();
        releaseOxygen.run();
        phaser.arriveAndAwaitAdvance();
        semO.release();
    }
}