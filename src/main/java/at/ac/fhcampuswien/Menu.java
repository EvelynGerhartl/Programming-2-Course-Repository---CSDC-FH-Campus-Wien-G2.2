package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.net.URLConnection;


public class Menu {

    public ImageView newsImage;
    public Text noInternet;
    private AppController ctrl = new AppController();
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
    public Text bigText;

    private void getArticleCount(AppController ctrl) {
        textArea.setText(" Number of articles: " + ctrl.getArticleCount());
    }

    private void getTopHeadlinesAustria(AppController ctrl) throws NewsApiException {
        textArea.setText(ctrl.getTopHeadlinesAustria());

    }

    private void getAllNewsBitcoin(AppController ctrl) throws NewsApiException {
        textArea.setText(ctrl.getAllNewsBitcoin());
    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);                   //exit message is shown in console after closing application
        Platform.exit();

    }


    public void clickedA() throws NewsApiException {
        try {
            checkInternetConnection();
            internet(); //set visibility to false on "no internet" warning
        } catch (NewsApiException nae) {
            textArea.setText(nae.getMessage());
        }
        getTopHeadlinesAustria(ctrl);
    }

    public void clickedB() throws NewsApiException {
        try {
            checkInternetConnection();
            internet(); //set visibility to false on "no internet" warning
        } catch (NewsApiException nae) {
            textArea.setText(nae.getMessage());
        }
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
        text1.setFill(Color.BLACK);
        opt1.setOpacity(0.5);
    }

    public void onHover2() {
        text2.setFill(Color.BLACK);
        opt2.setOpacity(0.5);
    }

    public void onHover4() {
        text4.setFill(Color.BLACK);
        opt4.setOpacity(0.5);
    }


    public void onExited() {
        opt1.setOpacity(1);
        opt2.setOpacity(1);
        opt4.setOpacity(1);
        text1.setFill(Color.WHITE);
        text2.setFill(Color.WHITE);
        text4.setFill(Color.WHITE);
    }


    // ex3: Analysis with streams

    // 3) How many articles come from the source "New York Times"?
    public void countFromSource() {
        String source = "New York Times";
        try {
            textArea.setText("There's " + ctrl.getCountFromSource(source) + " articles from '" + source + "' in the previously loaded articles.");

        } catch (NewsApiException nae) {
            textArea.setText(nae.getMessage());
        }

    }

    // 4) Which articles have a title that consists of less than 15 characters?
    public void titleLessThan15() {
        try {
            textArea.setText(ctrl.getTitleLessThan15());
        } catch (NewsApiException nae) {
            textArea.setText(nae.getMessage());
        }
    }


    public void biggestSource() {
        //Which provider (= source) delivers the most articles?
        try {
            textArea.setText(ctrl.biggestSource());
        } catch (NewsApiException nae) {
            textArea.setText(nae.getMessage());
        }
    }

    public void longestAuthorName() { // check exceptions
        //Which author has the longest name?
        try {
            textArea.setText(ctrl.getLongestAuthorName());
        } catch (NewsApiException | NullPointerException | ArrayIndexOutOfBoundsException nae) {
            textArea.setText(nae.getMessage());
        }
    }

    public void sortByDescription() {

        try {
            textArea.setText(ctrl.sortByDescription().toString());

        } catch (NewsApiException | NullPointerException nae) {
            textArea.setText(nae.getMessage());
        }
    }


    void checkInternetConnection() throws NewsApiException {

        try {
            URL urlToCheck = new URL("http://www.google.com");
            URLConnection connection = urlToCheck.openConnection();
            connection.connect();
            textArea.setText("Internet is connected");

        } catch (Exception e) { //first
            noInternet();
            throw new NewsApiException(""); // // giuli's comment: first
        }

    }

    void noInternet() {
        noInternet.setVisible(true);
    }

    void internet() {
        noInternet.setVisible(false);
    }

}
