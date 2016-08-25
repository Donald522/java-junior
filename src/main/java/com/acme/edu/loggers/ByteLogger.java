package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;

/**
 * Created by Dmitriy on 25.08.2016.
 */
public class ByteLogger extends IntLogger {

    private static ByteLogger itSelf = null;

    private ByteLogger() {
        this.maxVaule = Byte.MAX_VALUE;
    }

    public static ByteLogger getInstance() {
        if(itSelf == null) {
            itSelf = new ByteLogger();
        }
        return itSelf;
    }

    @Override
    protected void applyNumSettings() {
        loggerType = Constants.BYTE;
    }
}
