#include <stdio.h>

double division(double dividend, double divisor ) {

    return dividend / divisor;
    
}

double multiplication(double multiple_1, double multiple_2) {

    return multiple_1 * multiple_2;

}

double addition(double add_1, double add_2) {

    return add_1 + add_2;

}

double subtraction(double starter, double subtractor){

    return starter - subtractor;
}

int main(void) {

    int selection;
    double input_1;
    double input_2;
    double output;

    printf("Select Number:\n1. For Addition\n2. For Subtraction\n3. For Multiplication\n4. For Division\n");
    scanf("%d", &selection);
    printf("Enter your first input in decimal format:\n");
    scanf("%lf", &input_1);
    printf("Enter your second input in decimal format:\n");
    scanf("%lf", &input_2);

    if (selection == 1) {
        
        output = addition(input_1, input_2);

    }

    else if (selection == 2) {

        output = subtraction(input_1, input_2);

    }

    else if (selection == 3) {

        output = multiplication(input_1, input_2);


    }

    else {

        output = division(input_1, input_2);
    }

    printf("%.2lf",output);

    return 0;

}