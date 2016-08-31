package com.acme.edu.savers;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Java_10 on 31.08.2016.
 */
public class Connector {

    public Socket connect(String address, int port) throws IOException {
        return new Socket(address, port);
    }

}
