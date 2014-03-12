package net.digihippo;

public final class CycleDetectors {

    private CycleDetectors() {}

    public static CycleDetector after(Class<?> klass) {
        return new ConfigurableCycleDetector(Bound.AFTER, klass);
    }
}
