package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

import java.util.Map;

/**
 * Команда-фильтр, выводящая все объекты, поле cave которых меньше заданного
 */

public class FilterLessCave extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public FilterLessCave(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "filter_less_than_cave";
        description = "вывести все элементы, значение поля cave которых меньше заданного.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, выводящий все элементы коллекции, поле cave которых меньше заданного
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     */

    public void execute(Console consoleManager) {
        System.out.println("Вот все элементы коллекции, поле cave которых меньше заданного:");
        int countStrings = 0;
        for (int i = 0; i < consoleManager.list.size(); i++) {
            if (consoleManager.list.get(i).getCave() < (float) consoleManager.digitArg) {
                consoleManager.list.get(i).getFields();
                System.out.println("\n");
                countStrings += 1;
            }
        }
        if (consoleManager.list.size() == 0) {
            System.out.println("В коллекции нет элементов.");
        } else {
            if (countStrings == 0) {
                System.out.println("В коллекции отсутствуют элементы с полем cave меньшим, чем заданное.");
            }
        }
    }
}
