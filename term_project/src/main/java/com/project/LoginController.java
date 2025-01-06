package com.project;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Pair;


public class LoginController {
    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        String password = passwordText.getText();
        try {
            App.socket.write(new Pair<String, String>(userName, password));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object status = false;
        try {
            status = App.socket.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(status instanceof Boolean){
            if((Boolean) status){
                try {
                    App.CLUBID = userName;
                    App.setRoot("home");
                    App.playerList = (PlayerList) App.socket.read();
                    App.buyablelist = FXCollections.observableArrayList((ArrayList<Player>) App.socket.read());            
                    new AppReaderThread();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Credentials");
                alert.setHeaderText("Incorrect Credentials");
                alert.setContentText("The username and password you provided is not correct.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }
}
