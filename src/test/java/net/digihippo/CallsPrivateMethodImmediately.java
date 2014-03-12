package net.digihippo;

public class CallsPrivateMethodImmediately {
    private final Runnable runnable;

    public CallsPrivateMethodImmediately(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        doRun();
    }

    private void doRun() {
        runnable.run();
    }
}
