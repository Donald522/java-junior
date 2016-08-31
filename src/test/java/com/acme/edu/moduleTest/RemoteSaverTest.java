package com.acme.edu.moduleTest;

import com.acme.edu.exceptions.AppendException;
import com.acme.edu.savers.RemoteSaver;
import com.acme.edu.savers.Saver;
import org.junit.Test;

/**
 * Created by Java_9 on 31.08.2016.
 */
public class RemoteSaverTest {
    @Test
    public void shouldSendMessageOnRemoteServer() throws AppendException {
        Saver saver = new RemoteSaver();
        saver.save("new message");
        saver.save("ololol");
    }
}
