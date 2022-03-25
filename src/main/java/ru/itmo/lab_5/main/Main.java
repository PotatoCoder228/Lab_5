package ru.itmo.lab_5.main;

import org.xml.sax.SAXException;
import ru.itmo.lab_5.commands.*;
import ru.itmo.lab_5.console.Console;
import ru.itmo.lab_5.console.Parser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;

/**
 * Главный класс, запускающий программу
 */

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Добро пожаловать в моё консольное приложение.");
        System.out.println("help - справка по всем командам.");
        Console consoleManager = new Console();
        Parser.parseFile(consoleManager.list, consoleManager);
        new Help(consoleManager.info, consoleManager.map);
        new Info(consoleManager.info, consoleManager.map);
        new Add(consoleManager.info, consoleManager.map);
        new AddIfMax(consoleManager.info, consoleManager.map);
        new Clear(consoleManager.info, consoleManager.map);
        new ExecuteScript(consoleManager.info, consoleManager.map);
        new Exit(consoleManager.info, consoleManager.map);
        new FilterGreaterDescription(consoleManager.info, consoleManager.map);
        new FilterLessCave(consoleManager.info, consoleManager.map);
        new PrintFieldSpeaking(consoleManager.info, consoleManager.map);
        new RemoveById(consoleManager.info, consoleManager.map);
        new RemoveFirst(consoleManager.info, consoleManager.map);
        new RemoveGreater(consoleManager.info, consoleManager.map);
        new Save(consoleManager.info, consoleManager.map);
        new Show(consoleManager.info, consoleManager.map);
        new UpdateId(consoleManager.info, consoleManager.map);
        Collections.sort(consoleManager.list);
        Console.getMainConsole(consoleManager);
    }
}