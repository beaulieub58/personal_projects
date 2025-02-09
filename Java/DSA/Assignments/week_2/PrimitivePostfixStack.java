package Java.DSA.Assignments.week_2;

public class PrimitivePostfixStack {

    public static double postFix(String input) {
       //instantiate a primitive array of type double with an arbitrary length
        double[] dbl = new double[100];
        //declare the two variables to pop in the lpostfix
        double firstDouble;
        double secondDouble;
        //declare a starting double variable (I was getting an error without doing this)
        double output = 0.0;
        //start the head of the stack at -1 since nothing has been pushed on to it yet
        int head = -1;

        //for i in the length of the input string
        for(int i = 0; i < input.length(); ++i) {

            //declare a true numeric field to determine if the string item is a number or not
            boolean isNumeric = true;
            //used a try and catch block, but I'm sure there's a more elegant way to do this 
            try {
                //attempt to parse the string character at position i
                //if it's able to be parsed, isNumeric stays true
                output = Double.parseDouble(String.valueOf(input.charAt(i)));       
            }
            catch (NumberFormatException e) {
                //if not, it has to be an operand, and isNumeric turns false
                isNumeric = false;

            }
            //if it's a numeric
            if(isNumeric) {
                //increment head from -1 to 0 and push the parsed double on to the stack
                head += 1;
                dbl[head] = output;
            }
            else {
                //if it's an operand, we need to pop these items, starting with the most recently added variable
                secondDouble = dbl[head];
                //decrement head so we know that the top of the stack is now the second double in line
                head -= 1;
                firstDouble = dbl[head];
                //decrement head again
                head -= 1;
                //switch statement to complete the expression
                switch (input.charAt(i)) {
                    case '+':
                        head += 1;
                        dbl[head] = firstDouble + secondDouble;
                        break;
                    case '-':
                        head += 1;
                        dbl[head] = firstDouble - secondDouble;
                        break;
                    case '*':
                        head += 1;
                        dbl[head] = firstDouble * secondDouble;
                        break;
                    case '/':
                        head += 1;
                        dbl[head] = firstDouble / secondDouble;
                        break;
                    case '^':
                        head += 1;
                        dbl[head] = Math.pow(firstDouble, secondDouble);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }  
        //at last, return the top item on the list 
        return dbl[head];
    }

    public static void main (String[] args) {
        String input = new String("54+3*");
        System.out.println(postFix(input));
    }

}


