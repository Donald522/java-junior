package com.acme.edu;

import java.math.BigInteger;

public class Logger {

    private static boolean accNumStreamNotNull = false;
    private static boolean isaccNumeger = false;
    private static BigInteger accNumStream = new BigInteger("0");

    public static void log(int message) {
        if (!isaccNumeger)
            logEnd();
        isaccNumeger = true;
        accNumStreamNotNull = true;
        accNumStream = accNumStream.add(new BigInteger(String.valueOf(message)));
    }

    public static void log(byte message) {
        if (isaccNumeger)
            logEnd();
        isaccNumeger = false;
        accNumStreamNotNull = true;
        accNumStream = accNumStream.add(new BigInteger(String.valueOf(message)));
    }

    public static void logEnd() {
        if (isaccNumeger)
            logaccNumStream(Integer.MAX_VALUE);
        else
            logaccNumStream((int) Byte.MAX_VALUE);
    }

    private static void logaccNumStream(Integer maxValue) {
        if (accNumStreamNotNull) {
            String prefix = "";
            if (accNumStream.compareTo(new BigInteger("0")) < 0) {
                prefix = "-";
            }
            accNumStream = accNumStream.abs();
            logMessagePrinter("primitive: " + prefix + accNumStream.mod(new BigInteger(String.valueOf(maxValue))).toString());
            for (int i = 0; i < Integer.parseInt(accNumStream.divide(new BigInteger(String.valueOf(maxValue))).toString()); i++) {
                logMessagePrinter(prefix + String.valueOf(maxValue));
            }
            accNumStream = new BigInteger("0");
            accNumStreamNotNull = false;
        }
    }

    public static void log(char message) {
        logEnd();
        logMessagePrinter("char: " + message);
    }

    public static void log(boolean message) {
        logEnd();
        logMessagePrinter("primitive: " + message);
    }

    public static void log(Object message) {
        logEnd();
        logMessagePrinter((message instanceof String) ?
               "string: " + message.toString() : "reference: " + message);
    }

    private static void logMessagePrinter(String message) {
        System.out.println(message);
    }
}
