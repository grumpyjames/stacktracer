package net.digihippo;

public class CoRecursiveB {
    private final int maxCalls;
    private final Runnable runnable;

    public CoRecursiveB(int maxCalls, Runnable runnable) {
        this.maxCalls = maxCalls;
        this.runnable = runnable;
    }

    public void run(int callCount, CoRecursiveA coRecursiveA) {
        if (callCount == maxCalls) {
            runnable.run();
        } else {
            coRecursiveA.run(++callCount, this);
        }
    }
}
