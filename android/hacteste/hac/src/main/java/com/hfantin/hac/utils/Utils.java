package com.hfantin.hac.utils;

import android.content.res.Resources;

import java.text.DecimalFormat;

/**
 * Created by hamilton on 08/06/17.
 */

public class Utils {

    private static final DecimalFormat dec = new DecimalFormat("#,##0.00");

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static String price(Double valor){

        return dec.format(valor);
    }

}
