package com.blazer.javaconcurrency.extras.lockcounter;

public class LockCounterMain {
    public static void main(String[] args) {
        System.out.println("Checking Reentrant Lock Counter");
        checkLock(new ReentrantLockCounter());
        System.out.println("Finished Reentrant Lock Counter");

        System.out.println("\nChecking Synchronized Lock Counter");
        checkLock(new SynchronizedLockCounter());
        System.out.println("Finished Synchronized Lock Counter");

        System.out.println("\nChecking Optimistic Lock Counter");
        checkLock(new OptimisticLockCounter());
        System.out.println("Finished Optimistic Lock Counter");
    }

    private static void checkLock(LockCounter lockCounter) {
        System.out.println(lockCounter.getCount());

        Thread thread1 = new Thread(() -> {
            for (int i = 0 ; i < 100 ; i++) {
                lockCounter.inc();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0 ; i < 100 ; i++) {
                lockCounter.inc();
            }
        });

        thread1.start();
        thread2.start();

        // Wsit for threads to finish work
        sleep(3000);
        System.out.println(lockCounter.getCount());
    }

    private static void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
