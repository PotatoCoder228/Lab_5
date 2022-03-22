package ru.itmo.lab_5.exceptions;

/**
 * Исключение бросается, когда у объекта неверная дата создания
 */

public class WrongDateException extends RuntimeException {
    public WrongDateException(String s) {
        super(s);
        System.out.println(s);
    }
}