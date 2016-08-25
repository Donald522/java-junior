package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public class LoggerMaster {

    Loger logger;
    Saver saver;
    Decorator decorator;

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
                logger = IntLogger.getInstance();
                if(decoratorBydefault) {
                    decorator = IntDecorator.getInstance();
                }
                break;
            case "java.lang.Byte":
                System.out.println(message.getClass().getName());
                break;
            case "java.lang.Character":
                System.out.println(message.getClass().getName());
                break;
            case "java.lang.String":
                System.out.println(message.getClass().getName());
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
