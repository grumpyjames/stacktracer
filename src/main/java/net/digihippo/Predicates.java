package net.digihippo;

public final class Predicates {
    public static Predicate klass(Class<?> klass) { return new ClassPredicate(klass); }

    private Predicates() {}
}
