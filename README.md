## Stacktracer

Suspicion:
* Call paths in object oriented code should be acyclic

Problem:
* Bawling at people to make things acyclic post-facto doesn't scale

Hope:
* Automating the nagging by encoding anti-cycle thinking into an assertion might work better

Thus: Stacktracer - a library for analysing Java stack traces for circular call paths.

## Example usage

Capture a stack:

<pre>
final List&lt;StackTraceElement&gt; stackTrace = StackTrace.getStackTrace();
</pre>

Transform it to include just the elements you like:

<pre>
final StackTransformer transformer = 
    after(klass(TestFramework.class)).and(before(PublisherProxy.class))
final List&lt;StackTraceElement&gt; transformed = transformer.apply(stackTrace);
</pre>

Scan a stack trace for cycles:

<pre>
final CycleReport report = CycleDetectors.scanForCycles(transformed);
for (final String klassName: report.classNamesThatCycled()) {
    System.out.println("Found a cycle that visits " + klassName + " at least twice!");
}
</pre>