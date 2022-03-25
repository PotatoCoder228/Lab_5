package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

import java.util.Map;

/**
 * Команда-фильтр, выводящая все объекты, поле description которых больше заданного
 */

public class FilterGreaterDescription extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public FilterGreaterDescription(Map<String, String> info, Map<String, Command> map) {
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

    public void execute(Console consoleManager) {
        System.out.println("Вот все элементы коллекции, поле description которых больше заданного:");
        int countStrings = 0;
        for (int i = 0; i < consoleManager.list.size(); i++) {
            if (consoleManager.list.get(i).getDescription().length() > consoleManager.strArg.length()) {
                consoleManager.list.get(i).getFields();
                System.out.println("\n");
                countStrings += 1;
            }
        }
        if (consoleManager.list.size() == 0) {
            System.out.println("В коллекции нет элементов.");
        } else {
            if (countStrings == 0) {
                System.out.println("В коллекции отсутствуют элементы с полем description большим, чем заданное.");
            }
        }
    }
}
