package net.digihippo;


import net.digihippo.xform.StackTransformer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class StackTransformingCycleDetector implements CycleDetector {
    private final StackTransformer transform;

    public StackTransformingCycleDetector(StackTransformer transform) {
        this.transform = transform;
    }

    @Override
    public boolean isAcyclic(List<StackTraceElement> stackTrace) {
        return acyclic(transform.apply(stackTrace));
    }

    private interface F<A,B> {
        A f(A a, B b);
    }

    // Don't tell Tony Morris. He'd be very upset.
    public static <A, B> A horribleMutableFold(F<A, B> f, A initialValue, Iterable<B> bs) {
        A context = initialValue;
        for (B b : bs) {
            context = f.f(context, b);
        }
        return context;
    }

    private static final class FoldContext {
        private final Set<String> classesSeen;
        private final String currentClass;
        private final int cycleCount;

        private FoldContext() {
            classesSeen = new HashSet<String>();
            currentClass = null;
            cycleCount = 0;
        }

        public FoldContext(Set<String> classesSeen, String className, int cycleCount) {
            this.classesSeen = classesSeen;
            this.currentClass = className;
            this.cycleCount = cycleCount;
        }

        public FoldContext process(StackTraceElement stackTraceElement) {
            if (currentClass == null) {
                return new FoldContext(classesSeen, stackTraceElement.getClassName(), 0);
            } else if (stackTraceElement.getClassName().equals(currentClass)) {
                return this;
            } else {
                if (classesSeen.add(currentClass)) {
                    return new FoldContext(classesSeen, stackTraceElement.getClassName(), 0);
                } else {
                    return new FoldContext(classesSeen, stackTraceElement.getClassName(), 1);
                }
            }
        }

        public boolean acyclic() {
            return cycleCount == 0 && !classesSeen.contains(currentClass);
        }
    }

    private static final class FoldFn implements F<FoldContext, StackTraceElement> {
        @Override
        public FoldContext f(FoldContext foldContext, StackTraceElement stackTraceElement) {
            return foldContext.process(stackTraceElement);
        }
    }

    private boolean acyclic(List<StackTraceElement> elements) {
        FoldContext result = horribleMutableFold(new FoldFn(), new FoldContext(), elements);
        return result.acyclic();
    }
}
