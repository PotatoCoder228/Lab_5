package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

/**
 * Интерфейс, описывающий вызов всех команд
 */

public interface ExecuteCommand {

    /**
     * Метод, вызывающий консольную команду
     */

    void execute(Console consoleManager);
}
