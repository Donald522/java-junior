package com.acme.edu.loggers;

import com.acme.edu.constants.Constants;

/**
 * Created by Dmitriy on 25.08.2016.
 */
public class ByteLogger extends IntLogger {

    public ByteLogger() {
        setDefaultDecorator();
        this.loggerType = Constants.BYTE;
        this.maxValue = Byte.MAX_VALUE;
        this.minValue = Byte.MIN_VALUE;
    }
}
