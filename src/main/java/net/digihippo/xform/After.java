package net.digihippo.xform;

import java.util.Arrays;

public class After implements StackTransformer {
    private final Class<?> klass;

    public After(Class<?> klass) {
        this.klass = klass;
    }

    @Override
    public StackTraceElement[] apply(StackTraceElement[] stackTrace) {
        final int trimIndex = indexOfFirstOccurrence(klass, stackTrace);

        return Arrays.copyOfRange(stackTrace, 0, trimIndex);
    }

    private static int indexOfFirstOccurrence(Class<?> klass, StackTraceElement[] stackTrace) {
        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.getClassName().equals(klass.getName()))
            {
                return i;
            }
        }
        throw new IllegalArgumentException("Limiting class " + klass + " not found in " + Arrays.toString(stackTrace));
    }
}
