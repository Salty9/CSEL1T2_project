package com.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeController {

    private App main;

    @FXML
    private Label message;

    @FXML
    private ImageView image;

    @FXML
    private Button button;

    public void init(String msg) {
        message.setText(msg);
        // Image img = new Image(App.class.getResourceAsStream("1.png"));
        // image.setImage(img);
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            // main.showLoginPage();
            App.setRoot("login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void searchplayeraction(ActionEvent event){
        try {
            App.setRoot("searchplayer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchclubaction(ActionEvent event){
        try {
            App.setRoot("searchclub");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void addplayeraction(ActionEvent event){
        try{
            App.setRoot("addplayer");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void sellplayeraction(ActionEvent event){
        try{
            App.setRoot("sellplayer");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void buyplayeraction(ActionEvent event){
        try{
            App.setRoot("buyplayer");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void setMain(App main) {
        this.main = main;
    }

}
