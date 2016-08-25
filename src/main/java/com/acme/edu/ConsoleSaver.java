package com.acme.edu;

/**
 * Created by anton on 25.08.16.
 */
public class ConsoleSaver implements Saver {

    @Override
    public void save(String message) {
        System.out.println(message);
    }
}
