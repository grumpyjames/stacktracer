package net.digihippo;

import net.digihippo.xform.StackTransformer;
import org.junit.Test;

import java.util.List;

import static net.digihippo.CycleDetectors.scanForCycles;
import static net.digihippo.predicate.Predicates.klass;
import static net.digihippo.xform.StackTransformers.*;
import static org.junit.Assert.*;

public class CyclicCallDetectorTest {
    private final StackCapture stackTraceCapturer = new StackCapture();

    @Test
    public void should_detect_no_cycle_in_acyclic_call_path() {
        assertAcyclic(after(klass(CyclicCallDetectorTest.class)), generateRepeatedCallStackOfDepth(0));
    }

    @Test
    public void two_recursive_calls_to_the_same_class_are_defined_as_acyclic() {
        assertAcyclic(after(klass(CyclicCallDetectorTest.class)), generateRepeatedCallStackOfDepth(1));
    }

    @Test
    public void calls_to_private_methods_are_not_acyclic() {
        assertAcyclic(after(klass(CyclicCallDetectorTest.class)), generateStackTraceContainingPrivateMethodCall());
    }

    @Test
    public void corecursive_calls_are_cyclic() {
        assertCyclic(after(klass(CyclicCallDetectorTest.class)), generateCoRecursiveStack());
    }

    @Test
    public void excluding_the_cyclic_class_leaves_an_acyclic_result() {
        assertAcyclic(after(klass(CyclicCallDetectorTest.class)).and(excluding(klass(CoRecursiveA.class))),
                      generateCoRecursiveStack());
    }

    @Test
    public void starting_at_a_suitable_point_leaves_an_acyclic_result() {
        assertAcyclic(start(klass(CoRecursiveB.class)), generateCoRecursiveStack());
    }

    @Test
    public void callbacks_to_anonymous_inner_classes_is_acyclic() {
        assertAcyclic(after(klass(CyclicCallDetectorTest.class)), generateCallbackStack());
    }

    @Test
    public void terminating_stack_before_corecursion_occurs_is_acyclic() {
        assertAcyclic(before(klass(CoRecursiveB.class)).and(after(klass(CyclicCallDetectorTest.class))),
                      generateCoRecursiveStack());
    }

    @Test
    public void start_plays_well_with_before() {
        assertAcyclic(start(klass(CoRecursiveA.class)).and(before(klass(CoRecursiveB.class))),
                      generateCoRecursiveStack());
    }

    @Test
    public void cycle_after_a_while_without_cycle_is_cyclic() {
        assertCyclic(start(klass(JustRun.class)), generateOkStackWithCycleAtTheEnd());
    }

    @Test
    public void cycle_report_contains_all_cyclic_classes() {
        final List<StackTraceElement> elements = generateDoublyCoRecursiveStack();
        assertReportContains(scanForCycles(elements), CoRecursiveA.class, CoRecursiveB.class);
    }

    private void assertReportContains(CycleReport cycleReport, Class<?>... klasses) {
        for (Class<?> klass : klasses) {
            boolean found = false;
            for (String klassName : cycleReport.classNamesThatCycled()) {
                if (klassName.equals(klass.getName())) {
                    found = true;
                }
            }
            if (!found) {
                fail("Class " + klass + " not found in " + cycleReport.classNamesThatCycled());
            }
        }
    }

    private void assertAcyclic(StackTransformer stackTransformer, List<StackTraceElement> elements) {
        assertTrue(scanForCycles(stackTransformer.apply(elements)).isAcyclic());
    }

    private void assertCyclic(StackTransformer stackTransformer, List<StackTraceElement> elements) {
        assertFalse(scanForCycles(stackTransformer.apply(elements)).isAcyclic());
    }

    private List<StackTraceElement> generateCallbackStack() {
        final CallbackThing callbackThing = new CallbackThing(stackTraceCapturer, new RunsPassedInRunnable());
        callbackThing.run();

        return stackTraceCapturer.getStackTrace();
    }

    private List<StackTraceElement> generateOkStackWithCycleAtTheEnd() {
        final CoRecursiveA a = new CoRecursiveA(2, stackTraceCapturer);
        final CoRecursiveB b = new CoRecursiveB(2, stackTraceCapturer);
        new JustRun(new RunCoRecursive(0, a, b)).run();

        return stackTraceCapturer.getStackTrace();
    }

    private List<StackTraceElement> generateDoublyCoRecursiveStack() {
        final CoRecursiveA a = new CoRecursiveA(3, stackTraceCapturer);
        final CoRecursiveB b = new CoRecursiveB(3, stackTraceCapturer);

        a.run(0, b);
        return stackTraceCapturer.getStackTrace();
    }

    private List<StackTraceElement> generateCoRecursiveStack() {
        final CoRecursiveA a = new CoRecursiveA(2, stackTraceCapturer);
        final CoRecursiveB b = new CoRecursiveB(2, stackTraceCapturer);

        a.run(0, b);
        return stackTraceCapturer.getStackTrace();
    }

    private List<StackTraceElement> generateStackTraceContainingPrivateMethodCall() {
        final CallsPrivateMethodImmediately calls = new CallsPrivateMethodImmediately(stackTraceCapturer);
        calls.run();

        return stackTraceCapturer.getStackTrace();
    }

    private List<StackTraceElement> generateRepeatedCallStackOfDepth(int cycleCount) {
        final NRecursiveCalls nRecursiveCalls = new NRecursiveCalls(stackTraceCapturer, cycleCount);
        nRecursiveCalls.foo();
        return stackTraceCapturer.getStackTrace();
    }
}
