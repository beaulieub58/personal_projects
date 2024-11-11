package Java.test_files;
import java.util.Scanner;

public class ForLoop {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        final int ARRAY_LENGTH = 4;
        int[] inputArray = new int[ARRAY_LENGTH];

        for (int i = 0; i < ARRAY_LENGTH; ++i) {

            inputArray[i] = scnr.nextInt();


        }

        System.out.println(inputArray[0]);

    }
    
}
