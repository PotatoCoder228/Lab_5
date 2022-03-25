package ru.itmo.lab_5.console;

import ru.itmo.lab_5.commands.Command;

/**
 * Класс, отвечающий за парсинг команды из переданной строки
 */

public class CommandManager {

    /**
     * Метод, вызывающий нужную консольную команду
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @param command объект класса команды
     */

    public void activate(Console consoleManager, Command command) {
        command.execute(consoleManager);
    }
}
