package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.LinkedList;
import java.util.Map;

/**
 * Команда-фильтр, выводящая все объекты, поле cave которых меньше заданного
 */

public class FilterLessCave {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public FilterLessCave(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "filter_less_than_cave";
        description = "вывести все элементы, значение поля cave которых меньше заданного.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, выводящий все элементы коллекции, поле cave которых меньше заданного
     *
     * @param size размер коллекции
     */

    public static void execute(Console consoleManager, LinkedList<Dragon> list, int size) {
        System.out.println("Вот все элементы коллекции, поле cave которых меньше заданного:");
        int countStrings = 0;
        for (int i = 0; i < size; i++) {
            if (list.get(i).getCave() < (float) consoleManager.digitArg) {
                list.get(i).getFields();
                System.out.println("\n");
                countStrings += 1;
            }
        }
        if (size == 0) {
            System.out.println("В коллекции нет элементов.");
        } else {
            if (countStrings == 0) {
                System.out.println("В коллекции отсутствуют элементы с полем cave меньшим, чем заданное.");
            }
        }
    }
}
