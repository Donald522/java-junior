package com.acme.edu.moduleTest;

import com.acme.edu.RemoteServer.Server;
import org.junit.Test;

/**
 * Created by Java_9 on 31.08.2016.
 */
public class ServerTest {
    @Test
    public void shouldGetRemoteMessages() {
        Server server = new Server();
        server.startAcceptSocket();
    }
}
