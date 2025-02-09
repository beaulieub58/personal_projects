package Java.DSA.Assignments;

public class fibonnaci_no_recursion {

    public static int fib(int n) {

        int runningSum = 0;

        for (int i = 0; i <= n; ++i) {

            if (i == 0) {
               
            }
            else if (i == 1) {

                runningSum += 1;
                
            }
            else {
                runningSum += ((i-1) + (i-2));
            }
        }

        return runningSum;

    }
    
    public static void main(String[] args) {
        System.out.println(fib(3));
    }
}
