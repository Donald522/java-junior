package com.acme.edu;

/**
 * Created by Java_9 on 25.08.2016.
 */
public class ConsoleSaver implements Saver {
    @Override
    public void save(String message) {
        System.out.println(message);
    }
}
