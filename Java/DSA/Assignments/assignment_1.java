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

        int[] numbers = new int[3];

        for (int i = 0; i < numbers.length; ++i) {
            System.out.println("Enter the first runtime N:");
            numbers[i] = scnr.nextInt();
        }

        System.out.println("The run times of the first algorithm are as follows: ");
        long startTime = System.nanoTime();

        twoSevenBTwo(numbers[0]);

        long endTime = System.nanoTime();

        long timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b2 is: ");
        System.out.println(timeDifference);

        startTime = System.nanoTime();

        twoSevenBFour(numbers[0]);

        endTime = System.nanoTime();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b4 is: ");
        System.out.println(timeDifference);

        startTime = System.nanoTime();

        twoSevenBSix(numbers[0]);

        endTime = System.nanoTime();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b6 is: ");
        System.out.println(timeDifference);

        System.out.println("The run times of the second algorithm are as follows: ");
        startTime = System.nanoTime();

        twoSevenBTwo(numbers[1]);

        endTime = System.nanoTime();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b2 is: ");
        System.out.println(timeDifference);

        startTime = System.nanoTime();

        twoSevenBFour(numbers[1]);

        endTime = System.nanoTime();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b4 is: ");
        System.out.println(timeDifference);

        startTime = System.nanoTime();

        twoSevenBSix(numbers[1]);

        endTime = System.nanoTime();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b6 is: ");
        System.out.println(timeDifference);

        System.out.println("The run times of the third algorithm are as follows: ");
        startTime = System.nanoTime();

        twoSevenBTwo(numbers[2]);

        endTime = System.nanoTime();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b2 is: ");
        System.out.println(timeDifference);

        startTime = System.nanoTime();

        twoSevenBFour(numbers[2]);

        endTime = System.nanoTime();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b4 is: ");
        System.out.println(timeDifference);

        startTime = System.nanoTime();

        twoSevenBSix(numbers[2]);

        endTime = System.nanoTime();

        timeDifference = endTime - startTime;

        System.out.println("The calculated runtime for question 2.7b6 is: ");
        System.out.println(timeDifference);


        scnr.close();

    }


}
