package com.acme.edu.demo;

import com.acme.edu.LoggerFacad;
import com.acme.edu.savers.ConsoleSaver;

/**
 * Runner for our own tests.
 * Created by anton on 25.08.16.
 */
public class Runner {
    public static void main(String[] args) {
        LoggerFacad logger = new LoggerFacad(new ConsoleSaver());
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
