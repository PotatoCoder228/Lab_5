package ru.itmo.lab_5.exceptions;

/**
 * Исключение бросается, когда у объекта неверный тип
 */

public class WrongTypeException extends RuntimeException {
    public WrongTypeException(String s) {
        super(s);
        System.out.println(s);
    }
}
