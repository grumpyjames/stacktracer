package net.digihippo;

import java.util.Iterator;
import java.util.Set;

public final class CycleReport {
    private final Set<String> classNamesThatCycled;

    public CycleReport(Set<String> classNamesThatCycled) {
        this.classNamesThatCycled = classNamesThatCycled;
    }

    public boolean isAcyclic() {
        return classNamesThatCycled.isEmpty();
    }

    public Iterable<String> classNamesThatCycled() {
        return unmodifiableIterable(classNamesThatCycled);
    }

    private Iterable<String> unmodifiableIterable(final Iterable<String> iterable) {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                final Iterator<String> iterator = iterable.iterator();
                return new Iterator<String>() {
                    @Override
                    public boolean hasNext() { return iterator.hasNext(); }

                    @Override
                    public String next() { return iterator.next(); }

                    @Override
                    public void remove() { throw new UnsupportedOperationException("You deserve this"); }
                };
            }

            @Override
            public String toString() {
                return iterable.toString();
            }
        };
    }
}
