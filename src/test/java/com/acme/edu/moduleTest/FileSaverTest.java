package com.acme.edu.moduleTest;

import com.acme.edu.LoggerFacade;
import com.acme.edu.exceptions.LoggerException;
import com.acme.edu.savers.ConsoleSaver;
import com.acme.edu.savers.FileSaver;
import com.acme.edu.savers.Saver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;

/**
 * Created by Java_10 on 30.08.2016.
 */
public class FileSaverTest {
    private Saver consoleSaver, mockitoSaver;
    private LoggerFacade loggerFacade;
    //region given
    @Before
    public void setUpSystemOut() throws IOException, LoggerException {
        loggerFacade = new LoggerFacade(new FileSaver(), new ConsoleSaver());
    }

    @After
    public void tearDown() {    }
    //endregion

    @Test
    public void shouldWriteMessagesToFile() throws LoggerException {
        loggerFacade.log(1);
        loggerFacade.flush();
        loggerFacade.log(0);
        loggerFacade.flush();
        loggerFacade.log(2);
        loggerFacade.log(-1);
        loggerFacade.log(-2);
        loggerFacade.flush();
    }

}
