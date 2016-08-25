package com.acme.edu;

/**
 * Created by Java_9 on 25.08.2016.
 */
public class LoggerOOP {
    private Saver saver;
    private LoggerBaseClass currentLogger = null;
//    private enum String {INT, BYTE, MESSAGE, }

    public LoggerOOP(Saver saver) {
        this.saver = saver;
    }

    private boolean checkCurrentLoggerIsNull() {
        return currentLogger == null;
    }

    public void flush() {
        if (checkCurrentLoggerIsNull()) {
            currentLogger.flush();
        }
    }

    public void log(int message) {
        if (checkCurrentLoggerIsNull()) {
            if (currentLogger.getType() != 1) {
                currentLogger.flush();
            }
        }
        currentLogger = new LoggerOOPInt("primitive: ", "");
        currentLogger.log(message);
    }

    public void log(byte message) {
        if (checkCurrentLoggerIsNull()) {
            if (currentLogger.getType() != 2) {
                currentLogger.flush();
            }
        }
        currentLogger = new LoggerOOPInt("primitive: ", "");
        currentLogger.log(message);
    }

    public void log(String message) {
        if (checkCurrentLoggerIsNull()) {
            if (currentLogger.getType() != 5) {
                currentLogger.flush();
            }
        }
        currentLogger = new LoggerOOPInt("string: ", "");
        currentLogger.log(message);
    }

    public void log(char message) {
        if (checkCurrentLoggerIsNull()) {
            if (currentLogger.getType() != 3) {
                currentLogger.flush();
            }
        }
        currentLogger = new LoggerOOPInt("char: ", "");
        currentLogger.log(message);
    }

    public void log(boolean message) {
        if (checkCurrentLoggerIsNull()) {
            if (currentLogger.getType() != 4) {
                currentLogger.flush();
            }
        }
        currentLogger = new LoggerOOPInt("primitive: ", "");
        currentLogger.log(message);
    }

    public void log(Object message) {
        if (checkCurrentLoggerIsNull()) {
            if (currentLogger.getType() != 6) {
                currentLogger.flush();
            }
        }
        currentLogger = new LoggerOOPInt("reference: ", "");
        currentLogger.log(message);
    }
}
