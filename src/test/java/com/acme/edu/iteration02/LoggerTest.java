package com.acme.edu.iteration02;

import com.acme.edu.LoggerFacade;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.savers.ConsoleSaver;
import com.acme.edu.savers.Saver;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    LoggerFacade logger = new LoggerFacade(new ConsoleSaver(), new Saver() {
        @Override
        public void save(String message) {
            System.out.println("Second saver:  " + message);
        }
    });

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
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        try {
            logger.log("str 1");
            logger.log(1);
            logger.log(2);
            logger.log("str 2");
            logger.log(0);
            logger.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutContains(
                "str 1" + System.lineSeparator());
        assertSysoutContains(
                "3" + System.lineSeparator());
        assertSysoutContains(
                "str 2" + System.lineSeparator());
        assertSysoutContains(
                "0" + System.lineSeparator());
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        try {
            logger.log("str 1");
            logger.log(10);
            logger.log(Integer.MAX_VALUE);
            logger.log("str 2");
            logger.log(0);
            logger.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutContains(
                "str 1" + System.lineSeparator());
        assertSysoutContains(
                "10" + System.lineSeparator());
        assertSysoutContains(
                Integer.MAX_VALUE + System.lineSeparator());
        assertSysoutContains(
                "str 2" + System.lineSeparator());
        assertSysoutContains(
                "0" + System.lineSeparator());
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        try {
            logger.log("str 1");
            logger.log((byte) 10);
            logger.log((byte) Byte.MAX_VALUE);
            logger.log("str 2");
            logger.log(0);
            logger.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutContains(
                "str 1");
        assertSysoutContains(
                "10");
        assertSysoutContains(
                String.valueOf(Byte.MAX_VALUE));
        assertSysoutContains(
                "str 2");
        assertSysoutContains(
                "0");
        //endregion
    }


    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        try {
            logger.log("str 1");
            logger.log("str 2");
            logger.log("str 2");
            logger.log(0);
            logger.log("str 2");
            logger.log("str 3");
            logger.log("str 3");
            logger.log("str 3");
            logger.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("str 2 (x2)");
        assertSysoutContains("0");
        assertSysoutContains("str 2");
        assertSysoutContains("str 3 (x3)");
        //endregion
    }
}