package com.acme.edu;

public class Logger {

    private static boolean accIntStreamNotNull = false;
    private static int accIntStream = 0;

    public static void log(int message) {
        accIntStreamNotNull = true;
        accIntStream += message;
    }

    public static void logEnd() {
        logAccIntStream();
    }

    private static void logAccIntStream() {
        if (accIntStreamNotNull) {
            logMessagePrinter(Integer.toString(accIntStream));
            accIntStream = 0;
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
