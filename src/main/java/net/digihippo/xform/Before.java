package net.digihippo.xform;

import java.util.LinkedList;
import java.util.List;

public class Before extends ComposableTransformer {
    private final Class<?> klass;

    public Before(Class<?> klass) {
        this.klass = klass;
    }

    @Override
    public List<StackTraceElement> apply(List<StackTraceElement> stackTrace) {
        final LinkedList<StackTraceElement> result = new LinkedList<StackTraceElement>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.getClassName().equals(klass.getName())) {
                result.clear();
            } else {
                result.add(stackTraceElement);
            }
        }
        return result;
    }

}
