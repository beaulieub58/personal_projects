//local directory
package Java.Homework.assignment_2;
import java.util.Scanner;

public class TeamRosterTrackerInterface {

    public static void main(String[] args) {
        //declare an integer for the starting option for our switch statement
        int integerOption = 0;
        

        //Block of code to for testing out all of the milestones:

        /* 
        Player testPlayer = new Player("Ben", "Quarterback", 450, false);
        Player testPlayer2 = new Player("Matt", "Cornerback", 450, true);

        System.out.println(testPlayer.getName());
        System.out.println(testPlayer.getPosition());
        System.out.println(testPlayer.getPassingReceivingRushingYards());
        System.out.println(testPlayer.hasDefensivePosition());
        System.out.println(testPlayer.hasOffensivePosition());

        FootballRoster roster = new FootballRoster();

        roster.addPlayer(testPlayer);
        roster.addPlayer(testPlayer2);

        System.out.println(roster.getNumberOfPlayers());

        roster.saveRosterToFile("football_roster.txt");
        
        
        FootballRoster roster2 = new FootballRoster();
        roster2.loadRosterFromFile("football_roster.txt");
        System.out.println(roster2.getPlayer(0));
        System.out.println(roster2.getPlayer(1));
        */



        //instantiate a scanner object to read in strings, integers, and doubles while building the roster
        Scanner scnr = new Scanner(System.in);

        //instantiate a roster object to add players to and load players to
        FootballRoster newRoster = new FootballRoster();

        //while true will always be true until we force the loop to exit with the selection of the integer 5
        while (true) {

            //print the menu options for the user
            System.out.println("Select an integer value.");
            System.out.println("1. Add a new player to the roster");
            System.out.println("2. Get a players info based on index position in the array");
            System.out.println("3. Save Roster to File");
            System.out.println("4. Load Roster from File");
            System.out.println("5. Exit");

            //scan in the menu selection
            integerOption = scnr.nextInt();

            //switch statement for each menu case
            switch (integerOption) {
                //selection of first option, add a player
                case 1:
                    //instantiate the player object as null
                    Player newPlayer = null;

                    //scan in the name
                    System.out.println("Add a new player to your roster: ");

                    System.out.println("Enter the player's name: ");

                    String name = scnr.next();

                    //scan in the position
                    System.out.println("Enter the player's position: ");

                    String pos = scnr.next();

                    //scan in the passing, rushing, and receiving yards

                    System.out.println("Enter the player's total passing, rushing, and receiving yards (rounded to 1 decimal place): ");

                    double yards = scnr.nextDouble();

                    //scan in true if defensive, false if offensive.

                    System.out.println("Enter true if the player's position is defensive, and false if offensive: ");

                    boolean defensive = scnr.nextBoolean();

                    //add the player attributes that were scanned in

                    newPlayer = new Player(name, pos, yards, defensive);

                    //call the add player method from FootballRoster class to add player to roster.

                    newRoster.addPlayer(newPlayer);

                    break;
                    
                case 2:
                    //initialize the roster index with the position of 0
                    int rosterIndex = 0;

                    //Ask use to enter the index of the roster position of the player they want to identify and scan it in
                    System.out.println("Enter the index of a player's roster position (0-999): ");
                    
                    rosterIndex = scnr.nextInt();
                    
                    //print out the result of the FootballRoster class getPlayer method, with the inserted rosterIndex integer as the position
                    System.out.println(newRoster.getPlayer(rosterIndex));
                    
                    break;
                
                case 3:
                    //save the created roster to a file the user defines in .txt format. Scan that filename in.
                    System.out.println("Enter a text file name to save roster to, including .txt ending: ");
                    String fileNameEnter = scnr.next();

                    //call FootballRoster classes' saveRosterToFile method using the scanned in filename and save to your directory
                    newRoster.saveRosterToFile(fileNameEnter);

                    break;
                
                case 4:
                    //load the created roster file to the roster object array using a scanned in filename in .txt format
                    System.out.println("Enter a text file name where the roster will be loaded from, including .txt ending: ");
                    String fileNameLoad = scnr.next();
                    newRoster.loadRosterFromFile(fileNameLoad);
                    
                    break;
                
                case 5:
                    //close the scanner and exit the switch statement, as well as the while loop.
                    scnr.close();

                    System.out.println("Thank you for using our roster building tool!");

                    return;
                    

                    
                //default cause in the event someone selects an undefined option
                default:
                    System.out.println("Select an accepted integer");

                    break;

            }
            
        }

            
    }

}
