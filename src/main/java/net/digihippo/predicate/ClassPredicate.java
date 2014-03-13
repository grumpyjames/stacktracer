package net.digihippo.predicate;

class ClassPredicate implements Predicate {
    private final Class<?> klass;

    public ClassPredicate(Class<?> klass) {
        this.klass = klass;
    }

    @Override
    public boolean matches(StackTraceElement stackTraceElement) {
        return stackTraceElement.getClassName().equals(klass.getName());
    }

    @Override
    public String description() {
        return "class name == " + klass.getName();
    }
}
