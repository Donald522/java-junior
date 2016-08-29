package com.acme.edu.savers;

import com.acme.edu.exceptions.AppendException;

/**
 * Created by anton on 25.08.16.
 */
public class ConsoleSaver implements Saver {

    @Override
    public void save(String message) throws AppendException {
        System.out.println(message);
    }
}
