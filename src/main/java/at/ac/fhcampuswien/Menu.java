package at.ac.fhcampuswien;

public class Menu {


    public static String printedMenu() {
        return "************************" + System.lineSeparator() +
                "*Welcome to NewsApp*" + System.lineSeparator() +
                "************************" + System.lineSeparator() +
                "Enter what you want to do: " + System.lineSeparator() +
                "a: Get the top headlines for Austria" + System.lineSeparator() +
                "b: Get all the news about Bitcoin" + System.lineSeparator() +
                "y: Count articles" + System.lineSeparator() +
                "q: Quit program" + System.lineSeparator();
    }
}
