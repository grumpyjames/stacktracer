package net.digihippo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoundTest {

    @Test
    public void trim_all_elements_until_control_leaves_specified_class()
    {
        StackTraceElement[] stackTrace = generateStackTrace();
        StackTraceElement[] trimmed = Bound.AFTER.trim(BoundTest.class, stackTrace);
        assertEquals(2, trimmed.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_if_class_bound_is_not_found()
    {
        StackTraceElement[] stackTrace = generateStackTrace();
        Bound.AFTER.trim(Void.class, stackTrace);
    }

    private StackTraceElement[] generateStackTrace() {
        final StackCapture stackCapture = new StackCapture();
        final JustRun justRun = new JustRun(stackCapture);
        justRun.run();

        return stackCapture.getStackTrace();
    }
}
