package com.acme.edu;

public class Logger {
    public static void log(int message) { System.out.println("primitive: " + message); }

    public static void log(char message) { System.out.println("char: " + message); }

    public static void log(boolean message) { System.out.println("primitive: " + message); }

    public static void log(Object message) { System.out.println((message instanceof String) ? "string: " + message : "reference: " + message); }
}
