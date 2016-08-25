package com.acme.edu;

public class OldLogger {

    private final static int INT = 1;
    private final static int BYTE = 2;
    private final static int CHAR = 3;
    private final static int BOOLEAN = 4;
    private final static int STRING = 5;
    private final static int OBJECT = 6;
    private final static int OBJECTPTR = 7;

    private int accNumbersStream = 0;
    private boolean numberStreamOn = false;
    private int currentMessageType = 0;

    private int maxNumberCounter = 0;

    private String lastLoggedString = "";
    private int counterOfSameSimultaneousStrings = 1;
    private boolean stringsStream = false;

    public void log(int message) {
        checkType(INT);
        currentMessageType = INT;
        numberStreamOn = true;
        if(Integer.MAX_VALUE - accNumbersStream >= message) {
            accNumbersStream += message;
        } else {
            accNumbersStream -= (Integer.MAX_VALUE - message);
            maxNumberCounter++;
        }
    }

    public void log(byte message) {
        checkType(BYTE);
        currentMessageType = BYTE;
        numberStreamOn = true;
        if(Byte.MAX_VALUE - accNumbersStream >= message) {
            accNumbersStream += message;
        } else {
            accNumbersStream -= (Byte.MAX_VALUE - message);
            maxNumberCounter++;
        }
    }

    public void log(char message) {
        checkType(CHAR);
        currentMessageType = CHAR;
        logMessagePrinter("char: " + message + System.lineSeparator());
    }

    public void log(boolean message) {
        checkType(BOOLEAN);
        currentMessageType = BOOLEAN;
        logMessagePrinter("primitive: " + message + System.lineSeparator());
    }

    public void log(Object message) {
        if(message instanceof String) {
            checkType(STRING);
            currentMessageType = STRING;
            if(message.equals(lastLoggedString)) {
                counterOfSameSimultaneousStrings++;
            } else {
                flush();
                lastLoggedString = message.toString();
                stringsStream = true;
            }

        } else {
            checkType(OBJECT);
            currentMessageType = OBJECT;
            logMessagePrinter("reference: " + message + System.lineSeparator());
        }
    }

    public void log(int[] message) {
        checkType(OBJECTPTR);
        logMessagePrinter("primitives array: ");
        logOneDimArray(message);
    }

    public void log(int[][] message) {
        checkType(OBJECTPTR);
        logMessagePrinter("primitives matrix: {" + System.lineSeparator());
        for(int[] array : message) {
            logOneDimArray(array);
            logMessagePrinter(System.lineSeparator());
        }
        logMessagePrinter("}" + System.lineSeparator());
    }

    public void log(Object[] message) {
        checkType(OBJECTPTR);
        String className = message.getClass().getName();
        String prefix = "primitives matrix: {" + System.lineSeparator();
        String postfix = "}";
        int i = 0;
        int[] array;
        while(className.charAt(i) == 'I')
        {
            prefix += "{" + System.lineSeparator();
            postfix = "}" + System.lineSeparator() + postfix;
            i++;
        }
        System.out.println();
    }

    private void logOneDimArray(int[] array) {
        checkType(OBJECTPTR);
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

    private void logMessagePrinter(String message) {
        System.out.print(message);
    }

    private void checkType(int messageType) {
        if (messageType != currentMessageType) {
            flush();
        }
    }

    public void flush() {
        if(numberStreamOn) {
            logMessagePrinter("primitive: " + accNumbersStream + System.lineSeparator());
            if(currentMessageType == INT) {
                while (maxNumberCounter != 0) {
                    logMessagePrinter(Integer.MAX_VALUE + System.lineSeparator());
                    maxNumberCounter--;
                }
            } else {
                while (maxNumberCounter != 0) {
                    logMessagePrinter(Byte.MAX_VALUE + System.lineSeparator());
                    maxNumberCounter--;
                }
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
