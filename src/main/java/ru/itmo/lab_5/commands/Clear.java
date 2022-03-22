package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.LinkedList;
import java.util.Map;

/**
 * Команда очищающая коллекцию
 */

public class Clear {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь",возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public Clear(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "clear";
        description = "очистить коллекцию.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, очищающий коллекцию
     */

    public static void execute(Console consoleManager, LinkedList<Dragon> list) {
        list.clear();
        consoleManager.all_id.clear();
        System.out.println("\nВсе элементы коллекции удалены.");
    }
}
