package at.ac.fhcampuswien;

import at.ac.fhcampuswien.api.NewsApi;
import at.ac.fhcampuswien.ui.Menu;

public class App {
    private static Menu menu;
    public static void main(String[] args) {
        menu= Menu.getInstance();
        menu.start();



    }
}
