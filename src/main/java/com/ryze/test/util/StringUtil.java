package com.ryze.test.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.text.NumberFormat;

/**
 * Created by xueLai on 2020/1/10.
 */
public class StringUtil {

    /**
     * @param s 0.1245
     * @return 12.45%
     */
    public static String toPercent(String s) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        return nf.format(NumberUtils.toFloat(s)).replace(",", "");
    }
}
