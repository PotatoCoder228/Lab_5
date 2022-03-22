package ru.itmo.lab_5.exceptions;

/**
 * Исключение бросается, когда у объекта неверные координаты
 */

public class WrongCoordinatesException extends RuntimeException {
    public WrongCoordinatesException(String s) {
        super(s);
        System.out.println(s);
    }
}
