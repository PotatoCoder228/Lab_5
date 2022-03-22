package ru.itmo.lab_5.commands;

import ru.itmo.lab_5.console.Parser;
import ru.itmo.lab_5.object.Dragon;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

/**
 * Команда, сохраняющая коллекцию в файл
 */

public class Save {
    protected String nameOfCommand;
    protected String description;

    /**
     * Конструктор, задающий параметры для создания объекта
     *
     * @param info "словарь", возвращающий описание команды по ключу
     * @param map  "словарь", возвращающий объекты классов, наследующихся от Command
     */

    public Save(Map<String, String> info, Map<String, Object> map) {
        nameOfCommand = "save";
        description = "сохранить коллекцию в файл.";
        info.put(nameOfCommand, description);
        map.put(nameOfCommand, this);
    }

    /**
     * Метод, сохраняющий коллекцию в файл
     *
     * @param object коллекция
     */

    public static void execute(LinkedList<Dragon> object) {
        try {
            FileOutputStream fos = new FileOutputStream(Parser.path);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            String text = "<Dragons>";
            for (Dragon dragon : object) {
                String[] fields = dragon.getAllFields();
                String begin = "\n\t<Dragon>";
                String name = "\n\t\t<name>" + fields[0] + "</name>";
                String id = "\n\t\t<id>" + fields[1] + "</id>";
                String x = "\n\t\t<coordinate_x>" + fields[2] + "</coordinate_x>";
                String y = "\n\t\t<coordinate_y>" + fields[3] + "</coordinate_y>";
                String date = "\n\t\t<creationDate>" + fields[4] + "</creationDate>";
                String age = "\n\t\t<age>" + fields[5] + "</age>";
                String description = "\n\t\t<description>" + fields[6] + "</description>";
                String speaking = "\n\t\t<speaking>" + fields[7] + "</speaking>";
                String type = "\n\t\t<type>" + fields[8] + "</type>";
                String cave = "\n\t\t<cave>" + fields[9] + "</cave>";
                String end = "\n\t</Dragon>";
                text += begin + name + id + x + y + date + age + description + speaking + type + cave + end;
            }
            text += "\n</Dragons>";
            byte[] buffer = text.getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
            System.out.println("Сохранение успешно проведено.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения.");
        } catch (NullPointerException e) {
            System.out.println("Вы не ввели переменную окружения, в которой лежит путь к файлу.");
            System.out.println("Сохранение невозможно.");
        }
    }
}
