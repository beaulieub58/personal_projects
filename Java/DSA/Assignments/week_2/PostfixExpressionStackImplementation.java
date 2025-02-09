package Java.DSA.Assignments.week_2;
import java.util.Stack;


public class PostfixExpressionStackImplementation {


    public static double postFix(String input) {

        Stack<Double> dbl = new Stack<Double>();
        double firstDouble;
        double secondDouble;
        double output = 0.0;
        boolean isNumeric;


        for(int i = 0; i < input.length(); ++i) {

            isNumeric = true;

            try {
                output = Double.parseDouble(String.valueOf(input.charAt(i)));
            }
            catch (NumberFormatException e) {
                isNumeric = false;
            }

            if(isNumeric) {

                dbl.push(output);

            }

            else {
                secondDouble = dbl.pop();
                firstDouble = dbl.pop();
                switch (input.charAt(i)) {
                    case '+':
                        dbl.push(firstDouble+secondDouble);
                        break;
                    case '-':
                        dbl.push(firstDouble-secondDouble);
                        break;
                    case '*':
                        dbl.push(firstDouble*secondDouble);
                        break;
                    case '/':

                        dbl.push(firstDouble/secondDouble);
                        break;
                    case '^':
                        dbl.push(Math.pow(firstDouble,secondDouble));
                    default:
                        throw new IllegalArgumentException();
                }
            }

        }
        return dbl.peek();
    }

    public static void main (String[] args) {
        String input = new String("54+3*");
        System.out.println(postFix(input));
    }

}