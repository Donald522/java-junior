package com.acme.edu;

import com.acme.edu.savers.ConsoleSaver;

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
        logger.log("asfgfg");
        logger.log("asd");
        logger.log("asd");
        logger.log("tert");
        logger.log((byte)11);
        logger.log((byte)(Byte.MAX_VALUE-1));
        logger.log('a');
        logger.log('b');
        logger.log('c');
        logger.flush();
    }
}
