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
}
