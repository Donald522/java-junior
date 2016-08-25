package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public class Runner {
    public static void main(String[] args) {
        LoggerMaster logger = new LoggerMaster(new ConsoleSaver());
        logger.log(5);
        logger.log(15);
        logger.flush();
        logger.log(3);
        logger.flush();
        logger.log(4);
        logger.log(Integer.MAX_VALUE - 2);
        logger.log(11);
        logger.log(Integer.MAX_VALUE - 5);
        logger.flush();
    }
}
