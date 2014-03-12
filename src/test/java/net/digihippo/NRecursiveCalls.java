package net.digihippo;

class NRecursiveCalls {
    private int callCount = 0;
    private Runnable runnable;
    private int callLimit;

    public NRecursiveCalls(Runnable runnable, int cycleCount) {
        this.runnable = runnable;
        this.callLimit = cycleCount;
    }

    public void foo()
    {
        if (callCount > callLimit) {
            runnable.run();
        }
        else {
            callCount++;
            foo();
        }
    }
}
