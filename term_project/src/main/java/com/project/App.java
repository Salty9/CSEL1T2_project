package com.project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends Application {

    private static Scene scene;
    public static PlayerList playerList = new PlayerList(); // list of players it will get from server once it connects
    public static SocketWrapper socket;
    public static ObservableList<Player> buyablelist;
    public static String CLUBID;

    public static final double WIDTH = 1300;
    public static final double HEIGHT = 650;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
        stage.setTitle("IPL CLUB MANAGER");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        try {
            System.out.println("Client Started");
            socket = new SocketWrapper("127.0.0.1", 44444);
        } catch (Exception e) {
            System.out.println(e);
        }
        launch();
    }
}