package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.object.Dragon;

import java.util.LinkedList;
import java.util.Map;

/**
 * Команда, выводящая все элементы коллекции в строковом представлении
 */

public class Show {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public Show(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "show";
        description = "вывод элементов коллекции в строковом представлении.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, выводящий все элементы коллекции в строковом виде(понятном для пользователя)
     *
     * @param size количество элементов в коллекции
     */

    public static void execute(LinkedList<Dragon> list, int size) {
        System.out.println("\nВот все элементы коллекции:");
        for (int i = 0; i < size; i++) {
            list.get(i).getFields();
            System.out.println("\n");
        }
        if (size == 0) {
            System.out.println("В коллекции нет элементов.");
        }
    }
}
