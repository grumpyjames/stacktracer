package net.digihippo;

public final class CycleDetectors {
    public static CycleDetector cycleDetector() {
        return new FoldBasedCycleDetector();
    }

    private CycleDetectors() {}
}
