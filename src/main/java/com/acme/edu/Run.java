package com.acme.edu;

import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.loggers.ObjectLogger;
import com.acme.edu.savers.ConsoleSaver;

/**
 * Created by Dmitriy on 30.08.2016.
 */
public class Run {
    public static void main(String[] args) throws LoggerException {
        LoggerFacade logger = new LoggerFacade(new ConsoleSaver(), message -> System.out.println("Second saver:  " + message));
        logger.log(1);
        logger.log(2);
        logger.log(100);
        logger.log(Integer.MAX_VALUE);
        logger.log("asasa");
        logger.log("asasa");
        logger.log("asasa");
        logger.log("qweqwewqe");
        logger.log((byte)87);
        logger.log((byte)Byte.MAX_VALUE-1);
        logger.log(new ObjectLogger());
        logger.log(true);
        logger.log('h');
        logger.flush();
    }
}
