package ru.itmo.lab_5.exceptions;

public class FileWrongPermissionsException extends RuntimeException{
    public FileWrongPermissionsException(){
        super("Невозможно прочитать файл.");
    }
}
