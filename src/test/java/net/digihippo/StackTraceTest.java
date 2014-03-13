package net.digihippo;

import org.junit.Test;

import java.util.List;

import static net.digihippo.StackTrace.getStackTrace;
import static org.junit.Assert.assertEquals;

public class StackTraceTest {
    @Test
    public void stack_excludes_elements_from_StackTrace_class() {
        List<StackTraceElement> stackTrace = getStackTrace();
        assertEquals(StackTraceTest.class.getName(), stackTrace.get(0).getClassName());
    }
}
