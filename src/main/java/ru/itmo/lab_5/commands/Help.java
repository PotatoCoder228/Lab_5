package ru.itmo.lab_5.commands;

import java.util.Map;

/**
 * Команда, выводящая информацию о командах
 */

public class Help {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public Help(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "help";
        description = "вывод справки по доступным командам.";
        info.put(nameOfCommand, description);//кладём описание
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, выводящий информацию о командах
     *
     * @param info "словарь" с описанием команд
     */

    public static void execute(Map<String, String> info) {
        String string = info.entrySet().toString().replace(",", "\n");
        System.out.println("\nВот список команд:");
        System.out.println(" " + string.replace("=", ":").substring(1, string.length() - 1));
    }
}
