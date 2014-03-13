package net.digihippo.xform;

import net.digihippo.predicate.Predicate;

import java.util.LinkedList;
import java.util.List;

final class Excluding extends ComposableTransformer {
    private final Predicate predicate;

    public Excluding(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public List<StackTraceElement> apply(List<StackTraceElement> stackTrace) {
        final List<StackTraceElement> result = new LinkedList<StackTraceElement>();
        for (final StackTraceElement stackTraceElement : stackTrace) {
            if (!predicate.matches(stackTraceElement)) {
                result.add(stackTraceElement);
            }
        }
        return result;
    }
}
