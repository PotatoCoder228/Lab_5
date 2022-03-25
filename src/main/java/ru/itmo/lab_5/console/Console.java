package ru.itmo.lab_5.console;

import ru.itmo.lab_5.commands.Command;
import ru.itmo.lab_5.exceptions.*;
import ru.itmo.lab_5.object.Dragon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс для работы с вводимыми в консоль или записанными в скрипт командами и полями
 */

public class Console {
    public boolean ReaderNotWork = false;
    public int digitArg = 0;
    public String strArg = "";
    public LocalDateTime timeNow = LocalDateTime.now();
    public LinkedList<Dragon> list = new LinkedList<>();
    public CommandManager newCom = new CommandManager();
    public boolean scriptIsWork = false;
    public boolean consNotWork = false;
    public Scanner fileScanner;
    public LinkedList<String> scriptsNames = new LinkedList<>();
    public LinkedList<Scanner> filesScanners = new LinkedList<>();
    public Map<String, Command> map = new HashMap<>();
    public Map<String, String> info = new HashMap<>();
    /**
     * LinkedList, хранит все id объектов из текущей коллекции
     */
    public LinkedList<Integer> all_id = new LinkedList<>();

    /**
     * Метод, обрабатывающий и вызывающий команды из консоли, либо из скрипта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     */

    public static void getMainConsole(Console consoleManager) {
        try {
            if (consoleManager.scriptIsWork) {
                consoleManager.ReaderNotWork = false;
                String full_com = "";
                while (!consoleManager.ReaderNotWork) {
                    try {
                        full_com = consoleManager.fileScanner.nextLine();
                        String[] command = full_com.split("\\s+");
                        if (command.length == 2) {
                            if (!command[0].equals("filter_greater_than_description") && !command[0].equals("execute_script")) {
                                consoleManager.digitArg = Integer.parseInt(command[1]);
                            } else {
                                consoleManager.strArg = command[1];
                            }
                            consoleManager.newCom.activate(consoleManager, consoleManager.map.get(command[0]));
                        } else if (command[0].equals("filter_greater_than_description") || command[0].equals("execute_script")) {
                            command = full_com.split("\\s+", 2);
                            consoleManager.strArg = command[1];
                            consoleManager.newCom.activate(consoleManager, consoleManager.map.get(command[0]));
                        } else {
                            consoleManager.newCom.activate(consoleManager, consoleManager.map.get(full_com));
                        }
                    } catch (NullPointerException e) {
                        System.out.println("\nНекорректная команда. Вот она:" + full_com);
                    } catch (NoSuchElementException e) {
                        consoleManager.ReaderNotWork = true;
                    }
                }
            } else {
                consoleManager.consNotWork = false;
                while (!consoleManager.consNotWork) {//TODO
                    try {
                        System.out.print("\nВведите одну из доступных команд:");
                        Scanner scanner = new Scanner(System.in);
                        String full_com = scanner.nextLine();
                        String[] command = full_com.split("\\s+");
                        if (command.length == 2) {
                            if (!command[0].equals("filter_greater_than_description") && !command[0].equals("execute_script")) {
                                try {
                                    consoleManager.digitArg = Integer.parseInt(command[1]);
                                } catch (NumberFormatException e) {
                                    System.out.println("\nВы ввели не команду из списка...");
                                }
                            } else {
                                consoleManager.strArg = command[1];
                            }
                            consoleManager.newCom.activate(consoleManager, consoleManager.map.get(command[0]));
                        } else if (command[0].equals("filter_greater_than_description") || command[0].equals("execute_script")) {
                            command = full_com.split("\\s+", 2);
                            consoleManager.strArg = command[1];
                            consoleManager.newCom.activate(consoleManager, consoleManager.map.get(command[0]));
                        } else {
                            consoleManager.newCom.activate(consoleManager, consoleManager.map.get(command[0]));
                        }
                        if (consoleManager.consNotWork) {
                            scanner.close();
                        }
                    } catch (NullPointerException e) {
                        System.out.println("\nОшибочный ввод. Введите команду из списка, пожалуйста.");
                    }
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nНекорректный ввод. Попробуйте ввести команду снова.");
            Console.getMainConsole(consoleManager);
        }
    }


    /**
     * Метод, обрабатывающий поля объектов, вводимые в консоль во время какой-либо команды
     *
     * @return строку, являющуюся(предварительно) полем класса
     */

    public static String getConsole() {
        boolean checking = false;
        String Field = "";
        while (!checking) {
            try {
                Scanner scanner = new Scanner(System.in);
                Field = scanner.nextLine();
                checking = true;
            } catch (Exception e) {
                System.out.println("Некорректные данные, попробуйте ввести ещё раз:");
            }
        }
        return Field;
    }

    /**
     * Метод, обрабатывающий поля объектов, идущие в скрипте во время какой-либо команды
     *
     * @param scanner текущий сканер(файла или консоли)
     * @return строку, являющуюся(предварительно) полем класса
     */

    public static String getConsole(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Метод, обрабатывающий предполагаемое имя объекта, из консоли или из скрипта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @return строку, которая будет полем String name объекта класса Dragon
     */

    public static String inputName(Console consoleManager) {
        String Name;
        if (consoleManager.scriptIsWork) {
            Name = Console.getConsole(consoleManager.fileScanner);
        } else {
            System.out.print("Введите имя элемента: ");
            Name = Console.getConsole();
            while (Name.equals("")) {
                System.out.print("Повторите ввод имени, оно не может быть null: ");
                Name = Console.getConsole();
            }
        }
        return Name;
    }

    /**
     * Метод, обрабатывающий предполагаемую координату по оси Ox объекта, из консоли или из скрипта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @return строку, которая будет полем Coordinates coordinates объекта класса Dragon
     */

    public static String inputCoordinate_X(Console consoleManager) {
        String x;
        if (consoleManager.scriptIsWork) {
            x = Console.getConsole(consoleManager.fileScanner);
            if (!Console.isInt(x) || Integer.parseInt(x) > 436) {
                throw new WrongCoordinatesException("Некорректное значение Ox.");
            }
        } else {
            System.out.print("Введите координату x(целое число до 436): ");
            x = Console.getConsole();
            while (!Console.isInt(x) || Integer.parseInt(x) > 436) {
                System.out.print("Некорректный ввод, это поле - целое число до 436 включительно: ");
                x = Console.getConsole();
            }
        }
        return x;
    }

    /**
     * Метод, обрабатывающий предполагаемую координату по оси Oy объекта, из консоли или из скрипта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @return строку, которая будет полем Coordinates coordinates объекта класса Dragon
     */

    public static String inputCoordinate_Y(Console consoleManager) {
        String y;
        if (consoleManager.scriptIsWork) {
            y = Console.getConsole(consoleManager.fileScanner);
            if (!Console.isDouble(y) || Double.parseDouble(y) > 101) {
                throw new WrongCoordinatesException("Некорректное значение Oy.");
            }
        } else {
            System.out.print("Введите координату y(число с плавающей точкой до 101, не null): ");
            y = Console.getConsole();
            while (!Console.isDouble(y) || Double.parseDouble(y) > 101) {
                System.out.print("Некорректный ввод, это поле - число с плавающей ТОЧКОЙ до 101 включительно: ");
                y = Console.getConsole();
            }
        }
        return y;
    }

    /**
     * Метод, обрабатывающий предполагаемую возраст объекта, из консоли или из скрипта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @return строку, которая будет полем int age объекта класса Dragon
     */

    public static String inputAge(Console consoleManager) {
        String age;
        if (consoleManager.scriptIsWork) {
            age = Console.getConsole(consoleManager.fileScanner);
            if (!Console.isInt(age) || Integer.parseInt(age) < 1) {
                throw new WrongAgeException("Некорректное значение возраста.");
            }
        } else {
            System.out.print("Введите возраст(значение больше 0): ");
            age = Console.getConsole();
            while (!Console.isInt(age) || Integer.parseInt(age) < 1) {
                System.out.print("Некорректный ввод, это поле - целое число больше 0: ");
                age = Console.getConsole();
            }
        }
        return age;
    }

    /**
     * Метод, обрабатывающий предполагаемое описание объекта, из консоли или из скрипта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @return строку, которая будет полем String description объекта класса Dragon
     */

    public static String inputDescription(Console consoleManager) {
        String string;
        if (consoleManager.scriptIsWork) {
            string = Console.getConsole(consoleManager.fileScanner);
        } else {
            System.out.print("Введите описание: ");
            string = Console.getConsole();
        }
        return string;
    }

    /**
     * Метод, обрабатывающий предполагаемую способность говорить у объекта, из консоли или из скрипта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @return строку, которая будет полем Boolean speaking объекта класса Dragon
     */

    public static String inputSpeaking(Console consoleManager) {
        String string;
        if (consoleManager.scriptIsWork) {
            string = Console.getConsole(consoleManager.fileScanner);
            if (!string.equals("true") && !string.equals("false")) {
                throw new WrongSpeakingException("Некорректное значение поля speaking.");
            }
        } else {
            System.out.print("Введите может ли элемент говорить(true или false): ");
            string = Console.getConsole();
            while (!string.equals("true") && !string.equals("false")) {
                System.out.print("Некорректный ввод, это поле - true или false: ");
                string = Console.getConsole();
            }
        }
        return string;
    }

    /**
     * Метод, обрабатывающий предполагаемый тип объекта, из консоли или из скрипта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @return строку, которая будет полем DragonType type объекта класса Dragon
     */

    public static String inputType(Console consoleManager) {
        String type;
        if (consoleManager.scriptIsWork) {
            type = Console.getConsole(consoleManager.fileScanner);
            if ((!type.equals("AIR")) && (!type.equals("WATER")) && (!type.equals("UNDERGROUND")) && (!type.equals("FIRE"))) {
                throw new WrongTypeException("Некорректный тип объекта.");
            }
        } else {
            System.out.print("Введите тип элемента(WATER,FIRE,AIR или UNDERGROUND): ");
            type = Console.getConsole();
            while ((!type.equals("AIR")) && (!type.equals("WATER")) && (!type.equals("UNDERGROUND")) && (!type.equals("FIRE"))) {
                System.out.print("Некорректный ввод, это поле - WATER,FIRE,AIR или UNDERGROUND: ");
                type = Console.getConsole();
            }
        }
        return type;
    }

    /**
     * Метод, обрабатывающий предполагаемую глубину пещеры объекта, из консоли или из скрипта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @return строку, которая будет полем DragonCave cave объекта класса Dragon
     */

    public static String inputCave(Console consoleManager) {
        String cave;
        if (consoleManager.scriptIsWork) {
            cave = Console.getConsole(consoleManager.fileScanner);
            if (!Console.isDouble(cave)) {
                throw new WrongCaveException("Некорректное значение поля cave.");
            }
        } else {
            System.out.print("Введите размер пещеры(число с плавающей точкой): ");
            cave = Console.getConsole();
            while (!Console.isDouble(cave)) {
                System.out.print("Некорректный ввод, это поле - число с плавающей ТОЧКОЙ: ");
                cave = Console.getConsole();
            }
        }
        return cave;
    }

    /**
     * Метод, генерирующий уникальное поле id объекта
     *
     * @param consoleManager объект, содержащий поля для работы консоли
     * @return строку, которая будет полем int id объекта класса Dragon
     */

    public static String genId(Console consoleManager) {
        int maxId = 0;
        for (int i = 0; i < consoleManager.all_id.size(); i++) {
            if (consoleManager.all_id.get(i) > maxId) {
                maxId = consoleManager.all_id.get(i);
            }
        }
        maxId += 1;
        return Integer.toString(maxId);
    }

    /**
     * Метод, генерирующий дату создания объекта
     *
     * @return строку, которая будет полем java.time.LocalDateTime creationDate объекта класса Dragon
     */

    public static String genCreationDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"));
    }

    /**
     * Метод, обрабатывающий предполагаемую глубину пещеры объекта, из консоли или из скрипта
     *
     * @param s проверяемая строка
     * @return true, если это Double или false в ином случае
     */

    public static boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Метод, обрабатывающий предполагаемую глубину пещеры объекта, из консоли или из скрипта
     *
     * @param s проверяемая строка
     * @return true, если это int или false в ином случае
     */

    public static boolean isInt(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}