package net.digihippo.xform;

import net.digihippo.JustRun;
import net.digihippo.StackCapture;
import org.junit.Test;

import java.util.List;

import static net.digihippo.Predicates.klass;
import static net.digihippo.xform.StackTransformers.after;
import static org.junit.Assert.assertEquals;

public class AfterTest {
    @Test
    public void trim_all_elements_until_control_leaves_specified_class()
    {
        List<StackTraceElement> trimmed = after(klass(AfterTest.class)).apply(generateStackTrace());
        assertEquals(2, trimmed.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_if_class_bound_is_not_found()
    {
        after(klass(Void.class)).apply(generateStackTrace());
    }

    private List<StackTraceElement> generateStackTrace() {
        final StackCapture stackCapture = new StackCapture();
        final JustRun justRun = new JustRun(stackCapture);
        justRun.run();

        return stackCapture.getStackTrace();
    }
}
