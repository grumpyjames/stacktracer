package net.digihippo.xform;

class JustRun implements Runnable {
    private final Runnable runnable;

    JustRun(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        runnable.run();
    }
}
