package net.digihippo;

import org.junit.Test;

import static net.digihippo.CycleDetectors.after;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CyclicCallDetectorTest {
    private final StackCapture stackTraceCapturer = new StackCapture();

    @Test
    public void should_detect_no_cycle_in_acyclic_call_path() {
        assertAcyclic(generateRepeatedCallStackOfDepth(0));
    }

    @Test
    public void two_recursive_calls_to_the_same_class_are_defined_as_acyclic() {
        assertAcyclic(generateRepeatedCallStackOfDepth(1));
    }

    @Test
    public void calls_to_private_methods_are_not_acyclic() {
        assertAcyclic(generateStackTraceContainingPrivateMethodCall());
    }

    @Test
    public void corecursive_calls_are_cyclic() {
        assertCyclic(generateCoRecursiveStack());
    }

    @Test
    public void callbacks_to_anonymous_inner_classes_is_acyclic() {
        assertAcyclic(generateCallbackStack());
    }

    private void assertAcyclic(StackTraceElement[] elements) {
        assertTrue(after(CyclicCallDetectorTest.class).isAcyclic(elements));
    }

    private void assertCyclic(StackTraceElement[] elements) {
        assertFalse(after(CyclicCallDetectorTest.class).isAcyclic(elements));
    }

    private StackTraceElement[] generateCallbackStack() {
        final CallbackThing callbackThing = new CallbackThing(stackTraceCapturer, new RunsPassedInRunnable());
        callbackThing.run();

        return stackTraceCapturer.getStackTrace();
    }

    private StackTraceElement[] generateCoRecursiveStack() {
        final CoRecursiveA a = new CoRecursiveA(2, stackTraceCapturer);
        final CoRecursiveB b = new CoRecursiveB(2, stackTraceCapturer);

        a.run(0, b);
        return stackTraceCapturer.getStackTrace();
    }

    private StackTraceElement[] generateStackTraceContainingPrivateMethodCall() {
        final CallsPrivateMethodImmediately calls = new CallsPrivateMethodImmediately(stackTraceCapturer);
        calls.run();

        return stackTraceCapturer.getStackTrace();
    }

    private StackTraceElement[] generateRepeatedCallStackOfDepth(int cycleCount) {
        final NRecursiveCalls nRecursiveCalls = new NRecursiveCalls(stackTraceCapturer, cycleCount);
        nRecursiveCalls.foo();
        return stackTraceCapturer.getStackTrace();
    }


}
