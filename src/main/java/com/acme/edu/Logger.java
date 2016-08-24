package com.acme.edu;

import java.math.BigInteger;

public class Logger {

    private final static int INT = 1;
    private final static int BYTE = 2;
    private final static int CHAR = 3;
    private final static int BOOLEAN = 4;
    private final static int STRING = 5;
    private final static int OBJECT = 6;

    private static int accNumbersStream = 0;
    private static boolean numberStreamOn = false;
    private static int currentMessageType = 0;

    private static int maxIntCounter = 0;
    private static int maxByteCounter = 0;

    private static String lastLoggedString = "";
    private static int counterOfSameSimultaneousStrings = 1;
    private static boolean stringsStream = false;

    public static void log(int message) {
        if(checkType(INT)) {
            flush();
        }
        currentMessageType = INT;
        numberStreamOn = true;
        if(Integer.MAX_VALUE - accNumbersStream >= message) {
            accNumbersStream += message;
        } else {
            accNumbersStream -= (Integer.MAX_VALUE - message);
            maxIntCounter++;
        }
    }

    public static void log(byte message) {
        if(checkType(BYTE)) {
            flush();
        }
        currentMessageType = BYTE;
        numberStreamOn = true;
        if(Byte.MAX_VALUE - accNumbersStream >= message) {
            accNumbersStream += message;
        } else {
            accNumbersStream -= (Byte.MAX_VALUE - message);
            maxByteCounter++;
        }
    }

    public static void log(char message) {
        if(checkType(CHAR)) {
            flush();
        }
        currentMessageType = CHAR;
        logMessagePrinter("char: " + message + System.lineSeparator());
    }

    public static void log(boolean message) {
        if(checkType(BOOLEAN)) {
            flush();
        }
        currentMessageType = BOOLEAN;
        logMessagePrinter("primitive: " + message + System.lineSeparator());
    }

    public static void log(Object message) {
        if(message instanceof String) {
            if(checkType(STRING)) {
                flush();
            }
            currentMessageType = STRING;
            if(message.equals(lastLoggedString)) {
                counterOfSameSimultaneousStrings++;
            } else {
                flush();
                lastLoggedString = message.toString();
                stringsStream = true;
            }

        } else {
            if(checkType(OBJECT)) {
                flush();
            }
            currentMessageType = OBJECT;
            logMessagePrinter("reference: " + message + System.lineSeparator());
        }
    }

    public static void log(int[] message) {
        logMessagePrinter("primitives array: ");
        logOneDimArray(message);
    }

    public static void log(int[][] message) {
        logMessagePrinter("primitives matrix: {" + System.lineSeparator());
        for(int[] array : message) {
            logOneDimArray(array);
            logMessagePrinter(System.lineSeparator());
        }
        logMessagePrinter("}" + System.lineSeparator());
    }

    private static void logOneDimArray(int[] array) {
        logMessagePrinter("{");
        for (int i = 0, messageLength = array.length; i < messageLength; i++) {
            int element = array[i];
            logMessagePrinter(String.valueOf(element));
            if(i != messageLength - 1) {
                logMessagePrinter(", ");
            }
        }
        logMessagePrinter("}");
    }

    private static void logMessagePrinter(String message) {
        System.out.print(message);
    }

    private static boolean checkType(int messageType) {
        return (messageType != currentMessageType);
    }

    public static void flush() {
        if(numberStreamOn) {
            logMessagePrinter("primitive: " + accNumbersStream + System.lineSeparator());
            while(maxIntCounter != 0) {
                logMessagePrinter(Integer.MAX_VALUE + System.lineSeparator());
                maxIntCounter--;
            }
            while(maxByteCounter != 0) {
                logMessagePrinter(Byte.MAX_VALUE + System.lineSeparator());
                maxByteCounter--;
            }
            accNumbersStream = 0;
            numberStreamOn = false;
        }
        if(stringsStream) {
            if(counterOfSameSimultaneousStrings > 1) {
                logMessagePrinter("string: " + lastLoggedString + " (x" + counterOfSameSimultaneousStrings + ")" + System.lineSeparator());
                counterOfSameSimultaneousStrings = 1;
            } else {
                logMessagePrinter(" string: " + lastLoggedString + System.lineSeparator());
            }
            lastLoggedString = "";
            stringsStream = false;
        }
    }
}
