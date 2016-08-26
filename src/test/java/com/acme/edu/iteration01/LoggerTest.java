package com.acme.edu.iteration01;

import com.acme.edu.LoggerFacad;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.savers.ConsoleSaver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    LoggerFacad logger = new LoggerFacad(new ConsoleSaver(), message -> System.out.println("Second saver:  " + message));

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();

    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogInteger() throws IOException {
        //region when
        logger.log(1);
        logger.flush();
        logger.log(0);
        logger.flush();
        logger.log(-1);
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("primitive: 1" + System.lineSeparator());
        assertSysoutContains("primitive: 0" + System.lineSeparator());
        assertSysoutContains("primitive: -1" + System.lineSeparator());
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        logger.log((byte)1);
        logger.flush();
        logger.log((byte)0);
        logger.flush();
        logger.log((byte)-1);
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("1");
        assertSysoutContains("0");
        assertSysoutContains("-1");
        //endregion
    }


    @Test
    public void shouldLogChar() throws IOException {
        //region when
        logger.log('a');
        logger.flush();
        logger.log('b');
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws IOException {
        //region when
        logger.log("test string 1");
        logger.log("other str");
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        logger.log(true);
        logger.flush();
        logger.log(false);
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException {
        //region when
        logger.log(new Object());
        logger.flush();
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }


}