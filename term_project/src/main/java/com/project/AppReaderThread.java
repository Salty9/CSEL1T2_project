package com.project;

import java.io.IOException;

import javafx.util.Pair;

public class AppReaderThread implements Runnable {
    private Thread thr;

    AppReaderThread(){
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        while(true){
            try {
                Pair<String, Player> msg = (Pair<String, Player>) App.socket.read();
                if(msg.getKey().equalsIgnoreCase("add")){
                    App.playerList.add_player(msg.getValue());
                }else if(msg.getKey().equalsIgnoreCase("sell")){
                    App.buyablelist.add(msg.getValue());
                }else if(msg.getKey().equalsIgnoreCase("buy")){
                    Player p = msg.getValue();

                    for(Player x: App.playerList.players){
                        if(x.equals(p)){
                            x.club = p.club;
                            break;
                        }
                    }
                    App.buyablelist.remove(msg.getValue());
                }else{
                    System.out.println("WHAT SIR ??");
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

        }

    }
        
}
