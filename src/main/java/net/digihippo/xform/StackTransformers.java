package net.digihippo.xform;

public class StackTransformers {
    public static StackTransformer after(final Class<?> klass) { return new After(klass); }
}
