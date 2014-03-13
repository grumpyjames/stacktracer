package net.digihippo;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FoldBasedCycleDetector implements CycleDetector {
    @Override
    public boolean isAcyclic(List<StackTraceElement> stackTrace) {
        return scanForCycles(stackTrace).isAcyclic();
    }

    @Override
    public CycleReport scanForCycles(List<StackTraceElement> stackTrace) {
        return horribleMutableFold(new FoldFn(), new FoldContext(), stackTrace).report();
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
        // oh for a functional Set :-(
        private final Set<String> classesSeen;
        private final Set<String> cycleClasses;
        private final String currentClass;

        private FoldContext() {
            classesSeen = new HashSet<String>();
            currentClass = null;
            cycleClasses = new HashSet<String>();
        }

        public FoldContext(Set<String> classesSeen, String className, Set<String> cycleClasses) {
            this.classesSeen = classesSeen;
            this.currentClass = className;
            this.cycleClasses = cycleClasses;
        }

        public FoldContext process(StackTraceElement stackTraceElement) {
            if (currentClass == null) {
                return new FoldContext(classesSeen, stackTraceElement.getClassName(), cycleClasses);
            } else if (stackTraceElement.getClassName().equals(currentClass)) {
                return this;
            } else {
                if (classesSeen.add(currentClass)) {
                    return new FoldContext(classesSeen, stackTraceElement.getClassName(), cycleClasses);
                } else {
                    cycleClasses.add(currentClass);
                    return new FoldContext(classesSeen, stackTraceElement.getClassName(), cycleClasses);
                }
            }
        }

        public CycleReport report() {
            if (!classesSeen.add(currentClass)) cycleClasses.add(currentClass);
            return new CycleReport(cycleClasses);
        }
    }

    private static final class FoldFn implements F<FoldContext, StackTraceElement> {
        @Override
        public FoldContext f(FoldContext foldContext, StackTraceElement stackTraceElement) {
            return foldContext.process(stackTraceElement);
        }
    }

}
