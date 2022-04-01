package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;



public class Menu {

    private AppController ctrl = new AppController();
    private static final String INVALID_INPUT_MESSAGE = "Invalid input message. Please, try again!";
    private static final String EXIT_MESSAGE = "Bye Bye!";

    @FXML
    public Text text1;
    public Text text2;
    public Text text3;
    public Text text4;
    public ImageView opt1;
    public ImageView opt2;
    public ImageView opt3;
    public ImageView opt4;

    public TextArea textArea = new TextArea();
    public TextArea textAreaInput;
    public Text bigText;



    public void start() {
       // printMenu();
       // Scanner scanner = new Scanner(System.in);
        // String input = scanner.next();
       // handleInput(input);

}


    @FXML
   public void onEnter() {
              String input = textAreaInput.getText();
              handleInput(input);
              textAreaInput.clear();

    }


     private void handleInput(String input) {

        if (input.equals("a")) {
            clickedA();

        } else if (input.equals("b")) {
            clickedB();

        } else if (input.equals("y")) {
            clickedY();


        } else if (input.equals("q")) {
            printExitMessage();


        } else {
            printInvalidInputMessage();
        }
    }

    private void getArticleCount(AppController ctrl) {
        textArea.setText("Number of articles: " + ctrl.getArticleCount());
    }

    private void getTopHeadlinesAustria(AppController ctrl) {
        textArea.setText(ctrl.getTopHeadlinesAustria().toString());

    }

    private void getAllNewsBitcoin(AppController ctrl) {
        textArea.setText(ctrl.getAllNewsBitcoin().toString());
    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
        Platform.exit();

    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }


    private static void printMenu() {

        System.out.println("************************");
        System.out.println("*Welcome to NewsApp*");
        System.out.println("Choose what you want to to from the menu or enter: ");
        System.out.println("a: Get the top headlines for Austria");
        System.out.println("b: Get all the news about Bitcoin");
        System.out.println("y: Count articles");
        System.out.println("q: Quit program");

    }



    public void clickedA() {
        getTopHeadlinesAustria(ctrl);
    }

    public void clickedB() {
        getAllNewsBitcoin(ctrl);
    }

    public void clickedY() {
        getArticleCount(ctrl);
    }

    public void clickedQ() {
        printExitMessage();
        bigText.setVisible(true);
        Platform.exit();
    }



    public void onHover1() {
        text1.setFill(Color.web("#007178"));
        opt1.setOpacity(0.5);
    }
    public void onHover2() {
        text2.setFill(Color.web("#007178"));
        opt2.setOpacity(0.5);
    }
    public void onHover3() {
        text3.setFill(Color.web("#007178"));
        opt3.setOpacity(0.5);
    }
    public void onHover4() {
        text4.setFill(Color.web("#007178"));
        opt4.setOpacity(0.5);
    }

    public void onExited() {
        opt1.setOpacity(1);
        opt2.setOpacity(1);
        opt3.setOpacity(1);
        opt4.setOpacity(1);
        text1.setFill(Color.WHITE);
        text2.setFill(Color.WHITE);
        text3.setFill(Color.WHITE);
        text4.setFill(Color.WHITE);


    }


}

