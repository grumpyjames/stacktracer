package net.digihippo;

import java.util.List;

public interface CycleDetector {
    boolean isAcyclic(List<StackTraceElement> stackTrace);
}
