package ru.itmo.lab_5.console;

import ru.itmo.lab_5.commands.*;
import ru.itmo.lab_5.object.Dragon;

import java.util.LinkedList;
import java.util.Map;

/**
 * Класс, отвечающий за парсинг команды из переданной строки
 */

public class CommandManager {

    /**
     * Метод, вызывающий команду, введённую пользователем
     */

    public void activate(Console consoleManager, Map<String, Object> map, Object ob, LinkedList<Dragon> list, Map<String, String> info) {
        switch (ob.getClass().toString().substring(29)) {
            case "Info":
                Info.execute(consoleManager, list);
                break;
            case "Help":
                Help.execute(info);
                break;
            case "Show":
                Show.execute(list, list.size());
                break;
            case "Add":
                Add.execute(consoleManager, list);
                break;
            case "Exit":
                Exit.execute(consoleManager);
                break;
            case "UpdateId":
                UpdateId.execute(consoleManager, list);
                break;
            case "RemoveById":
                RemoveById.execute(consoleManager, list);
                break;
            case "Clear":
                Clear.execute(consoleManager, list);
                break;
            case "RemoveFirst":
                RemoveFirst.execute(consoleManager, list, list.size());
                break;
            case "AddIfMax":
                AddIfMax.execute(consoleManager, list);
                break;
            case "RemoveGreater":
                RemoveGreater.execute(consoleManager, list);
                break;
            case "FilterLessCave":
                FilterLessCave.execute(consoleManager, list, list.size());
                break;
            case "FilterGreaterDescription":
                FilterGreaterDescription.execute(consoleManager, list, list.size());
                break;
            case "PrintFieldSpeaking":
                PrintFieldSpeaking.execute(list, list.size());
                break;
            case "Save":
                Save.execute(list);
                break;
            case "ExecuteScript":
                ExecuteScript.execute(consoleManager, map, list, info);
                break;
        }
    }
}
