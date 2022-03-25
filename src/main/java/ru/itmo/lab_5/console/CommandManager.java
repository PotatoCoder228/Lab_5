package ru.itmo.lab_5.console;

import ru.itmo.lab_5.commands.Command;

/**
 * Класс, отвечающий за парсинг команды из переданной строки
 */

public class CommandManager {
    public void activate(Console consoleManager, Command command) {
        command.execute(consoleManager);
    }
}
