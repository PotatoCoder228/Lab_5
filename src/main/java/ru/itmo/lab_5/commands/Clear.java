package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

import java.util.Map;

/**
 * Команда очищающая коллекцию
 */

public class Clear extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public Clear(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "clear";
        description = "очистить коллекцию.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, очищающий коллекцию
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     */

    public void execute(Console consoleManager) {
        consoleManager.list.clear();
        consoleManager.all_id.clear();
        System.out.println("\nВсе элементы коллекции удалены.");
    }
}
