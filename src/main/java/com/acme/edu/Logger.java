package com.acme.edu;

import java.math.BigInteger;

public class Logger {

    private static boolean accNumStreamNotNull = false;
    private static boolean isaccNumeger = false;
    private static int prevClassType = 0;
    private static BigInteger accNumStream = new BigInteger("0");
    private static String lastLoggedString = "";
    private static int counterOfSameSimultaneousStrings = 0;

    private static void checkPrevType(int type){
        if (type != prevClassType) {
            logEnd();
        }
    }

    public static void log(int message) {
        checkPrevType(1);
        prevClassType = 1;
        isaccNumeger = true;
        accNumStreamNotNull = true;
        accNumStream = accNumStream.add(new BigInteger(String.valueOf(message)));
    }

    public static void log(byte message) {
        checkPrevType(2);
        prevClassType = 2;
        isaccNumeger = false;
        accNumStreamNotNull = true;
        accNumStream = accNumStream.add(new BigInteger(String.valueOf(message)));
    }

    public static void logEnd() {
        if (counterOfSameSimultaneousStrings > 0)
        {
            String appendix = "";
            if (counterOfSameSimultaneousStrings > 1)
                appendix = " (x" + String.valueOf(counterOfSameSimultaneousStrings + ")");
            logMessagePrinter("string: " + lastLoggedString + appendix);
            counterOfSameSimultaneousStrings = 0;
            lastLoggedString = "";
        }
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
        checkPrevType(3);
        prevClassType = 3;
        logMessagePrinter("char: " + message);
    }

    public static void log(boolean message) {
        checkPrevType(4);
        prevClassType = 4;
        logMessagePrinter("primitive: " + message);
    }

    public static void log(Object message) {
        if(message instanceof String) {
            checkPrevType(5);
            prevClassType = 5;
            if(message.equals(lastLoggedString)) {
                counterOfSameSimultaneousStrings++;
            } else {
                logEnd();
                counterOfSameSimultaneousStrings = 1;
                lastLoggedString = message.toString();
            }
        } else {
            checkPrevType(6);
            prevClassType = 6;
            logMessagePrinter("reference: " + message);
        }
    }

    private static void logMessagePrinter(String message) {
        System.out.println(message);
    }
}
