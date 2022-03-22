package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.LinkedList;
import java.util.Map;

/**
 * Команда, удаляющая объект по заданному id
 */

public class RemoveById {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public RemoveById(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "remove_by_id";
        description = "удалить элемент из коллекции по его id.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, удаляющий объект по введенному пользователем id
     */

    public static void execute(Console consoleManager, LinkedList<Dragon> list) {
        if (list.size() == 0) {
            System.out.println("Коллекция пуста.");
        }
        for (int i = 0; i < consoleManager.all_id.size(); i++) {
            if (consoleManager.all_id.get(i) == consoleManager.digitArg) {
                consoleManager.all_id.remove(i);
                break;
            } else if ((i == consoleManager.all_id.size() - 1) && consoleManager.all_id.get(i) != consoleManager.digitArg) {
                System.out.println("\nОбъект с таким id отсутствует.");
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == (Integer) consoleManager.digitArg) {
                list.remove(i);
                System.out.println("\nОбъект успешно удалён.");
                break;
            }
        }
    }
}
