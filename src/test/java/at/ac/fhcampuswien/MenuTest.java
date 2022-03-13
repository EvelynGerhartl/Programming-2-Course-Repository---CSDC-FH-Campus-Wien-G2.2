package at.ac.fhcampuswien;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class MenuTest {
    private PrintStream ps;

    @Test
    public void menu() {
        Menu menuTest = new Menu();
        String actualText = menuTest.printMenu(); //printMenu is package-private
        //menuTest.menu();

        String expectedText = "************************" + System.lineSeparator() +
                "*Welcome to NewsApp*" + System.lineSeparator() +
                "************************" + System.lineSeparator() +
                "Enter what you want to do: " + System.lineSeparator() +
                "a: Get the top headlines for Austria" + System.lineSeparator() +
                "b: Get all the news about Bitcoin" + System.lineSeparator() +
                "y: Count articles" + System.lineSeparator() +
                "q: Quit program" + System.lineSeparator();

        assertEquals(expectedText, actualText);
    }

    @Test
public void controller() {

        //testen, ob user input erfolgt
        ps.println("a");

        //testen, ob Methode a, b, y aufgerufen wird

        //testen, ob q funktioniert (exit)

        String output = "Bye Bye!";
        //assertEquals();


    }
}
