package net.digihippo;

class JustRun {
    private final Runnable runnable;

    JustRun(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        runnable.run();
    }
}
