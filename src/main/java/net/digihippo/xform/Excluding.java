package net.digihippo.xform;

import java.util.LinkedList;
import java.util.List;

final class Excluding extends ComposableTransformer {
    private final Class<?> klass;

    public Excluding(Class<?> klass) {
        this.klass = klass;
    }

    @Override
    public List<StackTraceElement> apply(List<StackTraceElement> stackTrace) {
        final List<StackTraceElement> result = new LinkedList<StackTraceElement>();
        for (final StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.getClassName().equals(klass.getName())) {
                result.add(stackTraceElement);
            }
        }
        return result;
    }
}
