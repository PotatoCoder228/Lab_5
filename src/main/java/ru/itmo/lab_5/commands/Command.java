package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

/**
 * Класс, определяющий поля и методы для классов команд
 */


public abstract class Command implements ExecuteCommand{

    /**
     * Строка, хранящая имя команды
     */

    protected String nameOfCommand;

    /**
     * Строка, хранящая описание команды
     */

    protected String description;
}
