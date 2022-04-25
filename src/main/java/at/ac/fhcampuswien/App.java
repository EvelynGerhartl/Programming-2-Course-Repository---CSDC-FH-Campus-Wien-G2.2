package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;

public class App extends Application {
@FXML
    public static void main(String[] args) {
       launch(args);


    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/resources/graphicalInterface.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("_the news app_");
        stage.getIcons().add(new Image("file:src/main/resources/icon.png"));
        Scene scene = new Scene(root, 1000, 625);
        stage.setScene(scene);
        scene.setFill(Color.web("#db4840"));
        stage.setResizable(false);




        NewsApi newsapi = new NewsApi();
        System.out.println("news: ");
        System.out.println();
        newsapi.topHeadlinesOnly();
        System.out.println();
        System.out.println("Number of articles: " + newsapi.totalResults());

        NewsApi newsapi2 = new NewsApi();
        System.out.println("bitcoin: ");
        System.out.println(newsapi2.bitcoinHeadlines());
        System.out.println("");
        System.out.println("Number of articles: " + newsapi2.totalResults2());



        stage.show();

    }

}
