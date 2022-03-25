package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

import java.util.Map;

/**
 * Команда, прекращающая выполнение программы
 */

public class Exit extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь",возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public Exit(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "exit";
        description = "завершить программу(без сохранения в файл).";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, отвечающий за окончание работы консоли
     */
    @Override
    public void execute(Console consoleManager) {
        System.out.println("Вы выполнили выход из программы.");
        consoleManager.consNotWork = true;
    }
}
