package net.digihippo.xform;

import net.digihippo.predicate.Predicate;

import java.util.LinkedList;
import java.util.List;

public class Before extends ComposableTransformer {
    private final Predicate predicate;

    public Before(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public List<StackTraceElement> apply(List<StackTraceElement> stackTrace) {
        final LinkedList<StackTraceElement> result = new LinkedList<StackTraceElement>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (predicate.matches(stackTraceElement)) {
                result.clear();
            } else {
                result.add(stackTraceElement);
            }
        }
        return result;
    }

}
