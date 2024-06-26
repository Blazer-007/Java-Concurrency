package com.blazer.javaconcurrency.scheduledexecutorservice;


import java.util.concurrent.TimeUnit;

public interface ScheduledExecutorService {
    /**
     * Creates and executes a one-shot action that becomes enabled after the given delay.
     */
    void schedule(Runnable command, long delay, TimeUnit unit);

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given period; that is executions will commence after initialDelay then
     * initialDelay+period, then initialDelay + 2 * period, and so on.
     */
    void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given delay between the termination of one execution and the commencement of the next.
     */
    void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit);
}
