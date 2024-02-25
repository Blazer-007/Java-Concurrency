package com.blazer.javaconcurrency.extras.lockcounter;

public interface LockCounter {
    void inc();
    long getCount();
}
