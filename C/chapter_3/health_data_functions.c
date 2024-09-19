#include <stdio.h>

const int DAYS_IN_YEARS = 365;
const int LEAP_YEAR_INPUT = 4;
const int HOURS_IN_DAYS = 24;
const int MINUTES_IN_HOUR = 60;
const int SECONDS_IN_MINUTES = 60;
const int AVG_BEATS_PER_MINUTE = 72;
const int SNEEZES_PER_DAY = 4; // found the number of sneezes per day online
const int CALORIES_BURNED_PER_DAY = 2000; // somewhat of a middle ground between men and women daily burned cals
const int CALORIES_IN_CHEESE_PIZZA = 2269; // based on internet research
const int BREATHES_PER_HOUR = 960; //based on internet research

int multiplicationFunction(int given, int multiplier){

    int output = given * multiplier;

    return output;

}


int divisionFunction(int given, int divisor){

    int output = given / divisor;

    return output;

}

int main(void){

    int userAgeYears;
    int userAgeDays;
    //int userAgeMinutes;
    //int userAgeSeconds;
    //int totalHeartbeats;
    //int totalSneezes;
    //int totalCaloriesBurned;
    //int totalCheesePizzasEaten;
    //int breathesPerMinute;
    //int totalBreathesTaken;

    printf("Enter your age in years: ");
    scanf("%d", &userAgeYears);

    printf("\n");

    printf("You are approximately %d days old.\n", multiplicationFunction(userAgeYears, DAYS_IN_YEARS));
   
    userAgeDays = multiplicationFunction(userAgeYears, DAYS_IN_YEARS) + divisionFunction(userAgeYears, LEAP_YEAR_INPUT); // Add days for leap years
   
    //userAgeMinutes = userAgeDays * HOURS_IN_DAYS * MINUTES_IN_HOUR;   // 24 hours/day, 60 minutes/hour
    printf("You are approximately %d minutes old.\n", multiplicationFunction(multiplicationFunction(userAgeDays, HOURS_IN_DAYS), MINUTES_IN_HOUR));   

    //userAgeSeconds = userAgeMinutes * SECONDS_IN_MINUTES;
    printf("You are approximately %d seconds old.\n", multiplicationFunction(multiplicationFunction(multiplicationFunction(userAgeDays, HOURS_IN_DAYS), MINUTES_IN_HOUR), SECONDS_IN_MINUTES));

    //totalHeartbeats = userAgeMinutes * AVG_BEATS_PER_MINUTE;
    printf("Your heart has beat approximately %d times.\n", multiplicationFunction(multiplicationFunction(multiplicationFunction(userAgeDays, HOURS_IN_DAYS), MINUTES_IN_HOUR), AVG_BEATS_PER_MINUTE));

    //totalSneezes = userAgeDays * SNEEZES_PER_DAY;
    printf("You have sneezed approximately %d times.\n", multiplicationFunction(userAgeDays, SNEEZES_PER_DAY));

    //totalCaloriesBurned = userAgeDays * CALORIES_BURNED_PER_DAY;
    printf("You have burned approximately %d calories.\n", multiplicationFunction(userAgeDays, CALORIES_BURNED_PER_DAY));

    //totalCheesePizzasEaten = totalCaloriesBurned / CALORIES_IN_CHEESE_PIZZA;
    printf("The number of calories burned throughout your lifetime equates to approximately %d cheese pizzas.\n", divisionFunction(multiplicationFunction(userAgeDays, CALORIES_BURNED_PER_DAY), CALORIES_IN_CHEESE_PIZZA));

    //breathesPerMinute = BREATHES_PER_HOUR / MINUTES_IN_HOUR;
    //breathesPerMinute = divisionFunction(BREATHES_PER_HOUR, MINUTES_IN_HOUR);

    //totalBreathesTaken = breathesPerMinute * userAgeMinutes;
    printf("The number of breaths you've taken throughout your lifetime is approximately %d.\n",multiplicationFunction(divisionFunction(BREATHES_PER_HOUR, MINUTES_IN_HOUR),multiplicationFunction(multiplicationFunction(userAgeDays, HOURS_IN_DAYS), MINUTES_IN_HOUR)));

    printf("\n");
    getchar();
    return 0;
}