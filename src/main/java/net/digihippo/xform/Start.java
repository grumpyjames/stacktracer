package net.digihippo.xform;

import net.digihippo.predicate.Predicate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

final class Start extends ComposableTransformer {
    private final Predicate predicate;

    public Start(Predicate predicate) {
        this.predicate = predicate;
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
            if (predicate.matches(element)) {
                return i + 1;
            }
        }

        throw new IllegalArgumentException("Element " + predicate.description() + " not found in trace "
                                           + Arrays.toString(elements));
    }
}
