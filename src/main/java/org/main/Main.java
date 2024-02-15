package org.main;

import org.main.Services.ProcessText;
import org.main.Singleton.ReadWriteFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ReadWriteFile readWriteFile = ReadWriteFile.getInstance();
    static ProcessText processText = new ProcessText();
    static List<String> listOfLines;
    static final String readFrom = "src/main/resources/read_from_here.txt";
    static final String writeTo = "src/main/resources/written_here.txt";

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        printInstructions();
        System.out.println("Hello, please insert a number:");

        String opt = "0";
        while (Integer.parseInt(opt) != 99) {
            printMenu();
            opt = sc.nextLine();
            switch (Integer.parseInt(opt)) {
                case 0:
                    printInstructions();
                    printMenu();
                    break;
                case 1:
                    replaceDiacritics();
                    break;
                case 2:
                    orderAlphabetically();
                    break;
                case 3:
                    extractLinesThatContain(sc);
                    break;
                case 4:
                    convertFieldsInComments();
                    break;
                case 5:
                    snakeCaseToCamelCase();
                    break;
                case 6:
                    appendingToExistingFile();
                    break;
                case 7:
                    replaceWithNewRow(sc);
                    break;
                case 8:
                    formatNumbersForExcel();
                    break;
                case 9:
                    sumOfNumbers();
                    break;
                case 10:
                    trimAllBeforeString(sc);
                    break;
                case 11:
                    trimAllAfterString(sc);
                    break;
                case 12:
                    keepRowsThatContain(sc);
                    break;
                case 13:
                    addInQuotes();
                    break;
                case 99:
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
        sc.close();

    }

    public static void printInstructions() {
        System.out.println("This app processes text files.\n" +
                "Put the file in resources folder and name it \"read_from_here.txt\"");
    }

    public static void printMenu() {
        System.out.println(
                "99 - Quit\n" +
                        "0  - About App + Instructions\n" +
                        "1  - ReplaceDiacritic\n" +
                        "2  - Order alphabetically\n" +
                        "3  - Extract lines that contain, insert:\n" +
                        "4  - From create sql script to comments in code for organised code\n" +
                        "5  - Transform abc_abc to abcAbc (snake_case to camelCase)\n" +
                        "6  - Append something to \"written_here.txt\"\n" +
                        "7  - Replace char/String with new row\n" +
                        "8  - Format numbers for excel: '.'\n" +
                        "9  - Sum of list of numbers\n" +
                        "10 - Trim all before string: \n" +
                        "11 - Trim all after string: \n" +
                        "12 - Keep only rows that contain:\n" +
                        "13 - Add all in \"\" + , except las one");
    }


    public static void addInQuotes() throws IOException {
        readAndShowBefore();
        listOfLines = processText.addInQuotes(listOfLines);
        showAfterAndWrite();
    }

    private static void keepRowsThatContain(Scanner sc) throws IOException{
        readAndShowBefore();
        System.out.println("Insert what should the rows contain:");
        String opt = sc.nextLine();
        listOfLines = processText.keepRowsThatContain(listOfLines, opt);
        showAfterAndWrite();
    }

    private static void trimAllBeforeString(Scanner sc) throws IOException{
        readAndShowBefore();
        System.out.println("Insert text that should be at the beginning of row:");
        String opt = sc.nextLine();
        listOfLines = processText.trimAllBeforeString(listOfLines, opt);
        showAfterAndWrite();
    }

    private static void trimAllAfterString(Scanner sc) throws IOException{
        readAndShowBefore();
        System.out.println("Insert text from which the rest should be excluded:");
        String opt = sc.nextLine();
        listOfLines = processText.trimAllAfterString(listOfLines, opt);
        showAfterAndWrite();
    }

    public static void sumOfNumbers() throws IOException {
        readAndShowBefore();
        listOfLines = processText.sumOfNumbers(listOfLines);
        showAfterAndWrite();
    }

    public static void formatNumbersForExcel() throws IOException {
        readAndShowBefore();
        listOfLines = processText.formatNumbersForExcel(listOfLines);
        showAfterAndWrite();
    }

    public static void replaceWithNewRow(Scanner sc) throws IOException {
        readAndShowBefore();
        System.out.println("Insert what you want to replace with new line:");
        String opt = sc.nextLine();
        listOfLines = processText.replaceWithNewRow(listOfLines, opt);
        showAfterAndWrite();
    }

    public static void snakeCaseToCamelCase() throws IOException {
        readAndShowBefore();
        listOfLines = processText.snakeCaseToCamelCase(listOfLines);
        showAfterAndWrite();
    }

    public static void convertFieldsInComments() throws IOException {
        readAndShowBefore();
        listOfLines = processText.convertFieldsInComments(listOfLines);
        showAfterAndWrite();
    }

    public static void extractLinesThatContain(Scanner sc) throws IOException {
        System.out.println("Process: All lines will be ordered alphabetically,\n" +
                "then the ones that contain the inserted text will be filtered.\n" +
                "All duplicate lines will be marked ");
        System.out.println("Insert case sensible text to look for in a line:");
        String opt = sc.nextLine();
        readAndShowBefore();
        Collections.sort(listOfLines);
        listOfLines = processText.extractLinesThatContain(opt, listOfLines);
        showAfterAndWrite();
    }

    public static void replaceDiacritics() throws IOException {
        readAndShowBefore();
        listOfLines = processText.replaceDiacritics(listOfLines);
        showAfterAndWrite();
    }

    public static void orderAlphabetically() throws IOException {
        readAndShowBefore();
        Collections.sort(listOfLines);
        showAfterAndWrite();
    }

    public static void readAndShowBefore() {
        try {
            listOfLines = readWriteFile.readFile(readFrom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Before: " + listOfLines);
    }

    public static void showAfterAndWrite() throws IOException {
        System.out.println("After:  " + listOfLines);
        readWriteFile.writeFile(writeTo, listOfLines);
        listOfLines.clear();
    }

    public static void appendingToExistingFile() throws IOException {
        readWriteFile.appendingToExistingFile(writeTo);
    }
}