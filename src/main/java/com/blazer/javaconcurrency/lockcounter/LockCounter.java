package com.blazer.javaconcurrency.lockcounter;

public interface LockCounter {
    void inc();
    long getCount();
}
