package com.project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    private ServerSocket serverSocket;
    public static ArrayList<SocketWrapper> clientlist = new ArrayList<>(); // HashMap of client's name and socket information
    public static PlayerList playerlist = new PlayerList();
    public static ArrayList<Player> buyablelist = new ArrayList<>();
    public static HashMap<String, String> logininfo = new HashMap<>();
    
    Server(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\com\\project\\players.txt"));
            while(true) {
                String line = br.readLine();
                if (line == null) break;
                playerlist.add_player(line);
            }
            System.out.println("Loaded " + playerlist.players.size() + " players from players list");
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\com\\project\\buyable.txt"));
            while(true) {
                String line = br.readLine();
                if (line == null) break;
                buyablelist.add(new Player(line.split(",")));
            }
            System.out.println("Loaded " + buyablelist.size() + " players from buyable list");
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }


        // NOTE: PRESERVE BUYABLE LIST ON A FILE OR SMTH !!!!!!!!!

        logininfo.put("Kolkata Knight Riders", "123");
        logininfo.put("Mumbai Indians", "123");
        logininfo.put("Chennai Super Kings", "123");
        logininfo.put("Sunrisers Hyderabad", "123");
        logininfo.put("Royal Challengers Bangalore", "123");
        logininfo.put("Delhi Capitals", "123");
        logininfo.put("Rajasthan Royals", "123");
        logininfo.put("a", "a");

        try {
            serverSocket = new ServerSocket(44444);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        new ServerReadThread(socketWrapper);
    }

    public static void main(String args[]) {

        System.out.println("Server is running. Press Ctrl+C to stop...");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server is shutting down. Saving player data...");

            try (FileWriter writer = new FileWriter("src\\main\\java\\com\\project\\players.txt")) {
                for(int i = 0; i < playerlist.players.size(); i++){
                    writer.write(playerlist.players.get(i).name + "," + playerlist.players.get(i).country + "," + playerlist.players.get(i).age + "," + playerlist.players.get(i).height + "," + playerlist.players.get(i).club + "," + playerlist.players.get(i).position + "," + playerlist.players.get(i).number + "," + playerlist.players.get(i).salary + (i == playerlist.players.size()-1 ? "" : "\n"));
                }
            } catch (IOException e) {
                System.err.println("Error writing log: " + e.getMessage());
            }

            try (FileWriter writer = new FileWriter("src\\main\\java\\com\\project\\buyable.txt")) {
                for(int i = 0; i < buyablelist.size(); i++){
                    writer.write(buyablelist.get(i).name + "," + buyablelist.get(i).country + "," + buyablelist.get(i).age + "," + buyablelist.get(i).height + "," + buyablelist.get(i).club + "," + buyablelist.get(i).position + "," + buyablelist.get(i).number + "," + buyablelist.get(i).salary + (i == buyablelist.size()-1 ? "" : "\n"));
                }
            } catch (IOException e) {
                System.err.println("Error writing log: " + e.getMessage());
            }
        }));
        
        new Server();
    }
}