package net.digihippo.xform;

public class StackTransformers {
    public static StackTransformer after(final Class<?> klass) { return new After(klass); }
    public static StackTransformer excluding(final Class<?> klass) { return new Excluding(klass); }
}
