#include <stdio.h>

int tenModulo (int number) {

    int output = number % 10;
    return output;
}

int stack (int number) {


    if (number == 0) {

        return 0;
    }

    else {
        int temp = tenModulo(number);

        stack(number / 10);

        printf("%d\n",temp);
    }

    return 0;
}


int main(void){

    int input;

    printf("Please enter a number: ");
    scanf("%d", &input);
    stack(input);

    return 0;
}