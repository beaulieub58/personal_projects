//local directory name
package Java.test_files;
//import scanner object library
import java.util.Scanner;

public class WordGame {

    public static void main(String[] args) {

        //define scanner object for user input setup
        Scanner scnr = new Scanner(System.in);

        //scan in a name`
        System.out.println("Enter a name: ");
        String name = scnr.nextLine();
        //scan in an age int
        System.out.println("Enter an age: ");
        int age = scnr.nextInt();
        //consume extra newline chartacter leftover from nextInt()
        scnr.nextLine();
        //scan in a city name
        System.out.println("Enter a city name: ");
        String city = scnr.nextLine();
        //scan in the name of a college
        System.out.println("Enter a college name: ");
        String college = scnr.nextLine();
        //scan in a profession
        System.out.println("Enter a profession: ");
        String profession = scnr.nextLine();
        //scan in a type of aninmal
        System.out.println("Enter an animal name: ");
        String animal = scnr.nextLine();
        //scan in a pet's name
        System.out.println("Enter a pet name: ");
        String pet_name = scnr.nextLine();

        //print story number 1
        System.out.println("There once was a person named " + name + " who lived in " + city + ". At the age of " + age + ", " + name + " went to college at " + college + ". "  + name + " graduated and went to work as a " + profession + ". Then, " + name + " adopted a(n) " + animal + " named " + pet_name + ". They both lived happily ever after!");

        //break up the stories
        System.out.println();
        //print story number 2
        System.out.println(name + " lived in " + city + " and, at " + age + ", studied at " + college + ". Now a " + profession + ", " + name + " adopted a " + animal + " named " + pet_name + ". They were inseparable ever after.");
        //close the scanner connection
        scnr.close();





    }
    
}
