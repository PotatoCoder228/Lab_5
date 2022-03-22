package ru.itmo.lab_5.exceptions;

/**
 * Исключение бросается, когда у объекта неверное имя
 */

public class WrongNameException extends RuntimeException {
    public WrongNameException(String s) {
        super(s);
        System.out.println(s);
    }
}
