package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.Map;

/**
 * Команда добавляющая элемент в коллекцию
 */

public class Add extends Command {

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public Add(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "add";
        description = "добавление нового элемента в коллекцию.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, добавляющий введенный пользователем объект в коллекцию
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     */

    @Override
    public void execute(Console consoleManager) {
        try {
            System.out.println("Для добавления элемента в коллекцию вам нужно ввести поля элемента.");
            String[] arg = new String[10];
            arg[0] = Console.inputName(consoleManager);
            arg[1] = Console.genId(consoleManager);
            arg[2] = Console.inputCoordinate_X(consoleManager);
            arg[3] = Console.inputCoordinate_Y(consoleManager);
            arg[4] = Console.genCreationDate();
            arg[5] = Console.inputAge(consoleManager);
            arg[6] = Console.inputDescription(consoleManager);
            arg[7] = Console.inputSpeaking(consoleManager);
            arg[8] = Console.inputType(consoleManager);
            arg[9] = Console.inputCave(consoleManager);
            Dragon newDragon = new Dragon(arg[0], arg[1], arg[2], arg[3], arg[4], arg[5], arg[6], arg[7], arg[8], arg[9], consoleManager);
            consoleManager.list.add(newDragon);
            System.out.println("Объект успешно добавлен в коллекцию.");
        } catch (Exception e) {
            System.out.println("\nПереход обратно к основной консоли.");
        }
    }
}

