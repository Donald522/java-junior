package com.acme.edu;

import java.math.BigInteger;

public class Logger {

    private static boolean accIntStreamNotNull = false;
    private static BigInteger accIntStream = new BigInteger("0");

    public static void log(int message) {
        accIntStreamNotNull = true;
        accIntStream = accIntStream.add(new BigInteger(String.valueOf(message)));
    }

    public static void logEnd() {
        logAccIntStream();
    }

    private static void logAccIntStream() {
        if (accIntStreamNotNull) {
            logMessagePrinter(accIntStream.mod(new BigInteger(String.valueOf(Integer.MAX_VALUE))).toString());
            for (int i = 0; i < Integer.parseInt(accIntStream.divide(new BigInteger(String.valueOf(Integer.MAX_VALUE))).toString()); i++) {
                logMessagePrinter(String.valueOf(Integer.MAX_VALUE));
            }
            accIntStream = new BigInteger("0");
            accIntStreamNotNull = false;
        }
    }

    public static void log(char message) {
        logAccIntStream();
        logMessagePrinter("char: " + message);
    }

    public static void log(boolean message) {
        logAccIntStream();
        logMessagePrinter("primitive: " + message);
    }

    public static void log(Object message) {
        logAccIntStream();
        logMessagePrinter((message instanceof String) ?
                message.toString() : "reference: " + message);
    }

    private static void logMessagePrinter(String message) {
        System.out.println(message);
    }
}
