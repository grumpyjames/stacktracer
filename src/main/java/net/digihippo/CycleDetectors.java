package net.digihippo;

import java.util.List;

public final class CycleDetectors {
    public static CycleReport scanForCycles(final List<StackTraceElement> elements) {
        return FoldBasedCycleDetector.scanForCycles(elements);
    }

    private CycleDetectors() {}
}
