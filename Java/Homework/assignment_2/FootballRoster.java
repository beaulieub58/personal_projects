//local directory
package Java.Homework.assignment_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FootballRoster {
    //define an array variable consisting of, up to, 1000 players
    private Player[] rosterArray = new Player[1000];
    
    //I looked up how to suppress untilization warnings.
    @SuppressWarnings("unused")

    //declare a totalPlayers variable set to 0
    private int totalPlayers = 0;

    //get method to return the totalPlayers variable due to it being private.
    public int getNumberOfPlayers() {

        return totalPlayers;

    }

    //get method to return the player defined by the index position in the player roster
    public Player getPlayer(int index) {

        //if the index argument is less than 0, is greater than the total number of players in the roster, or is greater than the roster array length, do nothing
        if (index < 0 || index >= totalPlayers || index > rosterArray.length) {
            return null;
        }
        else {
            //return the player defined by the rosterarray index
            return rosterArray[index];
        }

    }

    //add method to add a player to the roster. The method accepts a player object as an argument
    public void addPlayer(Player newItem) {

        //check to see if the player object is null before proceeding
        if (newItem == null) {

            System.out.println("Player not added.");
        }
        //add the player to the roster and increment totalPlayers to position the next added player correctly.
        else {
            rosterArray[totalPlayers] = newItem;
            totalPlayers += 1;
        }


    }

    //saves the roster of Player objects to a file name the user determines in the main method the roster class is instantiated in
    public void saveRosterToFile(String fileName) {

        //try block ensures the file exists before proceeding.
        try {
            //create a new print writer object to write to your file
            PrintWriter pw = new PrintWriter(fileName);

            //loop through the number of totalPlayers so we know how many times we need to write to the file
            for(int i = 0; i < totalPlayers; ++i) {

                //each iteration prints the Player at position i to the file
                pw.print(rosterArray[i]);

               

            }
            //close print writer instance
            pw.close();
        }
        //exception to throw if file is not found
        catch (IOException e) {
            System.err.println("file not found");
        }
    }
    //class method to load roster from file in to a roster array instance
    public void loadRosterFromFile(String fileName) {
        //try block to read in players from roster
        try {

            //Instantiate an object from the FileInputStream class to read in file contents
            FileInputStream fileByteStream = new FileInputStream(fileName);
            //Instantiate an object from the Scanner class to read file contents
            Scanner scnr = new Scanner(fileByteStream);

            System.out.println("Reading players from roster file.");
            //I know this wasn't part of the suggestions in the assignment, but I wasn't sure how else to read the file contents until it was empty
            while (scnr.hasNextLine()) {
                //read in the name from the roster file
               String name = scnr.next();
               //read in the position from the roster file
               String pos = scnr.next();
               //read in the yards from the roster file
               double yards = scnr.nextDouble();
               //read in the defensive boolean value from the file
               boolean defensive = scnr.nextBoolean();
                //Instantiate the a player object and assign the paramters with the arguments gathered from the file for each roster spot in file
               Player newPlayer = new Player(name, pos, yards, defensive);
               //use the add player method to add a player in to the roster array
               addPlayer(newPlayer);

            }
            //close the scanner instance
            scnr.close();

            
        }
        //file not found exception
        catch (FileNotFoundException e) {

            System.out.println("File not found!");

        }
        //I included this catch because I kept getting exceptions in the output when the file was done being read. I would love to see osme feedback as to how I could do this better
        catch (NoSuchElementException e) {
            
            System.out.println("File read to completion");

        }

        }

    }

    






