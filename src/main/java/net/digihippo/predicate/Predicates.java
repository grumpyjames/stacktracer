package net.digihippo.predicate;

public final class Predicates {
    public static Predicate klass(Class<?> klass) { return new ClassPredicate(klass); }

    private Predicates() {}
}
