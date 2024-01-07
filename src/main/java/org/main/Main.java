package org.main;

import org.main.Services.ProcessText;
import org.main.Singleton.ReadWriteFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ReadWriteFile readWriteFile =  ReadWriteFile.getInstance();
    static ProcessText processText = new ProcessText();
    static List<String> listOfLines;
    static String readFrom = "src/main/resources/read_from_here.txt";
    static String writeTo = "src/main/resources/written_here.txt";
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        printInstructions();
        System.out.println("Hello, please insert a number:");

        String opt = "0";
        while (Integer.parseInt(opt) != 99){
            printMenu();
            opt = sc.nextLine();
            switch (Integer.parseInt(opt)){
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
                case 99:
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }

    }

    public static void printInstructions(){
        System.out.println("This app processes text files.\n" +
                "Put the file in resources folder and name it \"read_from_here.txt\"");
    }
    public static void printMenu() {
        System.out.println(
                "99 - Quit\n" +
                "0  - About App + Instructions\n" +
                "1  - ReplaceDiacritic\n" +
                "2  - Order alphabetically\n" +
                "3  - abc_abc to abcAbc");
    }

    public static void replaceDiacritics() throws IOException {
        readAndShowBefore();
        listOfLines = processText.replaceDiacritics(listOfLines);
        showAfterAndWrite();
    }

    public static void orderAlphabetically() throws IOException{
        readAndShowBefore();
        Collections.sort(listOfLines);
        showAfterAndWrite();
    }

    public static void readAndShowBefore(){
        try {listOfLines = readWriteFile.readFile(readFrom);} catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Before: " + listOfLines);
    }

    public static void showAfterAndWrite() throws IOException {
        System.out.println("After:  " + listOfLines);
        readWriteFile.writeFile(writeTo,listOfLines);
        listOfLines.clear();

    }
}