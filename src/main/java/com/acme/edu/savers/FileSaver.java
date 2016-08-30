package com.acme.edu.savers;

import com.acme.edu.exceptions.AppendException;

import java.io.*;
// Apache commons-io
/**
 * Save message to file using
 * Created by anton on 30.08.2016.
 */
public class FileSaver implements Saver {

    private File logFile;

    public FileSaver() {
        File dir = new File("." + File.separator + "logs");
        this.logFile = new File(dir, "log.txt");
        if (!logFile.getParentFile().exists()) {
            logFile.getParentFile().mkdirs();
        }
    }

    @Override
    public void save(String message) throws AppendException {

        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new FileOutputStream(logFile, true)), "UTF-8"))) {


        bufferedWriter.write(message);
        } catch (IOException e) {
            throw new AppendException("Can't append message to log file: " + message, e);
        }
    }
}
