package net.digihippo;

import net.digihippo.xform.StackTransformers;

public final class CycleDetectors {

    private CycleDetectors() {}

    public static CycleDetector after(Class<?> klass) {
        return new ConfigurableCycleDetector(StackTransformers.after(klass));
    }
}
