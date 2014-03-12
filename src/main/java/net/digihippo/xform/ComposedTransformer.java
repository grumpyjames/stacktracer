package net.digihippo.xform;

import java.util.List;

final class ComposedTransformer extends ComposableTransformer {
    private final StackTransformer first;
    private final StackTransformer second;

    public ComposedTransformer(StackTransformer first, StackTransformer second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public List<StackTraceElement> apply(List<StackTraceElement> stackTrace) {
        return second.apply(first.apply(stackTrace));
    }
}
