package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

import java.util.Map;

/**
 * Команда, выводящая все элементы коллекции в строковом представлении
 */

public class Show extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public Show(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "show";
        description = "вывод элементов коллекции в строковом представлении.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    public void execute(Console consoleManager) {
        System.out.println("\nВот все элементы коллекции:");
        for (int i = 0; i < consoleManager.list.size(); i++) {
            consoleManager.list.get(i).getFields();
            System.out.println("\n");
        }
        if (consoleManager.list.size() == 0) {
            System.out.println("В коллекции нет элементов.");
        }
    }
}
