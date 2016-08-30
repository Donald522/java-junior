package com.acme.edu.moduleTest;

import com.acme.edu.LoggerFacade;
import com.acme.edu.exceptions.AppendException;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.savers.ConsoleSaver;
import com.acme.edu.savers.FileSaver;
import com.acme.edu.savers.Saver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Java_10 on 30.08.2016.
 */
public class FileSaverTest {
    private Saver fileSaver, mockitoSaver;
    private LoggerFacade loggerFacade;
    //region given
    @Before
    public void setUpSystemOut() throws IOException, LoggerException {
        fileSaver = new FileSaver();
        if (((FileSaver)fileSaver).getLogFile().exists()) {
            ((FileSaver) fileSaver).getLogFile().delete();
        }
        fileSaver = new FileSaver();
        loggerFacade = new LoggerFacade(fileSaver, new ConsoleSaver());
    }

    @After
    public void tearDown() {    }
    //endregion

//    @Test
//    public void shouldWriteMessagesToFile() throws LoggerException {
//        loggerFacade.log(1);
//        loggerFacade.flush();
//        loggerFacade.log(0);
//        loggerFacade.flush();
//        loggerFacade.log(2);
//        loggerFacade.log(-1);
//        loggerFacade.log(-2);
//        loggerFacade.flush();
//    }

    @Test
    public void shouldWriteMessagesToFile() throws LoggerException {
        File logfile = ((FileSaver)fileSaver).getLogFile();

        fileSaver.save("cool message1" + System.lineSeparator());
        fileSaver.save("cool message2" + System.lineSeparator());

        String readedLine = "";

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream(logfile)), "UTF-8"))) {


            for (int i = 0; i < 2; i++) {
                readedLine += bufferedReader.readLine() + System.lineSeparator();
            }
        } catch (IOException e) {
            throw new AppendException("Can't read message from log file: ", e);
        }
        assertEquals("cool message1" + System.lineSeparator() +
                "cool message2" + System.lineSeparator(),
                readedLine);
    }

}
