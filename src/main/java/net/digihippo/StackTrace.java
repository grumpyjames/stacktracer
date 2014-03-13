package net.digihippo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class StackTrace {
    /**
     * @return A List of StackTraceElements for the current thread, stopping at this class
     */
    public static List<StackTraceElement> getStackTrace() {
        final StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        final List<StackTraceElement> result = new ArrayList<StackTraceElement>(elements.length - 2);
        // skip the element for this class, and for thread itself...
        result.addAll(Arrays.asList(elements).subList(2, elements.length));

        return result;
    }

    private StackTrace() {}
}
