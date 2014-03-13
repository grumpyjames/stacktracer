package net.digihippo.xform;

import net.digihippo.predicate.Predicate;

import java.util.LinkedList;
import java.util.List;

final class After extends ComposableTransformer {
    private final Predicate predicate;

    After(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public List<StackTraceElement> apply(List<StackTraceElement> stackTrace) {
        final List<StackTraceElement> result = new LinkedList<StackTraceElement>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (predicate.matches(stackTraceElement)) {
                return result;
            }
            result.add(stackTraceElement);
        }

        throw new IllegalArgumentException("Element " + predicate.description() + " not found in trace " + stackTrace);
    }
}
