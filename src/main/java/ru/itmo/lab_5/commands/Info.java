package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;

import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Команда, выводящая информацию о коллекции
 */

public class Info extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public Info(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "info";
        description = "выводит информацию о коллекции.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    public void execute(Console consoleManager) {
        System.out.println("\nИнформация о коллекции:");
        System.out.println("\tТип коллекции: LinkedList");
        System.out.println("\tВремя создания коллекции: " + consoleManager.timeNow.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")));
        System.out.println("\tКоличество элементов в коллекции: " + consoleManager.list.size());
    }
}
