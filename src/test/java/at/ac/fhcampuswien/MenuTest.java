package at.ac.fhcampuswien;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MenuTest {
    @Test
    public void menu() {
        Menu menuTest = new Menu();
        String actualText = menuTest.printedMenu();
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
    }}
