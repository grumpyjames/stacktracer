package net.digihippo;

import net.digihippo.xform.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AfterTest {
    @Test
    public void trim_all_elements_until_control_leaves_specified_class()
    {
        List<StackTraceElement> trimmed = new After(AfterTest.class).apply(generateStackTrace());
        assertEquals(2, trimmed.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_if_class_bound_is_not_found()
    {
        new After(Void.class).apply(generateStackTrace());
    }

    private List<StackTraceElement> generateStackTrace() {
        final StackCapture stackCapture = new StackCapture();
        final JustRun justRun = new JustRun(stackCapture);
        justRun.run();

        return Arrays.asList(stackCapture.getStackTrace());
    }
}
