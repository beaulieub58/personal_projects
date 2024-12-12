//local directory
package Java.Homework.assignment_2;

public class Player {

    //declare private variables for the player's attributes
    private String name;
    private String position;
    private double passingReceivingRushingYards;
    private boolean isDefensive;
    //I included this so I wouldn't see a squigly line under the variable. I looked up how to do this, if that is alright.
    @SuppressWarnings("unused")
    //empty constructor class
    private Player() {

    }
    //constructor class to define Player attributes and parameters
    public Player(String name, String position, double yards, boolean defensive) {

        this.name = name;
        this.position = position;
        this.passingReceivingRushingYards = yards;
        this.isDefensive = defensive;
    }
    //simple get method to return player name
    public String getName() {
        return this.name;
    }
    //simple get method to return the player's position
    public String getPosition() {
        return this.position;
    }
    //simple get method to return the passing, receiving, and passing yards
    public double getPassingReceivingRushingYards() {
        return this.passingReceivingRushingYards;
    }
    //returns true if the player's position is defensive, and false otherwise
    public boolean hasDefensivePosition() {

        return this.isDefensive;

    }
    //returns true if the player's position is offensive, and false otherwise
    public boolean hasOffensivePosition() {

        return !this.isDefensive;

    }
    //I needed to ovveride the toString method so I could print the player attributes to the file correctly. Is there a better way to do this?
    @Override
    public String toString() {
        return name + " " + position + " " + passingReceivingRushingYards + " " + isDefensive + "\n";

    }


}
