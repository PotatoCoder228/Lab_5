package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.Map;

/**
 * Команда добавляющая элемент в коллекцию, если он больше максимального
 */

public class AddIfMax extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь",возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public AddIfMax(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "add_if_max";
        description = "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, добавляющий объект в коллекцию, если он больше максимально имеющегося
     */

    public void execute(Console consoleManager) {
        try {
            System.out.println("\nДля того, чтобы  добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции,\nвам нужно ввести значения его полей.");
            String[] fields = new String[10];
            fields[0] = Console.inputName(consoleManager);
            fields[1] = Console.genId(consoleManager);
            fields[2] = Console.inputCoordinate_X(consoleManager);
            fields[3] = Console.inputCoordinate_Y(consoleManager);
            fields[4] = Console.genCreationDate();
            fields[5] = Console.inputAge(consoleManager);
            fields[6] = Console.inputDescription(consoleManager);
            fields[7] = Console.inputSpeaking(consoleManager);
            fields[8] = Console.inputType(consoleManager);
            fields[9] = Console.inputCave(consoleManager);
            Dragon newDragon = new Dragon(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8], fields[9], consoleManager);
            if (consoleManager.list.size() == 0) {
                consoleManager.list.add(newDragon);
                System.out.println("Элемент успешно добавлен в коллекцию.");
            } else {
                if (consoleManager.list.get(consoleManager.list.size() - 1).getAge() < newDragon.getAge()) {
                    consoleManager.list.add(newDragon);
                    System.out.println("Элемент успешно добавлен в коллекцию.");
                } else {
                    System.out.println("Элемент меньше максимального в коллекции.");
                }
            }
        } catch (Exception e) {
            System.out.println("\nПереход обратно к основной консоли.");
        }
    }
}
