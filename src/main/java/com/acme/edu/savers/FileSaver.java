package com.acme.edu.savers;

import com.acme.edu.exceptions.AppendException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// Apache commons-io
/**
 * Save message to file using BufferedWriter
 * Created by anton on 30.08.2016.
 */
public class FileSaver implements Saver {

    private File dirFile;
    private File logFile;
    private final int threshSize = 10;
    private String logDir;

    public FileSaver()  throws AppendException {
        dirFile = new File("." + File.separator + "logs");
        createDirFile();
    }

    public FileSaver(String logDir) throws AppendException {
        dirFile = new File(logDir);
        createDirFile();
    }

    private void createDirFile() throws AppendException {
        if ((!dirFile.exists()) && !dirFile.mkdirs()) {
            throw new AppendException("Wrong directory name");
        }
        if (!dirFile.isDirectory()) { throw new AppendException("Wrong directory name"); }
    }

    public void changeFile() throws AppendException {
        logFile = new File(dirFile, "log_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm"))+".txt");
        logFile.getParentFile().mkdirs();
    }

    public File getLogFile() {
        return logFile;
    }

    @Override
    public void save(String message) throws AppendException {
        if ((logFile == null) ||
                (!logFile.exists()) ||
                (logFile.length() > threshSize*1024*1024)) {
            changeFile();
        }
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
