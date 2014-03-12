package net.digihippo;

import net.digihippo.xform.After;

public final class CycleDetectors {

    private CycleDetectors() {}

    public static CycleDetector after(Class<?> klass) {
        return new ConfigurableCycleDetector(new After(klass));
    }
}
