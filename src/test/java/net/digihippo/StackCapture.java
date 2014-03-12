package net.digihippo;

class StackCapture implements Runnable {
    private StackTraceElement[] stackTrace;

    @Override
    public void run() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();

        final StackTraceElement[] result = new StackTraceElement[trace.length - 2];
        System.arraycopy(trace, 1, result, 0, trace.length - 2);
        stackTrace = result;
    }

    StackTraceElement[] getStackTrace() {
        return stackTrace;
    }
}
