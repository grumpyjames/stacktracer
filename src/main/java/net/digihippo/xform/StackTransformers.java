package net.digihippo.xform;

import net.digihippo.predicate.Predicate;

public class StackTransformers {
    public static StackTransformer before(Predicate predicate) { return new Before(predicate); }
    public static StackTransformer after(Predicate predicate) { return new After(predicate); }
    public static StackTransformer excluding(Predicate predicate) { return new Excluding(predicate); }
    public static StackTransformer start(Predicate predicate) { return new Start(predicate); }
}
