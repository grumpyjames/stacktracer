package net.digihippo.xform;

public interface StackTransformer {
    StackTraceElement[] apply(StackTraceElement[] stackTrace);
}
