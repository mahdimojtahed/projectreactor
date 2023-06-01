package com.dotin.projectreactor.dotin_class.util;

public class CommonUtil {
    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
