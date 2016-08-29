package com.acme.edu.savers;

import com.acme.edu.exceptions.AppendException;

/**
 * Created by anton on 25.08.16.
 */
public interface Saver {

    void save(String message) throws AppendException;

}
