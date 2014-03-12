package net.digihippo;

public interface CycleDetector {
    boolean isAcyclic(StackTraceElement[] stackTrace);
}
