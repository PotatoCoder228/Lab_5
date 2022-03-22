package ru.itmo.lab_5.exceptions;

/**
 * Исключение бросается, когда у объекта неверный id
 */

public class WrongIdException extends RuntimeException {
    public WrongIdException(String s) {
        super(s);
        System.out.println(s);
    }
}
