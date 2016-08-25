package com.acme.edu;

import com.acme.edu.decorators.*;
import com.acme.edu.loggers.*;
import com.acme.edu.savers.Saver;

/**
 * Created by anton on 25.08.16.
 */
public class LoggerMaster {

    private Logger logger;
    private Saver saver;
    private Decorator decorator;

    private boolean decoratorBydefault = true;

    public LoggerMaster(Saver saver) {
        this.saver = saver;
    }

    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
        decoratorBydefault = false;
    }

    public void log(Object message) {
        switch (message.getClass().getName()) {
            case "java.lang.Integer":
                if((logger != null) && !(logger instanceof IntLogger)) {
                    flush();
                }
                logger = IntLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = IntDecorator.getInstance();
                }
                break;
            case "java.lang.Byte":
                if((logger != null) && !(logger instanceof ByteLogger)) {
                    flush();
                }
                message = ((Byte)message).intValue();
                logger = ByteLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = IntDecorator.getInstance();
                }
                break;
            case "java.lang.Character":
                if((logger != null) && !(logger instanceof CharLogger)) {
                    flush();
                }
                logger = CharLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = CharDecorator.getInstance();
                }
                break;
            case "java.lang.String":
                if((logger != null) && !(logger instanceof StringLogger)) {
                    flush();
                }
                logger = StringLogger.getInstance();
                logger.setSaver(saver);
                if(decoratorBydefault) {
                    decorator = StringDecorator.getInstance();
                }
                logger.setDecorator(decorator);
                break;
            case "java.lang.Boolean":
                if((logger != null) && !(logger instanceof BooleanLogger)) {
                    flush();
                }
                logger = BooleanLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = BooleanDecorator.getInstance();
                }
                break;
            case "java.lang.Object":
                if((logger != null) && !(logger instanceof ObjectLogger)) {
                    flush();
                }
                logger = ObjectLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = ObjectDecorator.getInstance();
                }
                break;
        }
        logger.log(message);
    }

    public void flush() {
        saver.save(decorator.decorate(logger.getData()));
        logger.clear();
    }

}
