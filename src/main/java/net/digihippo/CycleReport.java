package net.digihippo;

import java.util.Set;

public class CycleReport {
    private final Set<String> classNamesThatCycled;

    public CycleReport(Set<String> classNamesThatCycled) {
        this.classNamesThatCycled = classNamesThatCycled;
    }

    public boolean isAcyclic() {
        return classNamesThatCycled.isEmpty();
    }
}
