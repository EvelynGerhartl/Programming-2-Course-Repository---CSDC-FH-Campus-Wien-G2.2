package at.ac.fhcampuswien.ui;

import at.ac.fhcampuswien.controllers.AppController;
import at.ac.fhcampuswien.controllers.NewsAPIException;
import at.ac.fhcampuswien.downloader.ParallelDownloader;
import at.ac.fhcampuswien.downloader.SequentialDownloader;
import at.ac.fhcampuswien.models.Article;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String INVALID_INPUT_MESSAGE = "No valid input. Try again";
    private static final String EXIT_MESSAGE = "Bye bye!";
    private AppController controller;
    private static Menu instance;           // Singleton (private static for storing instance)

    private Menu() {
    }                       //private class constructor

    public static Menu getInstance() {      //public static creation method (to get the instance instead of using direct calls)
        if (instance == null)
            instance = new Menu();
        return instance;
    }

    public void start() {
        String input;
        controller = AppController.getInstance();       //"new" keyword cannot be used (private constructor), class can only have 1 single instance

        do {
            printMenu();
            input = readLine();
            handleInput(input);
        } while (!input.equals("q"));
    }

    private void handleInput(String input) {
        switch (input) {
            case "a" -> getTopHeadlinesAustria(controller);
            case "b" -> getAllNewsBitcoin(controller);
            case "y" -> getArticleCount(controller);
            case "q" -> printExitMessage();
            case "c" -> getProviderWithMostArticles();
            case "d" -> getLongestNameOfAuthors();
            case "e" -> getCountArticlesNYTimes();
            case "f" -> getArticlesShorterThan();
            case "g" -> sortArticlesByContentLength();
            case "h" -> downloadURLs();
            default -> printInvalidInputMessage();
        }
    }

    private void downloadURLs() {
        try {
            // current time stamp before Sequential Downloader and after, difference: how long it took
            long timeBeforeSequential = System.currentTimeMillis();
            int resultSequential = controller.downloadURLs(new SequentialDownloader());
            long timeAfterSequential = System.currentTimeMillis();
            long differenceSequential = timeAfterSequential - timeBeforeSequential;
            long differenceSequentialSeconds = differenceSequential / 1000;


            // TODO print time in ms it took to download URLs sequentially >>> DONE!
            System.out.println("The time it took to download " + resultSequential + " articles SEQUENTIALLY is: " + differenceSequential + " milliseconds. (which is around " + differenceSequentialSeconds + " seconds)");


            // TODO implement the process() function in ParallelDownloader class >>> DONE!
            long timeBeforeParallel = System.currentTimeMillis();
            int resultParallel = controller.downloadURLs(new ParallelDownloader());
            long timeAfterParallel = System.currentTimeMillis();
            long differenceParallel = timeAfterParallel - timeBeforeParallel;
            long differenceParallelSeconds = differenceParallel / 1000;


            // TODO print time in ms it took to download URLs parallel >>> DONE!
            System.out.println("The time it took to download " + resultParallel + " articles PARALLELLY is: " + differenceParallel + " milliseconds. (which is around " + differenceParallelSeconds + " seconds)");


        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getProviderWithMostArticles() {
        try {
            System.out.println(controller.getProviderWithMostArticles());
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getLongestNameOfAuthors() {
        try {
            System.out.println(controller.getLongestNameOfAuthors());
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getCountArticlesNYTimes() {
        try {
            System.out.println(controller.getCountArticlesNYTimes());
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getArticlesShorterThan() {
        try {
            List<Article> articles = controller.getArticlesShorterThan(15);
            if (articles.size() > 0) {
                articles.forEach(System.out::println);
            } else {
                System.out.println("No articles.");
            }
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sortArticlesByContentLength() {
        try {
            List<Article> sortedArticles = controller.sortArticlesByContentLength();
            sortedArticles.forEach(System.out::println);
        } catch (NewsAPIException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getArticleCount(AppController controller) {
        System.out.println("Number of articles: " + controller.getArticleCount());
    }

    private void getTopHeadlinesAustria(AppController controller) {
        List<Article> articleList = controller.getTopHeadlinesAustria();

        for (Article a : articleList) {
            System.out.println(a);
        }
    }

    private void getAllNewsBitcoin(AppController controller) {
        System.out.println(controller.getAllNewsBitcoin());
    }

    public static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    private static void printMenu() {
        String text = """
                *****************************
                *   Welcome to NewsApp   *
                *****************************
                Enter what you wanna do:
                a: Get top headlines austria
                b: Get all news about bitcoin
                y: Count articles
                q: Quit program
                c: Get provider with most articles
                d: Get longest author name
                e: Count articles from NY Times
                f: Get articles with short title
                g: Sort articles by content length
                h: Download URLs
                """;

        System.out.println(text);
    }

    private static String readLine() {
        String value;
        Scanner scanner = new Scanner(System.in);
        value = scanner.nextLine();
        return value.trim();
    }

}
