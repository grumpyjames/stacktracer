package net.digihippo;

public class CoRecursiveA {

    private final int maxCalls;
    private final Runnable runnable;

    public CoRecursiveA(int maxCalls, Runnable runnable) {
        this.maxCalls = maxCalls;
        this.runnable = runnable;
    }

    public void run(int callCount, CoRecursiveB coRecursiveB)
    {
        if (callCount == maxCalls) {
            runnable.run();
        } else {
            coRecursiveB.run(++callCount, this);
        }
    }
}
