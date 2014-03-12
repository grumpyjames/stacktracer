package net.digihippo.xform;

public abstract class ComposableTransformer implements StackTransformer {
    @Override
    public StackTransformer and(StackTransformer stackTransformer) {
        return new ComposedTransformer(this, stackTransformer);
    }
}
