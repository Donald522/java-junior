package com.acme.edu.RemoteServer;

import com.acme.edu.exceptions.AppendException;

import java.io.*;
import java.net.Socket;

/**
 * Created by Java_9 on 31.08.2016.
 */
public class ClientSocketStreamHandler {
    private Socket socket;

    public ClientSocketStreamHandler(Socket socket) {
        this.socket = socket;
    }

    public void readMessage() {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                socket.getInputStream()), "UTF-8"))) {
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
