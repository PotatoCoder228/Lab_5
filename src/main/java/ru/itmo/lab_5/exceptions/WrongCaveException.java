package ru.itmo.lab_5.exceptions;

/**
 * Исключение бросается, когда у объекта неверная глубина пещеры
 */

public class WrongCaveException extends RuntimeException {
    public WrongCaveException(String s) {
        super(s);
        System.out.println(s);
    }
}
