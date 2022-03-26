package ru.itmo.lab_5.object;

import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.exceptions.WrongCaveException;

/**
 * Класс, описывающий пещеру дракона
 */

public class DragonCave {
    private final float depth;

    /**
     * Конструктор, задающий координаты
     *
     * @param depth глубина пещеры
     */

    DragonCave(String depth, Console consoleManager) {
        try {
            this.depth = Float.parseFloat(depth);

        } catch (NumberFormatException e) {
            consoleManager.idList.removeLast();
            throw new WrongCaveException("На месте числа в поле cave стоит некорректное значение.");
        }
    }

    /**
     * Возвращает глубину пещеры
     *
     * @return depth
     */

    public float getDepth() {
        return depth;
    }
}