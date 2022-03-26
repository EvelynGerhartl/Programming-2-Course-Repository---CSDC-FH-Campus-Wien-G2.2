package at.ac.fhcampuswien;

import java.util.List;
import java.util.Scanner;

public class Menu {

    //private static controller() // AppController Verknüpfung

    private static final String INVALID_INPUT_MESSAGE = "Invalid input message. Please, try again!";
    private static final String EXIT_MESSAGE = "Bye Bye!";

    public static void start() {

    }


    private void handleInput(String input) {
        Scanner scanner = new Scanner(System.in);
        input = scanner.next();

        if (input.equals("a")) {
            //getTopHeadlinesAustria(); //List<Article>
            printMenu();

        } else if (input.equals("b")) {
            //getAllNewsBitcoin(); // List<Article>
            printMenu();

        } else if (input.equals("y")) {
            //getArticleCount(); // : int;
            printMenu();

        } else if (input.equals("q")) {
            printExitMessage();

        } else {
            printInvalidInputMessage();
        }
    }

    private static void getArticleCount(AppController ctrl){


    } //(AppController ctrl)
    private static void getTopHeadlinesAustria(AppController ctrl){} //(AppController ctrl)

    private static void getAllNewsBitcoin(AppController ctrl){} //(AppController ctrl)

    private static void printExitMessage(){
        System.out.println(EXIT_MESSAGE);
    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }


    private static void printMenu() {
        System.out.println("************************");
        System.out.println("*Welcome to NewsApp*");
        System.out.println("Enter what you want to do: ");
        System.out.println("a: Get the top headlines for Austria");
        System.out.println("b: Get all the news about Bitcoin");
        System.out.println("y: Count articles");
        System.out.println("q: Quit program");
    }
}

