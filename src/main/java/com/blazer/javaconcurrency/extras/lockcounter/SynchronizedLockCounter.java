package com.blazer.javaconcurrency.extras.lockcounter;

public class SynchronizedLockCounter implements LockCounter{
    private long count = 0;

    public synchronized void inc() {
        this.count++;
    }

    public synchronized long getCount() {
        return this.count;
    }
}
