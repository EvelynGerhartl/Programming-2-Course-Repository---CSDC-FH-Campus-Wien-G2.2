package at.ac.fhcampuswien;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private AppController ctrl = new AppController();
    private static final String INVALID_INPUT_MESSAGE = "Invalid input message. Please, try again!";
    private static final String EXIT_MESSAGE = "Bye Bye!";

    public void start() {

        printMenu();
        Scanner scanner = new Scanner(System.in);    //Scanner was originally in handleInput (see commented out).
        String input = scanner.next();
        handleInput(input);
    }


    private void handleInput(String input) {

        if (input.equals("a")) {
            getTopHeadlinesAustria(ctrl);

        } else if (input.equals("b")) {
            getAllNewsBitcoin(ctrl);

        } else if (input.equals("y")) {
            getArticleCount(ctrl);

        } else if (input.equals("q")) {
            printExitMessage();

        } else {
            printInvalidInputMessage();
        }
    }

    private void getArticleCount(AppController ctrl){
        System.out.println("Number of articles: " + ctrl.getArticleCount());
    }

    private void getTopHeadlinesAustria(AppController ctrl) {
        System.out.println(ctrl.getTopHeadlinesAustria());

    }
    private void getAllNewsBitcoin(AppController ctrl){
        //System.out.println(ctrl.getAllNewsBitcoin());
    }

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

