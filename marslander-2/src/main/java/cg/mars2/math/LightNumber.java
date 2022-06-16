package cg.mars2.math;

public class LightNumber {

    public static int round(double x) {
        int s = x < 0 ? -1 : 1;
        return s * (int) Math.round(s * x);
    }

    public static int truncate(double x){
        //Optimization to fix rounding errors. No need to replicate
        double roundedX = round(x);


       // return (int)(x > 0 ? Math.ceil(x) : Math.floor(x));
        return (int)roundedX;
    }

    public static double roundToTwoDecimals(double a){
        return Math.round(a * 100.0) / 100.0;
    }
}
