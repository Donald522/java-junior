//package com.acme.edu.iteration02;
//
//import com.acme.edu.OldLogger;
//import com.acme.edu.SysoutCaptureAndAssertionAbility;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
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
//
//    @Test
//    public void shouldLogSequentIntegersAsSum() throws IOException {
//        //region when
//        OldLogger.log("str 1");
//        OldLogger.log(1);
//        OldLogger.log(2);
//        OldLogger.log("str 2");
//        OldLogger.log(0);
//        OldLogger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains(
//                "str 1" + System.lineSeparator());
//        assertSysoutContains(
//                "3" + System.lineSeparator());
//        assertSysoutContains(
//                "str 2" + System.lineSeparator());
//        assertSysoutContains(
//                "0" + System.lineSeparator());
//        //endregion
//    }
//
//    @Test
//    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
//        //region when
//        OldLogger.log("str 1");
//        OldLogger.log(10);
//        OldLogger.log(Integer.MAX_VALUE);
//        OldLogger.log("str 2");
//        OldLogger.log(0);
//        OldLogger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains(
//                "str 1" + System.lineSeparator());
//        assertSysoutContains(
//                "10" + System.lineSeparator());
//        assertSysoutContains(
//                Integer.MAX_VALUE + System.lineSeparator());
//        assertSysoutContains(
//                "str 2" + System.lineSeparator());
//        assertSysoutContains(
//                "0" + System.lineSeparator());
//        //endregion
//    }
//
//    @Test
//    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
//        //region when
//        OldLogger.log("str 1");
//        OldLogger.log((byte) 10);
//        OldLogger.log((byte) Byte.MAX_VALUE);
//        OldLogger.log("str 2");
//        OldLogger.log(0);
//        OldLogger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains(
//                "str 1");
//        assertSysoutContains(
//                "10");
//        assertSysoutContains(
//                String.valueOf(Byte.MAX_VALUE));
//        assertSysoutContains(
//                "str 2");
//        assertSysoutContains(
//                "0");
//        //endregion
//    }
//
//
//    @Test
//    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
//        //region when
//        OldLogger.log("str 1");
//        OldLogger.log("str 2");
//        OldLogger.log("str 2");
//        OldLogger.log(0);
//        OldLogger.log("str 2");
//        OldLogger.log("str 3");
//        OldLogger.log("str 3");
//        OldLogger.log("str 3");
//        OldLogger.flush();
//        //endregion
//
//        //region then
//        assertSysoutContains("str 1");
//        assertSysoutContains("str 2 (x2)");
//        assertSysoutContains("0");
//        assertSysoutContains("str 2");
//        assertSysoutContains("str 3 (x3)");
//        //endregion
//    }
//}