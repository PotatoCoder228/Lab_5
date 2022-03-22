package ru.itmo.lab_5.object;


import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.exceptions.WrongCoordinatesException;

/**
 * Класс, описывающий координаты x и y
 */

public class Coordinates {
    private final long x;//Максимальное значение поля: 436
    private final Double y;//Максимальное значение поля: 101, Поле не может быть null

    /**
     * Конструктор, задающий координаты
     *
     * @param i координата x
     * @param k координата y
     */

    Coordinates(long i, Double k, Console consoleManager) {
        if (i <= 436) {
            if (k <= 101) {
                this.x = i;
                this.y = k;
            } else {
                consoleManager.all_id.removeLast();
                throw new WrongCoordinatesException("Координата объекта по оси Oy больше 101.");
            }
        } else {
            consoleManager.all_id.removeLast();
            throw new WrongCoordinatesException("Координата объекта по оси Ox больше 436.");
        }
    }

    /**
     * Возвращает поля класса в понятном для пользователя виде
     *
     * @return fields
     */

    public String getFields() {
        String fields = "(" + x + ";" + y.intValue() + ")";
        return fields;
    }

    /**
     * Возвращает координату x
     *
     * @return x
     */

    public String getX() {
        String x = Long.toString(this.x);
        return x;
    }

    /**
     * Возвращает координату y
     *
     * @return y
     */

    public String getY() {
        String y = Double.toString(this.y);
        return y;
    }
}
