package com.project;

import java.io.IOException;

import javafx.util.Pair;

public class ServerReadThread implements Runnable{
    private Thread thr;
    private SocketWrapper clientsocket;
    
    public ServerReadThread(SocketWrapper clientsocket){
        this.clientsocket = clientsocket;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        // check login credentials
        try{
            while (true) {
                Object o = clientsocket.read();
                Boolean status = false;
                if(o instanceof Pair){
                    String username = (String) ((Pair) o).getKey();
                    String password = (String) ((Pair) o).getValue();
                    System.out.println("server received : " + username + " " + password);
                    status = Server.logininfo.containsKey(username) && Server.logininfo.get(username).equals(password);
                    clientsocket.write(status);
                }
                if(status){
                    break;
                }                    
            }
        }catch(Exception e){
            System.out.println(e);
        }

        Server.clientlist.add(clientsocket);
        try {
            clientsocket.write(Server.playerlist);
            clientsocket.write(Server.buyablelist);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // process player updates
        try {
            while (true) {
                Object o = clientsocket.read();
                if(o instanceof Pair){
                    Pair<String, Player> p =  (Pair<String, Player>) o;
                    System.out.println("server received : " + p.getKey());
                    new ServerWriteThread(p.getKey(), p.getValue());
                }else{
                    System.out.println("WE GOT SMTH ELSE SIRS");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                clientsocket.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
