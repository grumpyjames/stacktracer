package net.digihippo.xform;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

final class Start extends ComposableTransformer {
    private final Class<?> klass;

    public Start(Class<?> klass) {
        this.klass = klass;
    }

    @Override
    public List<StackTraceElement> apply(List<StackTraceElement> stackTrace) {
        final StackTraceElement[] elements = stackTrace.toArray(new StackTraceElement[stackTrace.size()]);
        final int newLength = elementsToCopy(elements);

        final LinkedList<StackTraceElement> result = new LinkedList<StackTraceElement>();
        result.addAll(Arrays.asList(elements).subList(0, newLength));

        return result;
    }

    private int elementsToCopy(StackTraceElement[] elements) {
        for (int i = elements.length - 1; i >= 0; --i) {
            final StackTraceElement element = elements[i];
            if (element.getClassName().equals(klass.getName())) {
                return i + 1;
            }
        }

        throw new IllegalArgumentException("No element featuring class " + klass + " found in trace "
                                           + Arrays.toString(elements));
    }
}
