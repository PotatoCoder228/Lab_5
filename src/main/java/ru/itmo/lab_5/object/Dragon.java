package ru.itmo.lab_5.object;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, описывающий дракона.
 * Объекты класса являются элементами коллекции
 */

public class Dragon implements Comparable<Dragon> {
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final int age; //Значение поля должно быть больше 0
    private final String description; //Поле может быть null
    private final Boolean speaking; //Поле может быть null
    private final DragonType type; //Поле может быть null
    private final DragonCave cave; //Поле не может быть null

    /**
     * Конструктор, задающий параметры для создания дракона
     *
     * @param nm           имя
     * @param id           уникальный номер
     * @param x_1          координата по Ox
     * @param y_1          координата по Oy
     * @param creationDate дата создания
     * @param age          возраст
     * @param description  описание
     * @param speaking     способность говорить
     * @param type         тип дракона
     * @param cave         глубина пещеры
     */

    public Dragon(String nm, String id, String x_1, String y_1, String creationDate, String age, String description, String speaking, String type, String cave, Console consoleManager) {
        if (!(nm.equals("null")) && nm.length() > 0) {
            this.name = nm;
        } else {
            throw new WrongNameException("У объекта в файле отсутствует имя.");
        }
        try {
            if (!(id.equals("null"))) {
                if (consoleManager.all_id.size() > 0) {
                    this.id = Integer.parseInt(id);
                    for (int i = 0; i < consoleManager.all_id.size(); i++) {
                        if (this.id == (int) consoleManager.all_id.get(i)) {
                            throw new WrongIdException("Объект с таким id уже есть в коллекции.");
                        }
                    }
                    consoleManager.all_id.add(this.id);
                } else {
                    this.id = Integer.parseInt(id);
                    consoleManager.all_id.add(this.id);
                }
            } else {
                throw new WrongIdException("У объекта отсутствует id.");
            }
        } catch (NumberFormatException e) {
            throw new WrongIdException("В поле id нечисловое значение.");
        }
        {
            if (!(y_1.equals("null"))) {
                if (x_1.equals("null")) {
                    consoleManager.all_id.removeLast();
                    throw new WrongCoordinatesException("У объекта отсутствует координата по оси Ox.");
                }
                long x = Integer.parseInt(x_1);
                Double y = Double.parseDouble(y_1);
                this.coordinates = new Coordinates(x, y, consoleManager);
            } else {
                consoleManager.all_id.removeLast();
                throw new WrongCoordinatesException("У объекта отсутствует координата по оси Oy.");
            }
        }
        {
            try {
                if (!(creationDate.equals("null"))) {
                    String[] date_and_time = creationDate.split("\\s+");
                    String[] time = date_and_time[0].split(":");
                    String[] date = date_and_time[1].split("\\.");
                    int day = Integer.parseInt(date[0]);
                    int month = Integer.parseInt(date[1]);
                    int year = Integer.parseInt(date[2]);
                    int hour = Integer.parseInt(time[0]);
                    int minute = Integer.parseInt(time[1]);
                    this.creationDate = LocalDateTime.of(year, month, day, hour, minute);
                } else {
                    consoleManager.all_id.removeLast();
                    throw new WrongDateException("У объекта отсутствует дата создания.");
                }
            } catch (NumberFormatException e) {
                throw new WrongDateException("В поле даты некорректное значение.");
            }
        }
        if (Console.isInt(age)) {
            if (Integer.parseInt(age) > 0) {
                this.age = Integer.parseInt(age);
            } else {
                consoleManager.all_id.removeLast();
                throw new WrongAgeException("У объекта возраст меньше 0 или отсутствует.");
            }
        } else {
            consoleManager.all_id.removeLast();
            throw new WrongAgeException("В поле age не числовое значение.");
        }
        this.description = description;
        {
            if (speaking.equals("true") || speaking.equals("false")) {
                this.speaking = speaking.equals("true");
            } else {
                consoleManager.all_id.removeLast();
                throw new WrongSpeakingException("У объекта некорректное поле speaking.");
            }
        }
        try {
            this.type = DragonType.valueOf(type);
        } catch (IllegalArgumentException e) {
            consoleManager.all_id.removeLast();
            throw new WrongTypeException("У объекта некорректный тип.");
        }
        try {
            if (!(cave.equals("null"))) {
                this.cave = new DragonCave(cave, consoleManager);
            } else {
                consoleManager.all_id.removeLast();
                throw new WrongCaveException("Поле cave у объекта отсутствует.");
            }
        } catch (NumberFormatException e) {
            throw new WrongCaveException("Поле cave имеет нечисловое значение.");
        }
    }

    /**
     * Возвращает id дракона
     *
     * @return id
     */

    public Integer getId() {
        return this.id;
    }

    /**
     * Возвращает возраст дракона
     *
     * @return age
     */

    public int getAge() {
        return this.age;
    }

    /**
     * Возвращает способность говорить дракона
     *
     * @return speaking
     */

    public Boolean getSpeaking() {
        return this.speaking;
    }

    /**
     * Возвращает глубину пещеры дракона
     *
     * @return cave
     */

    public float getCave() {
        return this.cave.getDepth();
    }

    /**
     * Возвращает описание дракона
     *
     * @return description
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * Выводит на экран все поля дракона в понятном для пользователя виде
     */

    public void getFields() {
        String[] fields = new String[]{"Имя: " + name, "id: " + id, "Координаты:" + coordinates.getFields(),
                "Дата создания: " + creationDate.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")), "Возраст: " + age, "Описание: " + description, "Способность говорить: " + speaking,
                "Тип: " + type, "Размер пещеры дракона: " + cave.getDepth()};
        for (int i = 0; i < fields.length; i++) {
            if (i == 0) {
                System.out.println(fields[i]);
            } else {
                System.out.println("\t" + fields[i]);
            }
        }
    }

    /**
     * Возвращает массив из всех полей объекта в строковом виде
     *
     * @return fields
     */

    public String[] getAllFields() {
        return new String[]{name, id.toString(), coordinates.getX(),
                coordinates.getY(), creationDate.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")), Integer.toString(age), description, speaking.toString(),
                type.toString(), Float.toString(cave.getDepth())};
    }

    @Override
    public int compareTo(Dragon dragon) {
        return Integer.compare(this.age, dragon.age);
    }
}