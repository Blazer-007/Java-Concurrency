package com.blazer.javaconcurrency.lockcounter;


import java.util.concurrent.atomic.AtomicLong;

public class OptimisticLockCounter implements LockCounter{

    private AtomicLong count = new AtomicLong();

    public void inc() {
        boolean incSuccessful = false;
        while (!incSuccessful) {
            long curValue = this.count.get();
            long newValue = curValue + 1;

            // This will increment and return true only if current value of count is equal to curValue
            // essentially making it thread safe
            incSuccessful = this.count.compareAndSet(curValue, newValue);
        }
    }

    public long getCount() {
        return this.count.get();
    }
}
