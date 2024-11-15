//local directory
package Java.test_files;
//import scanner library
import java.util.Scanner;

public class HotDogCookoutCalculator {

    public static void main(String[] args) {
        //set constants to doubles to be able to do math appropriately
        final double HOTDOG_IN_PACKAGE = 10.0;
        final double BUNS_IN_PACKAGE = 8.0;
        //create scanner object to ask for cookout participants;
        Scanner scnr = new Scanner(System.in);

        //scan in the number of participants and hot dogs allocated to each person
        System.out.println("Please enter the number of cookout attendees:");
        int participants = scnr.nextInt();
        System.out.println("Please enter the number of hot dogs per person:");
        int hotdogsPerPerson = scnr.nextInt();


        //find the mininum number of hot dogs packages and bun packages needed
        double minDogsNeeded = (participants * hotdogsPerPerson) / HOTDOG_IN_PACKAGE;
        double minBunsNeeded = (participants * hotdogsPerPerson) / BUNS_IN_PACKAGE;

        //round up in case of remainders
        minDogsNeeded = Math.ceil(minDogsNeeded);
        minBunsNeeded = Math.ceil(minBunsNeeded);

        //print out the minimum packages of hot dogs and buns needed
        System.out.println("The minimum number packages of hotdogs is: " + (int) minDogsNeeded);
        System.out.println("The minimum number packages of buns is: " + (int) minBunsNeeded);

        //convert the doubles to int and calculate leftovers
        int dogsLeftover = (int) ( (minDogsNeeded * HOTDOG_IN_PACKAGE) - (participants * hotdogsPerPerson));
        int bunsLeftover = (int) ((minBunsNeeded * BUNS_IN_PACKAGE) - (participants * hotdogsPerPerson));

        //create space in output
        System.out.println();

        //print out left over number of hot dogs and buns
        System.out.println("The leftover number of hotdogs is: " + dogsLeftover);
        System.out.println("The left over number of buns is: " + bunsLeftover);

        //close scanner instance
        scnr.close();



    }
    
}
