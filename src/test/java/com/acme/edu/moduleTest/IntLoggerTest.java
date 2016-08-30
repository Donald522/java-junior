package com.acme.edu.moduleTest;

import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.constants.Constants;
import com.acme.edu.loggers.IntLogger;
import com.acme.edu.savers.Saver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by anton on 30.08.16.
 */
public class IntLoggerTest implements SysoutCaptureAndAssertionAbility {
    private IntLogger intLogger;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        intLogger = new IntLogger();

    }

    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldCreateObjectWithCorrectType() {
        //region Given
        //endregion

        //region When
        int type = intLogger.getLoggerType();
        //endregion

        //region Then
        assertEquals(Constants.INT, type);
        //endregion
    }

    @Test
    public void shouldReturnEmptyStringWhenGetDataWithoutLog() {

        //region When
        String data = intLogger.getData();
        //endregion

        //region Then
        assertEquals("primitive: ", data);
        //endregion
    }

    @Test
    public void shouldReturnNonEmptyStringWhenGetDataAfterLog() {

        //region When
        intLogger.log(2);
        String data = intLogger.getData();
        //endregion

        //region Then
        assertEquals("primitive: 2", data);
        //endregion
    }

    @Test
    public void shouldReturnMaxValuewhenSumMoreThanMaxValue() {

        //region When
        intLogger.log(101);
        intLogger.log(Integer.MAX_VALUE-1);
        String data = intLogger.getData();
        //endregion

        //region Then
        assertEquals("primitive: 100" + System.lineSeparator() + String.valueOf(Integer.MAX_VALUE), data);
        //endregion
    }

    @Test
    public void shouldReturnMaxValueWhenSumLittleThanMinValue() {

        //region When
        intLogger.log(-101);
        intLogger.log(Integer.MIN_VALUE+1);
        String data = intLogger.getData();
        //endregion

        //region Then
        assertEquals("primitive: -100" + System.lineSeparator() + String.valueOf(Integer.MIN_VALUE), data);
        //endregion
    }

    @Test
    public void shouldHadleChangedSignValue() {

        //region When
        intLogger.log(101);
        intLogger.log(Integer.MAX_VALUE-1);
        intLogger.log(Integer.MAX_VALUE);
        intLogger.log(Integer.MIN_VALUE);
        intLogger.log(Integer.MAX_VALUE);
        String data = intLogger.getData();
        //endregion

        //region Then
        assertEquals("primitive: " + (Integer.MIN_VALUE + 100 + Integer.MAX_VALUE)
                + System.lineSeparator() + String.valueOf(Integer.MAX_VALUE)
                + System.lineSeparator() + String.valueOf(Integer.MAX_VALUE), data);
        //endregion
    }
}
