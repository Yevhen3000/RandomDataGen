/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package RandomDataGen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.HashSet;

/**
 * @author Yevhen K, 2024
 */

public class RandomDataGen {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+";

    private static BufferedReader inputF;
    private static BufferedWriter outputF;
    
    private static String[] nationalities;
    private static String[] genres;
    private static String[] names;
    private static String[] surnames;
    
    private static HashSet<String> usedSurnames;
    
    public static void main(String[] args) {
        
        load_genres();
        load_nationalities();
        load_names();
        load_surnames();
        
        usedSurnames  = new HashSet<>();
        
        System.out.print("Generating actors... ");
        make_random_people("actors.csv");
        System.out.print("Generating directors... ");
        make_random_people("directors.csv");
        System.out.print("Generating users... ");
        make_random_users("users.csv");        
    }
    
    public static void load_genres(){
        System.out.print("Loading genres... ");
        genres = readStringsFromFile("genres.txt");
        System.out.println( genres.length + " done");
    }

    public static void load_nationalities(){
        System.out.print("Loading nationalities... ");
        nationalities = readStringsFromFile("nationalities.txt");
        System.out.println( nationalities.length + " done");
    }

    public static void load_surnames(){
        System.out.print("Loading surnames... ");
        surnames = readStringsFromFile("surnames.txt");
        System.out.println( surnames.length + " done");
    }

    public static void load_names(){
        System.out.print("Loading names... ");
        names = readStringsFromFile("names.txt");
        System.out.println( names.length + " done");
    }    

    public static void make_random_users(String fileName){
                 
        boolean in_progress = true;
        int how_many_generate = 500;
        int cnt = 0;
        String delimiter = ";";
        boolean first_line = true;
        
        try {
            outputF  = new BufferedWriter(new FileWriter(fileName, false ));
        } catch (Exception e) {
            System.out.println("Open file error: " + e );
        } 
        
        while (in_progress) {
            
            String random_name = (names[randomRangeRandom(0, names.length-1)]);
            String random_surname = (surnames[randomRangeRandom(0, surnames.length-1)]);
            
            int prev_count = usedSurnames.size();
            usedSurnames.add(random_surname);
            if (usedSurnames.size()>prev_count) {

                try {
                    if (first_line){
                        outputF.write("username" + delimiter + "name" + delimiter + "surname" + delimiter + "password\n" );
                        first_line = false;
                    }
                    outputF.write(random_name + delimiter + random_name + delimiter + random_surname + delimiter + generatePassword(8) );
                } catch (Exception e) {
                    System.out.println("Write to file error: " + e );
                }                    
                cnt++;
                if (cnt>how_many_generate-1) {
                    in_progress = false;
                } else {
                    try {
                        outputF.newLine();
                    } catch (Exception e) {
                        System.out.println("Write to file error: " + e );
                    }                    
                }
            } else {
               // System.out.println("The same - skip [" + cnt + "]");
            }

        }
        
        try {
            outputF.close();
        } catch (Exception e) {
              System.out.println("Closing error: " + e );
        }
        System.out.println(cnt + " Done");
        
    }    
    

    public static void make_random_people(String fileName){
                 
        boolean in_progress = true;
        int how_many_generate = 500;
        int cnt = 0;
        String delimiter = ";";
        boolean first_line = true;
        
        try {
            outputF  = new BufferedWriter(new FileWriter(fileName, false ));
        } catch (Exception e) {
            System.out.println("Open file error: " + e );
        } 
        
        while (in_progress) {
            
            String random_name = (names[randomRangeRandom(0, names.length-1)]);
            String random_surname = (surnames[randomRangeRandom(0, surnames.length-1)]);
            
            int prev_count = usedSurnames.size();
            usedSurnames.add(random_surname);
            if (usedSurnames.size()>prev_count) {
                String nationality = nationalities[randomRangeRandom(0, nationalities.length-1)];
                try {
                    if (first_line){
                        outputF.write("name" + delimiter + "surname" + delimiter + "nationality\n" );
                        first_line = false;
                    }                    
                    
                    outputF.write(random_name + delimiter + random_surname + delimiter + nationality );
                } catch (Exception e) {
                    System.out.println("Write to file error: " + e );
                }                    
                cnt++;
                if (cnt>how_many_generate-1) {
                    in_progress = false;
                } else {
                    try {
                        outputF.newLine();
                    } catch (Exception e) {
                        System.out.println("Write to file error: " + e );
                    }                    
                }
            } else {
               // System.out.println("The same - skip [" + cnt + "]");
            }

        }
        
        try {
            outputF.close();
        } catch (Exception e) {
              System.out.println("Closing error: " + e );
        }
        System.out.println(cnt + " Done");
        
    }
    
    public static int randomRangeRandom(int start, int end) {
        Random random = new Random();
        int number = random.nextInt((end - start) + 1) + start;
        return number;
    }
    
    private static String[] readStringsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().toArray(String[]::new);
        } catch (Exception e) {
            return new String[0];
        }
    }
    
   

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }
        return password.toString();
    } 
    
}
