package ru.itmo.lab_5.main;

import org.xml.sax.SAXException;
import ru.itmo.lab_5.commands.*;
import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.console.Parser;
import ru.itmo.lab_5.object.Dragon;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Главный класс, запускающий программу
 */

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Добро пожаловать в моё консольное приложение.");
        System.out.println("help - справка по всем командам.");
        Map<String, Object> map = new HashMap<>();
        Map<String, String> info = new HashMap<>();
        LinkedList<Dragon> list = new LinkedList<>();
        Console consoleManager = new Console();
        Parser.parseFile(list, consoleManager);
        new Help(info, map);
        new Info(info, map);
        new Add(info, map);
        new AddIfMax(info, map);
        new Clear(info, map);
        new ExecuteScript(info, map);
        new Exit(info, map);
        new FilterGreaterDescription(info, map);
        new FilterLessCave(info, map);
        new PrintFieldSpeaking(info, map);
        new RemoveById(info, map);
        new RemoveFirst(info, map);
        new RemoveGreater(info, map);
        new Save(info, map);
        new Show(info, map);
        new UpdateId(info, map);
        Collections.sort(list);
        Console.getMainConsole(consoleManager, map, list, info);
        consoleManager.fileScanner.close();
    }
}