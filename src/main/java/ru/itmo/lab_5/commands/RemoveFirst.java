package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.LinkedList;
import java.util.Map;

/**
 * Команда, удаляющая первый элемент в коллекции
 */

public class RemoveFirst {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public RemoveFirst(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "remove_first";
        description = "удалить первый элемент из коллекции.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, удаляющий первый объект в коллекции
     *
     * @param size размер коллекции
     */

    public static void execute(Console consoleManager, LinkedList<Dragon> list, int size) {
        if (size != 0) {
            list.remove(0);
            consoleManager.all_id.removeFirst();
            System.out.println("\nПервый элемент коллекции удалён");
        } else {
            System.out.println("\nПервый элемент не может быть удалён - коллекция пуста.");
        }
    }
}
