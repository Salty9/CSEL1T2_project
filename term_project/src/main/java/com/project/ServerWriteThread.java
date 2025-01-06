package com.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

public class ServerWriteThread implements Runnable {
    private Thread thr;
    String instruction;
    Player player;

    public ServerWriteThread(String instructoion, Player player){
        this.instruction = instructoion;
        this.player = player;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run(){
        if(this.instruction.equalsIgnoreCase("add")){
            Server.playerlist.add_player(player);
        }else if(this.instruction.equalsIgnoreCase("sell")){
            Server.buyablelist.add(player);
        }else if(this.instruction.equalsIgnoreCase("buy")){
            for(Player x: App.playerList.players){
                if(x.equals(player)){
                    x.club = player.club;
                    break;
                }
            }
            Server.buyablelist.remove(player);
        }else{
            System.out.println("WHAT SIR ??");
        }

        for(SocketWrapper client : Server.clientlist){
            try {
                client.write(new Pair<String, Player>(this.instruction, this.player));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
