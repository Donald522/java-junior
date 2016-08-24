package com.acme.edu;

public class Logger {

    public static void log(int message) {
        logMessagePrinter("primitive: " + message);
    }

    public static void log(char message) {
        logMessagePrinter("char: " + message);
    }

    public static void log(boolean message) {
        logMessagePrinter("primitive: " + message);
    }

    public static void log(Object message) {
        logMessagePrinter((message instanceof String) ?
                "string: " + message : "reference: " + message);
    }

    private static void logMessagePrinter(String message) {
        System.out.println(message);
    }
}
