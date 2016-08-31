package com.acme.edu.RemoteServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Java_9 on 31.08.2016.
 */
public class Acceptor {
    private int port;
    private ArrayList<Socket> clientSockets = new ArrayList<>();

    public Acceptor(int port) {
        this.port = port;
    }

    public void accept() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            clientSockets.add(serverSocket.accept());
            new ClientSocketStreamHandler(clientSockets.get(clientSockets.size()-1)).readMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
