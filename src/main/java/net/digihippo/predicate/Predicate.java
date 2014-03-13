package net.digihippo.predicate;

public interface Predicate {
    boolean matches(StackTraceElement stackTraceElement);

    String description();
}
