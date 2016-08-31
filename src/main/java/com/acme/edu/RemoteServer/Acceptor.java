package com.acme.edu.RemoteServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Java_9 on 31.08.2016.
 */
public class Acceptor {
    private int port;

    public Acceptor(int port) {
        this.port = port;
    }

    public void accept() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket clientSocket = serverSocket.accept();
            new ClientSocketStreamHandler(clientSocket).readMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
