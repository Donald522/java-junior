package com.acme.edu.moduleTest;

import com.acme.edu.LoggerFacade;
import com.acme.edu.decorators.IntDecorator;
import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.loggers.IntLogger;
import com.acme.edu.savers.ConsoleSaver;
import com.acme.edu.savers.Saver;
import com.sun.org.apache.xml.internal.security.signature.ObjectContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import com.acme.edu.SysoutCaptureAndAssertionAbility;

import java.io.IOException;

/**
 * Created by Java_9 on 26.08.2016.
 */
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    Saver consoleSaver, mockitoSaver;
    LoggerFacade loggerFacade;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
//        consoleSaver = new ConsoleSaver();
//        mockitoSaver = spy(consoleSaver);
        mockitoSaver = mock(Saver.class);
        loggerFacade = new LoggerFacade(mockitoSaver);

    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldCallIntLoggerWhenLodInt() {
        //region Given
        //endregion

        //region When
        try {
            loggerFacade.log(1);
            loggerFacade.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region Then
        try {
            verify(mockitoSaver).save("primitive: 1");
        } catch (AppendException e) {
            e.printStackTrace();
        }
        //endregion
    }

    @Test
    public void shouldCallStringLoggerWhenLodString() {
        //region Given
        //endregion

        //region When
        try {
            loggerFacade.log("123");
            loggerFacade.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region Then
        try {
            verify(mockitoSaver).save("string: 123");
        } catch (AppendException e) {
            e.printStackTrace();
        }
        //endregion
    }

    @Test
    public void shouldCallByteLoggerWhenLodByte() {
        //region Given
        //endregion

        //region When
        try {
            loggerFacade.log((byte)4);
            loggerFacade.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region Then
        try {
            verify(mockitoSaver).save("primitive: 4");
        } catch (AppendException e) {
            e.printStackTrace();
        }
        //endregion
    }

    @Test
    public void shouldCallCharLoggerWhenLodChar() {
        //region Given
        //endregion

        //region When
        try {
            loggerFacade.log('s');
            loggerFacade.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region Then
        try {
            verify(mockitoSaver).save("char: s");
        } catch (AppendException e) {
            e.printStackTrace();
        }
        //endregion
    }

    @Test
    public void shouldCallBooleanLoggerWhenLodBoolean() {
        //region Given
        //endregion

        //region When
        try {
            loggerFacade.log(true);
            loggerFacade.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region Then
        try {
            verify(mockitoSaver).save("primitive: true");
        } catch (AppendException e) {
            e.printStackTrace();
        }
        //endregion
    }

    @Test
    public void shouldCallObjectLoggerWhenLodObject() {
        //region Given
        Object object = new Object();
        //endregion

        //region When
        try {
            loggerFacade.log(object);
            loggerFacade.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region Then
        try {
            verify(mockitoSaver).save("reference: " + object.toString());
        } catch (AppendException e) {
            e.printStackTrace();
        }
        //endregion
    }

    @Test
    public void shouldChooseTheBranchToLogInteger() {
        //region Given
        IntDecorator stub = mock(IntDecorator.class);
        when(stub.decorate("123")).thenReturn("Integer 123");
        LoggerFacade loggerFacade = new LoggerFacade(new ConsoleSaver());
        try {
            loggerFacade.setDecorator(stub);
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region When
        try {
            loggerFacade.log(123);
            loggerFacade.flush();
        } catch (AppendException e) {
            e.printStackTrace();
        } catch (DecorateException e) {
            e.printStackTrace();
        }
        //endregion

        //region Then
        assertSysoutContains("Integer 123");
        //endregion
    }




}
