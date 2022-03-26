package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

import java.util.Map;

/**
 * Команда, удаляющая первый элемент в коллекции
 */

public class RemoveFirst extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public RemoveFirst(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "remove_first";
        description = "удалить первый элемент из коллекции.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, удаляющий первый объект в коллекции
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     */

    public void execute(Console consoleManager) {
        if (consoleManager.list.size() != 0) {
            consoleManager.list.remove(0);
            consoleManager.idList.removeFirst();
            System.out.println("\nПервый элемент коллекции удалён");
        } else {
            System.out.println("\nПервый элемент не может быть удалён - коллекция пуста.");
        }
    }
}
