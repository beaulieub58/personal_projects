package Java.DSA.Assignments;
import java.util.Scanner;

public class fibonnaci_sequence {

    public static int fib(int n) {

        if (n == 1){
            return 1;
        }
        else if (n == 0){
            return 0;
        }
        else {
            return fib(n-1) + fib(n-2);
        }


    }
    
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int chosen_int = scnr.nextInt();
        System.out.println(fib(chosen_int));
        scnr.close();

    }
}

