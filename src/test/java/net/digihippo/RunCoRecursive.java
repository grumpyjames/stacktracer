package net.digihippo;

public class RunCoRecursive implements Runnable {
    private final int i;
    private final CoRecursiveA a;
    private final CoRecursiveB b;

    public RunCoRecursive(int i, CoRecursiveA a, CoRecursiveB b) {

        this.i = i;
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        a.run(i, b);
    }
}
