package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 * Команда, выполняющая скрипт
 */

public class ExecuteScript {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь",возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public ExecuteScript(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "execute_script";
        description = "считать и исполнить скрипт из указанного файла.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, вызывающий и управляющий выполнением скрипта
     */

    public static void execute(Console consoleManager, Map<String, Object> map, LinkedList<Dragon> list, Map<String, String> info) {
        consoleManager.scriptIsWork = true;
        String repeatName = "";
        boolean checkRepeat = false;
        if (consoleManager.scriptsNames.size() == 0) {
            consoleManager.scriptsNames.add(consoleManager.strArg);
        } else {
            for (int i = 0; i < consoleManager.scriptsNames.size(); i++) {
                if (consoleManager.scriptsNames.get(i).equals(consoleManager.strArg)) {
                    repeatName = consoleManager.scriptsNames.get(i);
                    checkRepeat = true;
                    break;
                }
            }
            if (!checkRepeat) {
                consoleManager.scriptsNames.add(consoleManager.strArg);
            }
        }
        System.out.println("\nПроизводится выполнение скрипта из файла: " + consoleManager.scriptsNames.getLast());
        if (!checkRepeat) {
            try {
                consoleManager.fileScanner = new Scanner(new File(consoleManager.strArg));
                consoleManager.filesScanners.add(consoleManager.fileScanner);
                Console.getMainConsole(consoleManager, map, list, info);
                if (consoleManager.scriptsNames.size() == 0) {
                    consoleManager.scriptIsWork = false;
                    consoleManager.filesScanners.removeLast();
                } else {
                    consoleManager.fileScanner = consoleManager.filesScanners.getLast();
                    consoleManager.filesScanners.removeLast();
                    consoleManager.scriptIsWork = true;
                    Console.getMainConsole(consoleManager, map, list, info);
                    System.out.println("\nЧтение скрипта из " + consoleManager.scriptsNames.getLast() + " окончено.");
                    consoleManager.scriptsNames.removeLast();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл отсутствует или нет достаточных прав на его чтение. Скрипт не может быть выполнен");
            }
        } else {
            System.out.println("Рекурсивный вызов скрипта " + repeatName + ", его выполнение будет прервано...");
            consoleManager.scriptIsWork = false;
        }
    }
}
