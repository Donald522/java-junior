package com.acme.edu;

import com.acme.edu.decorators.CharDecorator;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.decorators.IntDecorator;
import com.acme.edu.decorators.StringDecorator;
import com.acme.edu.loggers.CharLogger;
import com.acme.edu.loggers.IntLogger;
import com.acme.edu.loggers.Logger;
import com.acme.edu.loggers.StringLogger;
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
                System.out.println(message.getClass().getName());
                break;
            case "java.lang.Character":
                if((logger != null)) {
                    flush();
                }
                logger = CharLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = CharDecorator.getInstance();
                }
                break;
            case "java.lang.String":
                if((logger != null)) {
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
                System.out.println(message.getClass().getName());
                break;
            case "java.lang.Object":
                System.out.println(message.getClass().getName());
                break;
        }
        logger.log(message);
    }

    public void flush() {
        saver.save(decorator.decorate(logger.getData()));
        logger.clear();
    }

}
