package net.digihippo;

import java.util.Arrays;
import java.util.List;

class StackCapture implements Runnable {
    private List<StackTraceElement> stackTrace;

    @Override
    public void run() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();

        final StackTraceElement[] result = new StackTraceElement[trace.length - 2];
        System.arraycopy(trace, 1, result, 0, trace.length - 2);
        stackTrace = Arrays.asList(result);
    }

    List<StackTraceElement> getStackTrace() {
        return stackTrace;
    }
}
