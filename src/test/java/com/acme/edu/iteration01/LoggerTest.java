//package com.acme.edu.iteration01;
//
//import com.acme.edu.OldLogger;
//import com.acme.edu.SysoutCaptureAndAssertionAbility;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.*;
//
//public class LoggerTest implements SysoutCaptureAndAssertionAbility {
//    //region given
//    @Before
//    public void setUpSystemOut() throws IOException {
//        resetOut();
//        captureSysout();
//    }
//
//    @After
//    public void tearDown() {
//        resetOut();
//    }
//    //endregion
//
//    @Test
//    public void shouldLogInteger() throws IOException {
//        //region when
//        OldLogger.log(1);
//        OldLogger.flush();
//        OldLogger.log(0);
//        OldLogger.flush();
//        OldLogger.log(-1);
//        OldLogger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains("primitive: ");
//        assertSysoutEquals("primitive: 1" + System.lineSeparator() + "primitive: 0" + System.lineSeparator() + "primitive: -1" + System.lineSeparator());
//        //endregion
//    }
//
//    @Test
//    public void shouldLogByte() throws IOException {
//        //region when
//        OldLogger.log((byte)1);
//        OldLogger.flush();
//        OldLogger.log((byte)0);
//        OldLogger.flush();
//        OldLogger.log((byte)-1);
//        OldLogger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains("primitive: ");
//        assertSysoutContains("1");
//        assertSysoutContains("0");
//        assertSysoutContains("-1");
//        //endregion
//    }
//
//
//    @Test
//    public void shouldLogChar() throws IOException {
//        //region when
//        OldLogger.log('a');
//        OldLogger.log('b');
//        //endregion
//
//        //region then
//        assertSysoutContains("char: ");
//        assertSysoutContains("a");
//        assertSysoutContains("b");
//        //endregion
//    }
//
//    @Test
//    public void shouldLogString() throws IOException {
//        //region when
//        OldLogger.log("test string 1");
//        OldLogger.log("other str");
//        OldLogger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains("string: ");
//        assertSysoutContains("test string 1");
//        assertSysoutContains("other str");
//        //endregion
//    }
//
//    @Test
//    public void shouldLogBoolean() throws IOException {
//        //region when
//        OldLogger.log(true);
//        OldLogger.log(false);
//        //endregion
//
//        //region then
//        assertSysoutContains("primitive: ");
//        assertSysoutContains("true");
//        assertSysoutContains("false");
//        //endregion
//    }
//
//    @Test
//    public void shouldLogReference() throws IOException {
//        //region when
//        OldLogger.log(new Object());
//        //endregion
//
//        //region then
//        assertSysoutContains("reference: ");
//        assertSysoutContains("@");
//        //endregion
//    }
//
//
//}