package ru.itmo.lab_5.console;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import ru.itmo.lab_5.object.Dragon;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для чтения из файла
 */

public class Parser {

    /**
     * Строка, хранящая путь к файлу для чтения и сохранения коллекции
     */

    public static String path;

    /**
     * Метод, загружающий объекты из файла в коллекцию
     *
     * @param list коллекция
     * @throws ParserConfigurationException Ошибка парсинга, некорректные теги
     * @throws SAXException                 Ошибка при анализе данных из файла
     * @throws IOException                  Ошибка чтения
     */

    public static void parseFile(LinkedList<Dragon> list, Console consoleManager) throws ParserConfigurationException, SAXException, IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            System.out.print("\nВведите переменную окружения, откуда читать и куда сохранять коллекцию(exit - выход из ввода):");
            Scanner scanPath = new Scanner(System.in);
            String exit = scanPath.nextLine();
            cycle:
            if (!exit.equals("exit")) {
                Parser.path = System.getenv(exit);
                String[] checkPaths = path.split(";");
                path = path.replace(";", "");
                while ((checkPaths.length > 1) || !(path.startsWith(".xml", path.length() - 4))) {
                    if (checkPaths.length > 1) {
                        System.out.print("\nВ этой переменной содержится более 1 пути к файлам, укажите другую:");
                    } else {
                        System.out.print("\nВы указали путь не на XML-файл. Введите другую переменную окружения:");
                    }
                    path = scanPath.nextLine();
                    if (!(path.equals("exit"))) {
                        checkPaths = System.getenv(path).split(";");
                        path = System.getenv(path).replace(";", "");
                    } else {
                        System.out.println("\nВы вышли из ввода переменной окружения. Вы не сможете сохранять коллекцию в файл.");
                        System.out.println("Для выхода из программы введите следующей командой exit.");
                        consoleManager.consNotWork = true;
                        break cycle;
                    }
                }
                path = path.replace(";", "");
                Document document = builder.parse(new File(path));
                String[] tags = new String[]{"name", "id", "coordinate_x", "coordinate_y", "creationDate", "age", "description", "speaking", "type", "cave"};
                String[] values = new String[10];
                int counter = document.getDocumentElement().getElementsByTagName("Dragon").getLength();
                for (int k = 0; k < counter; k++) {
                    for (int i = 0; i < tags.length; i++) {
                        try {
                            NodeList dragonElements = document.getDocumentElement().getElementsByTagName(tags[i]);
                            String value = dragonElements.item(k).getFirstChild().toString();
                            values[i] = value.substring(8, value.length() - 1);
                        } catch (NullPointerException e) {
                            values[i] = "null";
                        }
                    }
                    try {
                        Dragon newDragon = new Dragon(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], consoleManager);
                        list.add(newDragon);
                    } catch (Exception e) {
                        System.out.println("В объекте с id " + values[1] + " ошибка, он не будет добавлен в коллекцию.");
                    }
                }
            } else {
                System.out.println("\nВы вышли из ввода переменной окружения. Вы не сможете сохранять коллекцию в файл.");
                System.out.println("Для выхода из программы введите следующей командой exit.");
                consoleManager.consNotWork = true;
            }
        } catch (FileNotFoundException e) {
            try {
                File file = new File(path);
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(path);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                String text = "<Dragons>\n</Dragons>";
                byte[] buffer = text.getBytes();
                bos.write(buffer, 0, buffer.length);
                bos.flush();
                System.out.println("Файл отсутствовал, но был создан.");
            } catch (IOException ex) {
                System.out.println("\nОшибка чтения. Чтение и сохранение в файл невозможно." +
                        "\nПроверьте переменную окружения на корректность и перезапустите программу.");
                System.out.println("Чтобы выйти из программы, введите команду exit.");
            }
        } catch (SAXParseException e) {
            FileOutputStream fos = new FileOutputStream(path);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            String text = "<Dragons>\n</Dragons>";
            byte[] buffer = text.getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
            System.out.println("\nФайл был некорректен. Все нужные поправки внесены, файл полностью очищен. 0 объектов будет добавлено в коллекцию.");
        } catch (UnsupportedEncodingException e) {
            System.out.println("\nУ файла неподдерживаемая кодировка. Пожалуйста, измените её и попробуйте ввести переменную окружения снова.");
            Parser.parseFile(list, consoleManager);
        } catch (AccessDeniedException e) {
            System.out.println("\nНа файл с информацией о коллекции недостаточно прав доступа");
            System.out.println("Дайте необходимые права и попробуйте ввести переменную окружения снова.");
            Parser.parseFile(list, consoleManager);
        } catch (NullPointerException e) {
            System.out.println("\nНекорректная переменная окружения.");
            Parser.parseFile(list, consoleManager);
        } catch (IOException ex) {
            System.out.println("Ошибка чтения. Проверьте файл и попробуйте ввести переменную окружения снова.");
            Parser.parseFile(list, consoleManager);
        } catch (SAXException e) {
            System.out.println("Ошибка при анализе данных в файле. Проверьте файл на корректность и попробуйте ввести переменную окружения снова.");
            Parser.parseFile(list, consoleManager);
        } catch (NoSuchElementException e) {
            System.out.println("Некорректное имя переменной окружения. Попробуйте ввести переменную снова.");
            Parser.parseFile(list, consoleManager);
        }
    }
}
