//local directory
package Java.Homework;
//import scanner library
import java.util.Scanner;

public class HotDogCookoutCalculator {
    
    //function to return the minimum number of buns needed
    public static int bunsNeeded(int inputParticipants, int inputHotDogsPerPerson, double bunsInPackage) {

         //find the mininum number of bun packages needed foundation
        double minBunsNeeded = (inputParticipants * inputHotDogsPerPerson) / bunsInPackage;
        
        //round up in case of remainders for minimum number needed
        minBunsNeeded = Math.ceil(minBunsNeeded);

        //return the casted integer
        return (int) minBunsNeeded;
    }
    //function to return the minimum number of dogs needed
    public static int dogsNeeded(int inputParticipants, int inputHotDogsPerPerson, double dogsInPackage) {
        
        //find the mininum number of dog packages needed foundation
        double minDogsNeeded = (inputParticipants * inputHotDogsPerPerson) / dogsInPackage;
        
        //round up in case of remainders for minimum number needed
        minDogsNeeded = Math.ceil(minDogsNeeded);
        
        //return the casted integer
        return (int) minDogsNeeded;
    }

    public static void main(String[] args) {

        //declare a constant buns in package variable
        final double BUNS_IN_PACKAGE = 8.0;
        //declare a constant dogs in package variable
        final double HOTDOG_IN_PACKAGE = 10.0;

        //create scanner object to ask for cookout participants and hot dogs per person;
        Scanner scnr = new Scanner(System.in);

        //scan in the number of participants and hot dogs allocated to each person
        System.out.println("Please enter the number of cookout attendees:");
        int participants = scnr.nextInt();
        System.out.println("Please enter the number of hot dogs per person:");
        int hotdogsPerPerson = scnr.nextInt();


        //print out the minimum packages of hot dogs and buns needed
        System.out.println("The minimum number packages of hotdogs is: " + dogsNeeded(participants, hotdogsPerPerson, HOTDOG_IN_PACKAGE));
        System.out.println("The minimum number packages of buns is: " + bunsNeeded(participants, hotdogsPerPerson, BUNS_IN_PACKAGE));

        //calculate leftovers 
        int dogsLeftover = (int) ((dogsNeeded(participants, hotdogsPerPerson, HOTDOG_IN_PACKAGE) * HOTDOG_IN_PACKAGE) - (participants * hotdogsPerPerson));
        int bunsLeftover = (int) ((bunsNeeded(participants, hotdogsPerPerson, BUNS_IN_PACKAGE) * BUNS_IN_PACKAGE) - (participants * hotdogsPerPerson));

        //create space in output
        System.out.println();

        //print out left over number of hot dogs and buns
        System.out.println("The leftover number of hotdogs is: " + dogsLeftover);
        System.out.println("The left over number of buns is: " + bunsLeftover);

        //close scanner instance
        scnr.close();



    }
    
}
