package com.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerList implements Serializable{
    ArrayList<Player> players =  new ArrayList<Player>();

    ArrayList<Player> search_by_name(String name){
        ArrayList<Player> found = new ArrayList<Player>();
        for(Player x: players) if(x.name.equalsIgnoreCase(name)) found.add(x);
        return found;
    }

    // PlayerList(){};
    // PlayerList(PlayerList other) {
    //     this.players = new ArrayList<>();
    //     for (Player p : other.players) {
    //         this.players.add(new Player(p.name, p.country, p.age, p.height, p.club, p.position, p.number, p.salary));
    //     }
    // }
    // PlayerList(ArrayList<Player> other){
    //     this.players = other;
    // }

    
    public void change_club(Player p, String c){
        for(Player player : players){
            if(player.equals(p)){
                player.club = c;
                break;
            }
        }
    }
    


    ArrayList<Player> search_by_club_and_country(String country, String club){
        ArrayList<Player> found = new ArrayList<Player>();

        for(Player x: players) if(x.country.equalsIgnoreCase(country)){
            if(x.club.equalsIgnoreCase(club) || club.equalsIgnoreCase("ANY")) found.add(x);
        }
        return found;
    }

    ArrayList<Player> search_by_position(String position){
        ArrayList<Player> found = new ArrayList<Player>();
        for(Player x: players) if(x.position.equalsIgnoreCase(position)) found.add(x);
        return found;
    }

    ArrayList<Player> search_by_salary(int min, int max){
        ArrayList<Player> found = new ArrayList<Player>();
        for(Player x: players) if(min <= x.salary && x.salary <= max) found.add(x);
        return found;
    }
    ArrayList<Player> search_by_club(String clubname){
        ArrayList<Player> found = new ArrayList<Player>();
        for(Player x: players) if(x.club.equalsIgnoreCase(clubname)) found.add(x);
        return found;
    }

    HashMap<String, Integer> country_wise_player_count(){
        HashMap<String, Integer> count = new HashMap<String, Integer>();

        for(Player x: players) count.put(x.country.toLowerCase(), count.getOrDefault(x.country.toLowerCase(), 0)+1); // worst case all unique coutnry anyway
        return count;
    }

    ArrayList<Player> max_salary_of_club(String club){
        ArrayList<Player> found = new ArrayList<Player>();

        int maxsalary = -1;
        for(Player x: players) if(x.club.equalsIgnoreCase(club)) maxsalary = Math.max(maxsalary, x.salary);
        for(Player x: players) if(x.club.equalsIgnoreCase(club) && x.salary == maxsalary) found.add(x);
        return found;
    }

    ArrayList<Player> max_height_of_club(String club){
        ArrayList<Player> found = new ArrayList<Player>();

        double maxheight = -1;
        for(Player x: players) if(x.club.equalsIgnoreCase(club)) maxheight = Math.max(maxheight, x.height);
        for(Player x: players) if(x.club.equalsIgnoreCase(club) && x.height == maxheight) found.add(x);
        return found;
    }
    ArrayList<Player> max_age_of_club(String club){
        ArrayList<Player> found = new ArrayList<Player>();

        int maxage = -1;
        for(Player x: players) if(x.club.equalsIgnoreCase(club)) maxage = Math.max(maxage, x.age);
        for(Player x: players) if(x.club.equalsIgnoreCase(club) && x.age == maxage) found.add(x);
        return found;
    }

    int total_club_salary(String club){
        int total = 0;
        for(Player x: players) if(x.club.equalsIgnoreCase(club)) total += x.salary;
        return total*52; // this asks for yearly salary
    }

    boolean add_player(String input){ // true/false = success/nah of adding player
        String[] args = input.split(",");
        assert args.length == 8 : "Input wasnt given properly";

        for(Player x: players) if(x.name.equalsIgnoreCase(args[0])) return false;

        players.add(new Player(args));
        return true;
    }

    public void add_player(Player p){
        players.add(p);
    }
    public void remove_player(Player x){
        players.remove(x);
    }
}
