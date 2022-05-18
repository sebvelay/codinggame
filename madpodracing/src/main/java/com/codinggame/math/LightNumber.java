package com.codinggame.math;

import com.codinggame.simulation.Constant;

public class LightNumber {

    static int round(double x) {
        int s = x < 0 ? -1 : 1;
        return s * (int) Math.round(s * x);
    }

    public static int truncate(double x){
        //Optimization to fix rounding errors. No need to replicate
        double roundedX = round(x);
        if(Math.abs(roundedX-x) < Constant.EPSILON){
            return (int)roundedX;
        }

        return (int)(x < 0 ? Math.ceil(x) : Math.floor(x));
    }

    public static double roundToTwoDecimals(double a){
        return Math.round(a * 100.0) / 100.0;
    }
}
