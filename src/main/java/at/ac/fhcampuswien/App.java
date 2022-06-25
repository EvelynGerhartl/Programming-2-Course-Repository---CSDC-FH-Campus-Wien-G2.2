package at.ac.fhcampuswien;

import at.ac.fhcampuswien.ui.Menu;

public class App {
    private static Menu menu;
    public static void main(String[] args) {
        menu= Menu.getInstance(); //"new" keyword cannot be used (private constructor), class can only have 1 single instance
        menu.start();
    }
}
