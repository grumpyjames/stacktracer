package net.digihippo.xform;

abstract class ComposableTransformer implements StackTransformer {
    @Override
    public StackTransformer and(StackTransformer stackTransformer) {
        return new ComposedTransformer(this, stackTransformer);
    }
}
