package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.File;
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


        System.out.println("hi");

        NewsApi newsapi = new NewsApi();
        System.out.println(newsapi.urlMaker("queryyyyy","at", true));
        System.out.println(newsapi.urlMaker("query2", "de", false));
/*
        System.out.println("news: ");
        System.out.println();
        newsapi.topHeadlinesOnly("a","at", true);
        System.out.println("Number of articles corona: " + newsapi.totalResults());
       //System.out.println("Number of articles co: " + newsapi.totalResults());
       //System.out.println("Number of articles none: " + newsapi.totalResults());
        AppController ctrl = new AppController();

        System.out.println("from control: " + ctrl.getArticleCount());
        System.out.println(newsapi.topHeadlinesOnly("corona", "at", true));



        //NewsApi newsapi2 = new NewsApi();
       // System.out.println("bitcoin: ");
        //System.out.println(newsapi2.completeNews());
       // System.out.println("Nr of articles bitcoin: ");
       // System.out.println(newsapi.urlMaker("corona",""));
        //System.out.println(newsapi.urlMaker2("corona"));

*/

        stage.show();

    }

}
