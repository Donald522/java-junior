package com.acme.edu.moduleTest;

import com.acme.edu.LoggerFacade;
import com.acme.edu.decorators.Decorator;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.loggers.ByteLogger;
import com.acme.edu.loggers.IntLogger;
import com.acme.edu.loggers.Logger;
import com.acme.edu.loggers.StringLogger;
import com.acme.edu.savers.ConsoleSaver;
import com.acme.edu.savers.Saver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.acme.edu.constants.Constants.*;
import static org.mockito.Mockito.*;

import com.acme.edu.SysoutCaptureAndAssertionAbility;
import java.io.IOException;

/**
 * Created by Java_9 on 26.08.2016.
 */
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private Saver consoleSaver, mockitoSaver;
    private LoggerFacade loggerFacade;
    //region given
    @Before
    public void setUpSystemOut() throws IOException, LoggerException {
        resetOut();
        captureSysout();
        mockitoSaver = mock(Saver.class);
        loggerFacade = new LoggerFacade(mockitoSaver);
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldCallIntLoggerWhenLodInt() throws LoggerException {
        //region Given
        Logger intLogger = mock(IntLogger.class);
        when(intLogger.getLoggerType()).thenReturn(INT);
        loggerFacade.addLoggers(intLogger);
        //endregion

        //region When
        loggerFacade.log(1);
        loggerFacade.flush();
        //endregion

        //region Then
        verify(intLogger).log(1);
        //endregion
    }

    @Test
    public void shouldCallStringLoggerWhenLodString() throws LoggerException {
        //region Given
        Logger mockLogger = mock(StringLogger.class);
        when(mockLogger.getLoggerType()).thenReturn(STRING);
        loggerFacade.addLoggers(mockLogger);
        //endregion

        //region When
        loggerFacade.log("test string");
        //endregion

        //region Then
        verify(mockLogger).log("test string");
        //endregion
    }

    @Test
    public void shouldCallByteLoggerWhenLodByte() throws LoggerException {
        //region Given
        Logger mockLogger = mock(ByteLogger.class);
        when(mockLogger.getLoggerType()).thenReturn(BYTE);
        loggerFacade.addLoggers(mockLogger);
        //endregion

        //region When
        loggerFacade.log((byte)4);
        loggerFacade.flush();
        //endregion

        //region Then
        verify(mockLogger).log(4);
        //endregion
    }

    @Test
    public void shouldCallCharLoggerWhenLodChar() throws LoggerException {
        //region Given
        //endregion

        //region When
        loggerFacade.log('s');
        loggerFacade.flush();
        //endregion

        //region Then
        verify(mockitoSaver).save("char: s");
        //endregion
    }

    @Test
    public void shouldCallBooleanLoggerWhenLodBoolean() throws LoggerException {
        //region Given
        //endregion

        //region When
        loggerFacade.log(true);
        loggerFacade.flush();
        //endregion

        //region Then
        verify(mockitoSaver).save("primitive: true");
        //endregion
    }

    @Test
    public void shouldCallObjectLoggerWhenLodObject() throws LoggerException {
        //region Given
        Object object = new Object();
        //endregion

        //region When
        loggerFacade.log(object);
        loggerFacade.flush();
        //endregion

        //region Then
        verify(mockitoSaver).save("reference: " + object.toString());
        //endregion
    }

    @Test
    public void shouldChooseIntBranchWhenChooseType() throws  LoggerException {
        //region Given
        IntLogger stub = mock(IntLogger.class);
        when(stub.getLoggerType()).thenReturn(1);
        when(stub.getData()).thenReturn("IntegerLoggerStub");
        LoggerFacade loggerFacade = new LoggerFacade(new ConsoleSaver());
        loggerFacade.addLoggers(stub);
        //endregion

        //region When
        loggerFacade.log(123);
        loggerFacade.flush();
        //endregion

        //region Then
        assertSysoutContains("IntegerLoggerStub");
        //endregion
    }

    @Test
    public void shouldChooseByteBranchWhenChooseType() throws  LoggerException {
        //region Given
        ByteLogger stub = mock(ByteLogger.class);
        when(stub.getLoggerType()).thenReturn(2);
        when(stub.getData()).thenReturn("ByteLoggerStub");
        LoggerFacade loggerFacade = new LoggerFacade(new ConsoleSaver());
        loggerFacade.addLoggers(stub);
        //endregion

        //region When
        loggerFacade.log((byte)12);
        loggerFacade.flush();
        //endregion

        //region Then
        assertSysoutContains("ByteLoggerStub");
        //endregion
    }

    @Test(expected=LoggerException.class)
    public void shouldThrowExceptionWhenNullLoggerAdd() throws LoggerException {
        //region When
        loggerFacade.addLoggers((Logger)null);
        //endregion
    }

    @Test(expected=LoggerException.class)
    public void shouldThrowExceptionWhenNullPassedToMethod() throws LoggerException {
        //region When
        loggerFacade.addLoggers((Logger[]) null);
        //endregion
    }

    @Test(expected=LoggerException.class)
    public void shouldThrowExceptionWhenOneElementFromVarargIsNull() throws LoggerException {
        //region When
        loggerFacade.addLoggers(new IntLogger(), null, new StringLogger());
        //endregion
    }

    @Test
    public void shouldChangeLoggerWithTheSameType() throws LoggerException {

        //region Given
        LoggerFacade loggerFacade = new LoggerFacade(new ConsoleSaver());
        loggerFacade.addLoggers(new IntLogger(), new StringLogger());
        IntLogger stub = mock(IntLogger.class);
        when(stub.getLoggerType()).thenReturn(1);
        when(stub.getData()).thenReturn("IntegerLoggerStub");
        loggerFacade.addLoggers(stub);
        //endregion

        //region When
        loggerFacade.log(5);
        loggerFacade.flush();
        //endregion

        //region Then
        assertSysoutContains("IntegerLoggerStub");
        //endregion
    }

    @Test
    public void shouldSetDecorator() throws LoggerException {

        //region Given
        LoggerFacade loggerFacade = new LoggerFacade(new ConsoleSaver());
        loggerFacade.addLoggers(new IntLogger(), new StringLogger());

        //endregion

        //region When
        loggerFacade.log(5, new Decorator("setDecoratorPrefixTest: ", "Postfix"));
        loggerFacade.flush();
        //endregion

        //region Then
        assertSysoutContains("setDecoratorPrefixTest: 5 Postfix");
        //endregion
    }

}
