package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.Map;

/**
 * Команда, выводящая поля speaking в порядке убывания
 */

public class PrintFieldSpeaking extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public PrintFieldSpeaking(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "print_field_descending_speaking";
        description = "вывести значения поля speaking всех элементов в порядке убывания.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }


    public void execute(Console consoleManager) {
        System.out.println("Вот все поля speaking элементов коллекции, в порядке убывания:");
        for (int i = consoleManager.list.size() - 1; i >= 0; i--) {
            Dragon getDragon = consoleManager.list.get(i);
            System.out.print("id " + getDragon.getId() + ": " + getDragon.getSpeaking());
            System.out.println("\n");
        }
        if (consoleManager.list.size() == 0) {
            System.out.println("В коллекции нет элементов.");
        }
    }
}
