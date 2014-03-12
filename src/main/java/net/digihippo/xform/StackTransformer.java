package net.digihippo.xform;

import java.util.List;

public interface StackTransformer {
    List<StackTraceElement> apply(List<StackTraceElement> stackTrace);
}
