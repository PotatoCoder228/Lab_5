package ru.itmo.lab_5.exceptions;

/**
 * Исключение бросается, когда у объекта неверная способность говорить
 */

public class WrongSpeakingException extends RuntimeException {
    public WrongSpeakingException(String s) {
        super(s);
        System.out.println(s);
    }
}
