
package com.example.kotlinpractices.newTask;

/**
 * A parent exception, often coming from SQL Exceptions
 */
public class MBTilesException extends Exception {

    public MBTilesException(Throwable e) {
        super(e);
    }

    public MBTilesException(String msg, Throwable e) {
        super(msg, e);
    }
}
