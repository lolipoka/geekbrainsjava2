package ru.geekbrains.lesson4.networkchat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("networkchat.fxml"));
        primaryStage.setTitle("Simple network chat");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }
}
