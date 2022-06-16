package cg.mars2.game;

public class Chrono {

    public static long start = 0;

    public static void startChrono() {
        if (start == 0) {
            start = System.currentTimeMillis();
            System.err.println("start " + start);
            System.err.println("MAX_TIME_FOR_MUTATION " + Constant.MAX_TIME_FOR_MUTATION);
        }
    }

    public static long elapsedTime() {
        return System.currentTimeMillis() - start;
    }

    public static void stopChrono() {
        start = 0;
    }
}
