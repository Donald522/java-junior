//package com.acme.edu.iteration03;
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
//    public void shouldLogIntegersArray() throws IOException {
//        //region when
//        OldLogger.log(new int[] {-1, 0, 1});
//        //endregion
//
//        //region then
//        assertSysoutContains(
//            "primitives array: {-1, 0, 1}"
//        );
//        //endregion
//    }
//
//    @Test
//    public void shouldLogIntegersMatrix() throws IOException {
//        //region when
//        OldLogger.log(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
//        //endregion
//
//        //region then
//        assertSysoutEquals(
//            "primitives matrix: {" + System.lineSeparator() +
//                "{-1, 0, 1}" + System.lineSeparator() +
//                "{1, 2, 3}" + System.lineSeparator() +
//                "{-1, -2, -3}" + System.lineSeparator() +
//            "}" + System.lineSeparator()
//        );
//        //endregion
//    }
//
//    @Test
//    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
//        //region when
//        OldLogger.log(new int[][][][] {{{{0}}}});
//        //endregion
//
//        //region then
//        assertSysoutEquals(
//            "primitives multimatrix: {" + System.lineSeparator() +
//                "{" + System.lineSeparator() + "{" + System.lineSeparator() + "{" + System.lineSeparator() +
//                    "0" + System.lineSeparator() +
//                "}" + System.lineSeparator() + "}" + System.lineSeparator() + "}" + System.lineSeparator() +
//            "}" + System.lineSeparator()
//        );
//        //endregion
//    }
///*
//    @Test
//    public void shouldLogStringsWithOneMethodCall() throws IOException {
//        //region when
//        OldLogger.log("str1", "string 2", "str 3");
//        //endregion
//
//        //region then
//        assertSysoutContains("str1\nstring 2\nstr 3");
//        //endregion
//    }
//
//    @Test
//    public void shouldLogIntegersWithOneMethodCall() throws IOException {
//        //region when
//        OldLogger.log(-1, 0, 1, 3);
//        //endregion
//
//        //region then
//        assertSysoutContains("3");
//        //endregion
//    }
//
//    @Test
//    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
//        //region when
//        OldLogger.log(1);
//        OldLogger.log("str");
//        OldLogger.log(Integer.MAX_VALUE - 10);
//        OldLogger.log(11);
//        //endregion
//
//        //region then
//        assertSysoutContains(1);
//        assertSysoutContains("str");
//        assertSysoutContains(Integer.MAX_VALUE - 10);
//        assertSysoutContains(11);
//        //endregion
//    }
//
//    */
//}