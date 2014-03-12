package net.digihippo;

public class CallbackThing implements Runnable {
    private final Runnable stackTraceCapturer;
    private final RunsPassedInRunnable runsPassedInRunnable;

    public CallbackThing(final Runnable stackTraceCapturer, RunsPassedInRunnable runsPassedInRunnable) {
        this.stackTraceCapturer = stackTraceCapturer;
        this.runsPassedInRunnable = runsPassedInRunnable;
    }

    @Override
    public void run() {
        runsPassedInRunnable.run(new Runnable() {
            @Override
            public void run() {
                stackTraceCapturer.run();
            }
        });
    }
}
