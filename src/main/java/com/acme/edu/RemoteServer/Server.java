package com.acme.edu.RemoteServer;

/**
 * Created by Java_9 on 31.08.2016.
 */
public class Server {
    public void startAcceptSocket(int port) {
        Acceptor acceptor = new Acceptor(port);
        while(true) {
            acceptor.accept();
        }
    }
}
