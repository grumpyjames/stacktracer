package net.digihippo;

import java.util.Arrays;

public enum Bound {
    AFTER {
        @Override
        public StackTraceElement[] trim(Class<?> klass, StackTraceElement[] stackTrace) {
            final int trimIndex = indexOfFirstOccurrence(klass, stackTrace);

            return Arrays.copyOfRange(stackTrace, 0, trimIndex);
        }
    };

    private static int indexOfFirstOccurrence(Class<?> klass, StackTraceElement[] stackTrace) {
        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.getClassName().equals(klass.getName()))
            {
                return i;
            }
        }
        throw new IllegalArgumentException("Bound class " + klass + " not found in " + Arrays.toString(stackTrace));
    }

    public abstract StackTraceElement[] trim(Class<?> klass, StackTraceElement[] stackTrace);
}
