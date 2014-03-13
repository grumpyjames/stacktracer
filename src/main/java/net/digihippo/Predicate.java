package net.digihippo;

public interface Predicate {
    boolean matches(StackTraceElement stackTraceElement);

    String description();
}
