package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Menu {

    public ImageView newsImage;
    private AppController ctrl = new AppController();
    private static final String EXIT_MESSAGE = "Bye Bye!";
    //private static final String INVALID_INPUT_MESSAGE = "Invalid input message. Please, try again!";
    //there´s no input in ex2 (javafx)

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

    private void getTopHeadlinesAustria(AppController ctrl) {
        textArea.setText(ctrl.getTopHeadlinesAustria());

    }

    private void getAllNewsBitcoin(AppController ctrl) {
        textArea.setText(ctrl.getAllNewsBitcoin());
    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);                   //exit message is shown in console after closing application
        Platform.exit();

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





    // ex3: Analysis with streams

    /** START finished methods */

    // 3) How many articles come from the source "New York Times"?
    public void countFromSource() {
        String source = "New York Times";
        try {
            textArea.setText("There's " + ctrl.getCountFromSource(source) + " articles from '" + source + "' in the previously loaded articles.");

        } catch(NewsApiException nae) {
            System.out.println("Exception caught from countFromSource!");
            textArea.setText(nae.getMessage());
        }

    }

    // 4) Which articles have a title that consists of less than 15 characters?
    public void titleLessThan15() {
        try {
            textArea.setText(ctrl.getTitleLessThan15());
        } catch (NewsApiException nae) {
            textArea.setText(nae.getMessage());
            System.out.println("Exception caught from getTitleLessthan15! "); //delete before Abgabe
        }
    }

    /** END finished methods */


    public void streamsAnalysis1() {
        //Which provider (= source) delivers the most articles?
        try {
            textArea.setText(ctrl.biggestSource());
        } catch (NewsApiException nae) {
            textArea.setText(nae.getMessage());
            System.out.println(nae.getMessage());
        }
    }
    public void streamsAnalysis2() throws NewsApiException {
        //Which author has the longest name?
        try {
            ctrl.longestAuthorName();
            textArea.setText(ctrl.longestAuthorName().toString());
        } catch (NewsApiException nae) {
            textArea.setText(nae.getMessage());
        //} catch (NullPointerException npe) {
         //   throw new NewsApiException();
        }

    }

    public void streamsAnalysis5() {

        try {
            textArea.setText(ctrl.streamAnalysis5().toString());

        } catch (NewsApiException nae) {
            textArea.setText(nae.getMessage());
            System.out.println(nae.getMessage()); // comment out before deadline
        }

    }



}

