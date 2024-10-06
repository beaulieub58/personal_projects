#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void printRollTotals(int counter,int total,int die1,int die2) {



    printf("\nRoll %d is %d (%d+%d)\n", counter, total, die1, die2);

}

void printRollStatistics(int numTwo, int numThree, int numFour, int numFive, int numSix, int numSeven, int numEight, int numNine, int numTen, int numEleven, int numTwelve) {

    printf("Roll totals = to 2: %d\n",numTwo);
    printf("Roll totals = to 3: %d\n",numThree);
    printf("Roll totals = to 4: %d\n",numFour);
    printf("Roll totals = to 5: %d\n",numFive);
    printf("Roll totals = to 6: %d\n",numSix);
    printf("Roll totals = to 7: %d\n",numSeven);
    printf("Roll totals = to 8: %d\n",numEight);
    printf("Roll totals = to 9: %d\n",numNine);
    printf("Roll totals = to 10: %d\n",numTen);
    printf("Roll totals = to 11: %d\n",numEleven);
    printf("Roll totals = to 12: %d\n",numTwelve);


}

void printHistogram(int numTwo, int numThree, int numFour, int numFive, int numSix, int numSeven, int numEight, int numNine, int numTen, int numEleven, int numTwelve) {

    int i;
    
    printf(" 2: ");
    for (i = 0; i < numTwo; ++i) {

        printf("*");

    }

    printf("\n");
    printf(" 3: ");

    for (i = 0; i < numThree; ++i) {

        printf("*");

    }

    printf("\n");
    printf(" 4: ");

    for (i = 0; i < numFour; ++i) {

        printf("*");

    }

    printf("\n");
    printf(" 5: ");

    for (i = 0; i < numFive; ++i) {

        printf("*");

    }

    printf("\n");
    printf(" 6: ");

    for (i = 0; i < numSix; ++i) {

        printf("*");

    }

    printf("\n");
    printf(" 7: ");

    for (i = 0; i < numSeven; ++i) {

        printf("*");

    }

    printf("\n");
    printf(" 8: ");

    for (i = 0; i < numEight; ++i) {

        printf("*");

    }

    printf("\n");
    printf(" 9: ");

    for (i = 0; i < numNine; ++i) {

        printf("*");

    }

    printf("\n");
    printf("10: ");

    for (i = 0; i < numTen; ++i) {

        printf("*");

    }

    printf("\n");
    printf("11: ");

    for (i = 0; i < numEleven; ++i) {

        printf("*");

    }

    printf("\n");
    printf("12: ");

    for (i = 0; i < numTwelve; ++i) {

        printf("*");

    }
    printf("\n");

}



int main(void) {


   int i;
   int numRolls = 1;
   int sumTwo = 0;
   int sumThree = 0;
   int sumFour = 0;
   int sumFive = 0;
   int sumSix = 0;
   int sumSeven = 0;
   int sumEight = 0;
   int sumNine = 0;
   int sumTen = 0;
   int sumEleven = 0;
   int sumTwelve = 0;
   int die_1 = 0;
   int die_2 = 0;
   int rollTotal = 0;

   srand(time(0));

   printf("\n");

   while (numRolls > 0) {

    printf("Enter the number of rolls. Entering 0 will end the program and print results.\n");
    scanf("%d",&numRolls);

     if (numRolls >= 1) {
      // Roll dice numRoll times
      for (i = 1; i <= numRolls; ++i) {
         die_1 = rand() % 6 + 1;
         die_2 = rand() % 6 + 1;
         rollTotal = die_1 + die_2;
         
         // Count number of sixes and sevens
         if (rollTotal == 2) {
            sumTwo += 1;
         }
         else if (rollTotal == 3) {
            sumThree += 1;
         }
         else if (rollTotal == 4) {
            sumFour += 1;
         }
         else if (rollTotal == 5) {
            sumFive += 1;
         }
         else if (rollTotal == 6) {
            sumSix += 1;
         }
         else if (rollTotal == 7) {
            sumSeven += 1;
         }
         else if (rollTotal == 8) {
            sumEight += 1;
         }
         else if (rollTotal == 9) {
            sumNine += 1;
         }
         else if (rollTotal == 10) {
            sumTen += 1;
         }
         else if (rollTotal == 11) {
            sumEleven += 1;
         }
         else {
            sumTwelve += 1;
         }
         printRollTotals(i,rollTotal,die_1,die_2);

      }

      
   }

   }
    printf("\n");
    printRollStatistics(sumTwo, sumThree, sumFour, sumFive, sumSix, sumSeven, sumEight, sumNine, sumTen, sumEleven, sumTwelve);
    printf("\n");
    printHistogram(sumTwo, sumThree, sumFour, sumFive, sumSix, sumSeven, sumEight, sumNine, sumTen, sumEleven, sumTwelve);
    printf("\n");


    return 0;
}

