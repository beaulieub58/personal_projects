package Java.DSA.Assignments;
import java.util.Scanner;
//Question 2.7, Assignment 1
public class assignment_1 {

    public static int twoSevenBTwo(int n) {

        int sum = 0;

        for(int i = 0; i < n; ++i) {

            for(int j = 0; j < n; ++j){
                ++sum;
            } 

        }

        return sum;

    }

    public static int twoSevenBFour(int n) {

        int sum = 0;

        for(int i = 0; i < n; ++i) {

            for(int j = 0; j < i; ++j){
                ++sum;
            } 

        }

        return sum;

    }

    public static int twoSevenBSix(int n) {

        int sum = 0;

        for(int i = 0; i < n; ++i) {

            for(int j = 1; j < i * i; ++j){

                if(j % i ==0){

                    for(int k = 0; k < j; ++k) {

                        ++sum;

                    }
                }
            } 

        }

        return sum;

    }

    public static void main(String [] args){

        Scanner scnr = new Scanner(System.in);

        int number = scnr.nextInt();

        long startTime = System.currentTimeMillis();

        twoSevenBTwo(number);

        long endTime = System.currentTimeMillis();

        long timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b2 is: ");
        System.out.println(timeDifference);

        startTime = System.currentTimeMillis();

        twoSevenBFour(number);

        endTime = System.currentTimeMillis();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b4 is: ");
        System.out.println(timeDifference);

        startTime = System.currentTimeMillis();

        twoSevenBSix(number);

        endTime = System.currentTimeMillis();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b6 is: ");
        System.out.println(timeDifference);


        scnr.close();

    }


}
