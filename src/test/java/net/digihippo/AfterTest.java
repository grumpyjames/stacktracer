package net.digihippo;

import net.digihippo.xform.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AfterTest {
    @Test
    public void trim_all_elements_until_control_leaves_specified_class()
    {
        StackTraceElement[] stackTrace = generateStackTrace();
        StackTraceElement[] trimmed = new After(AfterTest.class).apply(stackTrace);
        assertEquals(2, trimmed.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_if_class_bound_is_not_found()
    {
        StackTraceElement[] stackTrace = generateStackTrace();
        new After(Void.class).apply(stackTrace);
    }

    private StackTraceElement[] generateStackTrace() {
        final StackCapture stackCapture = new StackCapture();
        final JustRun justRun = new JustRun(stackCapture);
        justRun.run();

        return stackCapture.getStackTrace();
    }
}
