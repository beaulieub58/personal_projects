#include <stdio.h>

int main(void){

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


    int userAgeYears;
    int userAgeDays;
    int userAgeMinutes;
    int userAgeSeconds;
    int totalHeartbeats;
    int totalSneezes;
    int totalCaloriesBurned;
    int totalCheesePizzasEaten;
    int breathesPerMinute;
    int totalBreathesTaken;

    printf("Enter your age in years: ");
    scanf("%d", &userAgeYears);

    printf("\n");
   
    userAgeDays = userAgeYears * DAYS_IN_YEARS;  // Calculate days without leap years
    userAgeDays = userAgeDays + (userAgeYears / LEAP_YEAR_INPUT); // Add days for leap years
   
    printf("You are %d days old.\n", userAgeDays);
   
    userAgeMinutes = userAgeDays * HOURS_IN_DAYS * MINUTES_IN_HOUR;   // 24 hours/day, 60 minutes/hour
    printf("You are approximately %d minutes old.\n", userAgeMinutes);   

    userAgeSeconds = userAgeMinutes * SECONDS_IN_MINUTES;
    printf("You are approximately %d seconds old.\n", userAgeSeconds);

    totalHeartbeats = userAgeMinutes * AVG_BEATS_PER_MINUTE;
    printf("Your heart has beat approximately %d times.\n", totalHeartbeats);

    totalSneezes = userAgeDays * SNEEZES_PER_DAY;
    printf("You have sneezed approximately %d times.\n", totalSneezes);

    totalCaloriesBurned = userAgeDays * CALORIES_BURNED_PER_DAY;
    printf("You have burned approximately %d calories.\n", totalCaloriesBurned);

    totalCheesePizzasEaten = totalCaloriesBurned / CALORIES_IN_CHEESE_PIZZA;
    printf("The number of calories burned throughout your lifetime equates to approximately %d cheese pizzas.\n", totalCheesePizzasEaten);

    breathesPerMinute = BREATHES_PER_HOUR / MINUTES_IN_HOUR;

    totalBreathesTaken = breathesPerMinute * userAgeMinutes;
    printf("The number of breaths you've taken throughout your lifetime is approximately %d.\n",totalBreathesTaken);

    printf("\n");
    getchar();
    return 0;
}