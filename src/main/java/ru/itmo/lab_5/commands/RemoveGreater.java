package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.LinkedList;
import java.util.Map;

/**
 * Команда, удаляющая первый элемент в коллекции
 */

public class RemoveGreater {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public RemoveGreater(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "remove_greater";
        description = "удалить из коллекции все элементы, превышающие заданный.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, удаляющий все элементы коллекции, больше данного
     */

    public static void execute(Console consoleManager, LinkedList<Dragon> list) {
        try {
            System.out.println("Для того, чтобы удалить все элементы коллекции, больше данного,\nвам нужно ввести значения его полей.");
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
            int maxIndex = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAge() > newDragon.getAge()) {
                    maxIndex = i;
                    break;
                }
            }
            while (list.size() != maxIndex) {
                list.remove(maxIndex);
            }
            System.out.println("Все элементы коллекции больше заданного удалены.");
        } catch (Exception e) {
            System.out.println("\nПереход обратно к основной консоли.");
        }
    }
}
