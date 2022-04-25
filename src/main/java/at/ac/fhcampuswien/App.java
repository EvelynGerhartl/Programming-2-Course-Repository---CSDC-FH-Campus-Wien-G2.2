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

        Menu menu = new Menu();

        TryingOutGson newsapi = new TryingOutGson();
        System.out.println("__________ tryingOutOkHttp:");
        newsapi.tryingOutOkHttp();
        System.out.println("__________");
        System.out.println("try nr 300:");
        System.out.println();
        newsapi.tryNr300();





        stage.show();

    }
}
