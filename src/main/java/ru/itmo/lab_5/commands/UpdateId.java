package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.object.Dragon;

import java.util.Map;

/**
 * Команда, обновляющая элемент коллекции с заданным id
 */

public class UpdateId extends Command {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public UpdateId(Map<String, String> info, Map<String, Command> map) {
        nameOfCommand = "update";
        description = "обновить значение элемента коллекции, id которого равен заданному.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, обновляющий объект, имеющий введенный пользователем id
     */

    public void execute(Console consoleManager) {
        boolean idChecker = false;
        for (int i = 0; i < consoleManager.all_id.size(); i++) {
            if (consoleManager.digitArg == consoleManager.all_id.get(i)) {
                idChecker = true;
                break;
            }
        }
        if (idChecker) {
            System.out.println("Для того, чтобы обновить объект, вам нужно ввести новые значения его полей.");
            try {
                String[] fields = new String[10];
                fields[0] = Console.inputName(consoleManager);
                fields[1] = Integer.toString(consoleManager.digitArg);
                fields[2] = Console.inputCoordinate_X(consoleManager);
                fields[3] = Console.inputCoordinate_Y(consoleManager);
                fields[4] = Console.genCreationDate();
                fields[5] = Console.inputAge(consoleManager);
                fields[6] = Console.inputDescription(consoleManager);
                fields[7] = Console.inputSpeaking(consoleManager);
                fields[8] = Console.inputType(consoleManager);
                fields[9] = Console.inputCave(consoleManager);
                for (int i = 0; i < consoleManager.all_id.size(); i++) {
                    if (consoleManager.all_id.get(i) == consoleManager.digitArg) {
                        consoleManager.all_id.remove(i);
                        break;
                    }
                }
                Dragon newDragon = new Dragon(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8], fields[9], consoleManager);
                consoleManager.list.add(newDragon);
                System.out.println("\nОбъект успешно обновлён.");
            } catch (Exception e) {
                System.out.println("\nПереход обратно к чтению команд.");
            }
        } else {
            System.out.println("\nОбъект с таким id отсутствует.");
        }
    }
}
