package at.ac.fhcampuswien;

import java.util.Scanner;

public class Menu {

    //private static controller() // AppController Verknüpfung

    private static final String INVALID_INPUT_MESSAGE = "Invalid input message. Please, try again!";
    private static final String EXIT_MESSAGE = "Bye Bye!";

    public static void start() {}


    static void handleInput(String input) { //handleInput is package-private
        Scanner scanner = new Scanner(System.in);
        input = scanner.next();

        if (input.equals("a")) {
            //getTopHeadlinesAustria(); List<Article>
            System.out.println(printMenu());
        } else if (input.equals("b")) {
            //getAllNewsBitcoin(): List<Article>
            System.out.println(printMenu());
        } else if (input.equals("y")) {
            //getArticleCount() : int;
            System.out.println(printMenu());
        } else if (input.equals("q")) {
            System.out.println(EXIT_MESSAGE);
        } else {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    private static void getArticleCount(){} //(AppController ctrl)
    private static void getTopHeadlinesAustria(){} //(AppController ctrl)
    private static void getAllNewsBitcoin(){} //(AppController ctrl)

    private static void printExitMessage(){
        System.out.println(EXIT_MESSAGE);
    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }


    static String printMenu() { //printMenu is package-private
        return "************************" + System.lineSeparator() +
                "*Welcome to NewsApp*" + System.lineSeparator() +
                "************************" + System.lineSeparator() +
                "Enter what you want to do: " + System.lineSeparator() +
                "a: Get the top headlines for Austria" + System.lineSeparator() +
                "b: Get all the news about Bitcoin" + System.lineSeparator() +
                "y: Count articles" + System.lineSeparator() +
                "q: Quit program" + System.lineSeparator();
    }}

