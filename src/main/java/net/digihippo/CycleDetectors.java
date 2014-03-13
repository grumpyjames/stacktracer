package net.digihippo;

import net.digihippo.xform.StackTransformer;

public final class CycleDetectors {
    public static CycleDetector cycleDetector(final StackTransformer stackTransformer) {
        return new ConfigurableCycleDetector(stackTransformer);
    }

    private CycleDetectors() {}
}
