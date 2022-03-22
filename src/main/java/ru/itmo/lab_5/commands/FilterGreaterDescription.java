package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.LinkedList;
import java.util.Map;

/**
 * Команда-фильтр, выводящая все объекты, поле description которых больше заданного
 */

public class FilterGreaterDescription {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public FilterGreaterDescription(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "filter_greater_than_description";
        description = "вывести элементы, значение поля description которых больше заданного.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, выводящий все элементы коллекции, поле description которых больше заданного
     *
     * @param size размер коллекции
     */

    public static void execute(Console consoleManager, LinkedList<Dragon> list, int size) {
        System.out.println("Вот все элементы коллекции, поле description которых больше заданного:");
        int countStrings = 0;
        for (int i = 0; i < size; i++) {
            if (list.get(i).getDescription().length() > consoleManager.strArg.length()) {
                list.get(i).getFields();
                System.out.println("\n");
                countStrings += 1;
            }
        }
        if (size == 0) {
            System.out.println("В коллекции нет элементов.");
        } else {
            if (countStrings == 0) {
                System.out.println("В коллекции отсутствуют элементы с полем description большим, чем заданное.");
            }
        }
    }
}
