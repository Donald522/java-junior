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
            String prefix = "";
            if (accIntStream.compareTo(new BigInteger("0")) < 0) {
                prefix = "-";
            }
            accIntStream = accIntStream.abs();
            logMessagePrinter("primitive: " + prefix + accIntStream.mod(new BigInteger(String.valueOf(Integer.MAX_VALUE))).toString());
            for (int i = 0; i < Integer.parseInt(accIntStream.divide(new BigInteger(String.valueOf(Integer.MAX_VALUE))).toString()); i++) {
                logMessagePrinter(prefix + String.valueOf(Integer.MAX_VALUE));
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
               "string: " + message.toString() : "reference: " + message);
    }

    private static void logMessagePrinter(String message) {
        System.out.println(message);
    }
}
