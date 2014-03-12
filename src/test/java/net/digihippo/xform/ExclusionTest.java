package net.digihippo.xform;

import net.digihippo.JustRun;
import net.digihippo.StackCapture;
import org.junit.Test;

import java.util.List;

import static net.digihippo.xform.StackTransformers.excluding;
import static org.junit.Assert.fail;

public class ExclusionTest {
    private final StackCapture stackCapture = new StackCapture();

    @Test
    public void should_omit_elements_from_excluded_class() {
        new JustRun(new JustRun(stackCapture)).run();
        List<StackTraceElement> filtered = excluding(JustRun.class).apply(stackCapture.getStackTrace());

        for (final StackTraceElement stackTraceElement : filtered) {
            if (stackTraceElement.getClassName().equals(JustRun.class.getName())) {
                fail("Found an element with the class name that was supposed to be excluded");
            }
        }
    }
}
