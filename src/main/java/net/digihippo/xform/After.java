package net.digihippo.xform;

import java.util.LinkedList;
import java.util.List;

final class After extends ComposableTransformer {
    private final Class<?> klass;

    After(Class<?> klass) {
        this.klass = klass;
    }

    @Override
    public List<StackTraceElement> apply(List<StackTraceElement> stackTrace) {
        final List<StackTraceElement> result = new LinkedList<StackTraceElement>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.getClassName().equals(klass.getName())) {
                return result;
            }
            result.add(stackTraceElement);
        }

        throw new IllegalArgumentException("Limiting class " + klass + " not found in " + stackTrace);
    }
}
