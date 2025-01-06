package com.project;
import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {
    public String name, country, club, position;
    public int age, number, salary;
    public double height;

    public Player(String name, String country, int age, double height, String club, String position, int number, int salary){
        this.name = name;
        this.country = country;
        this.club = club;
        this.height = height;
        this.position = position;
        this.age = age;
        this.number = number;
        this.salary = salary;
    }
    public Player(String country, int count){ // CHEESE playwerwisecountrycount!!!
        this.country = country;
        this.number = count;
    }

    public String getName() {
        return name; 
    }
    
    public String getCountry() {
        return country;
    }
    
    public String getClub() {
        return club;
    }
    
    public String getPosition() {
        return position;
    }
    
    public int getAge() {
        return age;
    }
    
    public int getNumber() {
        return number;
    }
    
    public int getSalary() {
        return salary;
    }
    
    public double getHeight() {
        return height;
    }

    public Player(String[] args){
        this(args[0], args[1], Integer.parseInt(args[2]), Double.parseDouble(args[3]), args[4], args[5], (args[6].isEmpty() ? -1 : Integer.parseInt(args[6])), Integer.parseInt(args[7]));
    }


    void display(){
        System.out.println("Name : " + name);
        System.out.println("Country : " + country);
        System.out.println("Club : " + club);
        System.out.println("Position : " + position);
        System.out.println("Age : " + age);
        System.out.println("Number : " + (number == -1 ? "N/A" : number));
        System.out.println("Salary : " + salary);
        System.out.println("Height : " + height);
        System.out.println();
    }

    // clubs can change, so we don't include it in equals and hashcode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; 
        Player player = (Player) obj;
        return age == player.age &&
            number == player.number &&
            salary == player.salary &&
            Double.compare(player.height, height) == 0 &&
            name.equalsIgnoreCase(player.name) &&
            country.equalsIgnoreCase(player.country) &&
            // club.equalsIgnoreCase(player.club) &&
            position.equalsIgnoreCase(player.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, position, age, number, salary, height);
    }

 

}