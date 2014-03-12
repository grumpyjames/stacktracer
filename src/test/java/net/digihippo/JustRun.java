package net.digihippo;

public class JustRun implements Runnable {
    private final Runnable runnable;

    public JustRun(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        runnable.run();
    }
}
