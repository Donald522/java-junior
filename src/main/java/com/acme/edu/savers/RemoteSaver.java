package com.acme.edu.savers;

import com.acme.edu.exceptions.AppendException;

import java.io.*;
import java.net.Socket;

/**
 * Created by Java_10 on 31.08.2016.
 */
public class RemoteSaver implements  Saver {
    @Override
    public void save(String message) throws AppendException {
        try (BufferedWriter remoteWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new Socket("localhost", 1111).getOutputStream()), "UTF-8"))) {

            remoteWriter.write(message);
    } catch (IOException e) {
            throw new AppendException("Can't write to the remote server");
        }
    }
}
