package ru.itmo.lab_5.exceptions;

public class FileNotExistsException extends RuntimeException{
    public FileNotExistsException(){
        super("Невозможно найти файл.");
    }
}
