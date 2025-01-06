package com.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchPlayerController {
    @FXML
    private Label message;

    @FXML
    private Button button;


    @FXML
    void buttonback(ActionEvent event){
        try {
            App.setRoot("searchplayer");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void backAction(ActionEvent event) {
        try {
            App.setRoot("home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void searchplayerbynameaction(ActionEvent event){
        try {
            App.setRoot("searchplayerbyname");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML 
    void searchplayerbyclubcountryaction(ActionEvent event){
        try {
            App.setRoot("searchplayerbyclub&country");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML 
    void searchplayerbypositionaction(ActionEvent event){
        try {
            App.setRoot("searchplayerbyposition");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML 
    void searchplayerbysalaryrange(ActionEvent event){
        try {
            App.setRoot("searchplayerbysalaryrange");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML 
    void searchplayerbycountrywisecount(ActionEvent event){
        try {
            App.setRoot("searchplayerbycountrywiseplayer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    @FXML
    ObservableList<Player> tabledata = FXCollections.observableArrayList(App.playerList.players);


    void showplayertable(TableView<Player> t, ArrayList<Player> P){
        TableColumn<Player, String> column1 =  new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> column2 = new TableColumn<>("Country");
        column2.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        TableColumn<Player, String> column3 = new TableColumn<>("Club");
        column3.setCellValueFactory(new PropertyValueFactory<>("club"));
        
        TableColumn<Player, String> column4 = new TableColumn<>("Position");
        column4.setCellValueFactory(new PropertyValueFactory<>("position"));
        
        TableColumn<Player, Integer> column5 = new TableColumn<>("Age");
        column5.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        TableColumn<Player, Integer> column6 = new TableColumn<>("Number");
        column6.setCellValueFactory(new PropertyValueFactory<>("number"));
        
        TableColumn<Player, Integer> column7 = new TableColumn<>("Salary");
        column7.setCellValueFactory(new PropertyValueFactory<>("salary"));

        
        
        TableColumn<Player, Double> column8 = new TableColumn<>("Height");
        column8.setCellValueFactory(new PropertyValueFactory<>("height"));
        
        tableViewbyname.getColumns().clear();
        tableViewbyname.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8);
        
        // tableViewbyname.setItems(tabledata);
        tableViewbyname.getItems().clear();
        
        if(P != null) for(Player p: P) tableViewbyname.getItems().add(p);
    }

    @FXML
    private TextField playernamefield;
    @FXML
    private TableView<Player> tableViewbyname;
    @FXML
    void playernamesearchaction(ActionEvent event){
        String inputname = playernamefield.getText();
        showplayertable(tableViewbyname, App.playerList.search_by_name(inputname));
    }

    @FXML
    private TextField country, club;
    @FXML
    void playerclubcountrysearchaction(ActionEvent event){
        String inputcountry = country.getText();
        String inputclub = club.getText();  
        showplayertable(tableViewbyname, App.playerList.search_by_club_and_country(inputcountry, inputclub));
    }

    @FXML
    private TextField salarymin, salarymax;
    @FXML
    void playersalarysearchaction(ActionEvent event){
        String inputsalarymin = salarymin.getText();
        String inputsalarymax = salarymax.getText();
        System.out.println(inputsalarymin + " " + inputsalarymax);  
        try{
            showplayertable(tableViewbyname, App.playerList.search_by_salary(Integer.parseInt(inputsalarymin), Integer.parseInt(inputsalarymax)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private TextField position;
    @FXML
    void playerpositionsearch(ActionEvent event){
        String inputposition = position.getText();
        showplayertable(tableViewbyname, App.playerList.search_by_position(inputposition));   
    }

    @FXML
    void LoadAction(ActionEvent event){
        HashMap<String, Integer> res = App.playerList.country_wise_player_count();
        ArrayList<Player> P = new ArrayList<>();
        for(var x: res.entrySet()) P.add(new Player(x.getKey(), x.getValue()));

        TableColumn<Player, String> column1 =  new TableColumn<>("Country");
        column1.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        TableColumn<Player, String> column2 = new TableColumn<>("Count");
        column2.setCellValueFactory(new PropertyValueFactory<>("number"));
        
        tableViewbyname.getColumns().clear();
        tableViewbyname.getColumns().addAll(column1, column2);
        
        tableViewbyname.getItems().clear();
        for(Player p: P) tableViewbyname.getItems().add(p);
    }

    @FXML
    private TextField clubname;
    @FXML
    private Label yearlysalary;
    @FXML
    void maxsalary(ActionEvent event){
        String club = clubname.getText();
        showplayertable(tableViewbyname, App.playerList.max_salary_of_club(club));
        yearlysalary.setText("Total Yearly Salary : " + App.playerList.total_club_salary(club));
    }
    @FXML
    void maxage(ActionEvent event){
        String club = clubname.getText();
        showplayertable(tableViewbyname, App.playerList.max_age_of_club(club));
        yearlysalary.setText("Total Yearly Salary : " + App.playerList.total_club_salary(club));
    }
    @FXML
    void maxheight(ActionEvent event){
        String club = clubname.getText();
        showplayertable(tableViewbyname, App.playerList.max_height_of_club(club));
        yearlysalary.setText("Total Yearly Salary : " + App.playerList.total_club_salary(club));
    }

    @FXML 
    private TextField userText, userText1, userText2, userText3, userText4, userText5, userText6, userText7;

    @FXML
    public void initialize() {
        if(userText4 != null) userText4.setText(App.CLUBID);
        if(tableViewbyname != null) showplayertable(tableViewbyname, null);
    }

    @FXML
    void addplayeraction(ActionEvent event){
        String name = userText.getText();
        String country = userText1.getText();
        String age = userText2.getText();
        String height = userText3.getText();
        String club = App.CLUBID;
        userText4.setText(App.CLUBID);
        String position = userText5.getText();
        String number = userText6.getText();
        String salary = userText7.getText();
        String[] construct = {name, country, age, height, club, position, number, salary};
        Player newPlayer = new Player(construct);
        try{
            App.socket.write(new Pair<String, Player>("add", newPlayer));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void sellplayeraction(ActionEvent event){
        TableColumn<Player, String> column1 =  new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> column2 = new TableColumn<>("Country");
        column2.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        TableColumn<Player, String> column3 = new TableColumn<>("Club");
        column3.setCellValueFactory(new PropertyValueFactory<>("club"));
        
        TableColumn<Player, String> column4 = new TableColumn<>("Position");
        column4.setCellValueFactory(new PropertyValueFactory<>("position"));
        
        TableColumn<Player, Integer> column5 = new TableColumn<>("Age");
        column5.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        TableColumn<Player, Integer> column6 = new TableColumn<>("Number");
        column6.setCellValueFactory(new PropertyValueFactory<>("number"));
        
        TableColumn<Player, Integer> column7 = new TableColumn<>("Salary");
        column7.setCellValueFactory(new PropertyValueFactory<>("salary"));
       
        TableColumn<Player, Double> column8 = new TableColumn<>("Height");
        column8.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Player, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button sellButton = new Button("Sell");
            {
                sellButton.setOnAction(e -> {
                    Player person = getTableView().getItems().get(getIndex());
                    try {
                        App.socket.write(new Pair<String, Player>("sell", person));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    getTableView().getItems().remove(person);
                    System.out.println("You Sold: " + person.getName());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(sellButton);
                }
            }
        });
        
        tableViewbyname.getColumns().clear();
        tableViewbyname.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, actionColumn);

        ObservableList<Player> clubplayers = FXCollections.observableArrayList();
        for(Player p: App.playerList.players) if(p.club.equalsIgnoreCase(App.CLUBID)){
            boolean found = false;
            for(Player q: App.buyablelist) if(p.equals(q)){
                found = true; break;
            } 
            if(!found) clubplayers.add(p);
        }
        tableViewbyname.setItems(clubplayers);  
    }

    @FXML
    void buyplayeraction(ActionEvent event){
        TableColumn<Player, String> column1 =  new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Player, String> column2 = new TableColumn<>("Country");
        column2.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        TableColumn<Player, String> column3 = new TableColumn<>("Club");
        column3.setCellValueFactory(new PropertyValueFactory<>("club"));
        
        TableColumn<Player, String> column4 = new TableColumn<>("Position");
        column4.setCellValueFactory(new PropertyValueFactory<>("position"));
        
        TableColumn<Player, Integer> column5 = new TableColumn<>("Age");
        column5.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        TableColumn<Player, Integer> column6 = new TableColumn<>("Number");
        column6.setCellValueFactory(new PropertyValueFactory<>("number"));
        
        TableColumn<Player, Integer> column7 = new TableColumn<>("Salary");
        column7.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        TableColumn<Player, Double> column8 = new TableColumn<>("Height");
        column8.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Player, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button sellButton = new Button("Buy");
            {
                sellButton.setOnAction(e -> {
                    Player person = getTableView().getItems().get(getIndex());
                    person.club = App.CLUBID; // change club to my club

                    getTableView().getItems().remove(person);
                    try {
                        App.socket.write(new Pair<String, Player>("buy", person));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("You Bought: " + person.getName());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(sellButton);
                }
            }
        });
        
        tableViewbyname.getColumns().clear();
        tableViewbyname.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, actionColumn);

        tableViewbyname.setItems(App.buyablelist);
    }
}

