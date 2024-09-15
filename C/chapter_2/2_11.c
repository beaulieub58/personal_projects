#include <stdio.h>

int main(void) {
   const double HOMEWORK_MAX = 80.0;
   const double QUIZ_MAX = 20.0;
   const double MIDTERM_MAX = 40.0;
   const double FINAL_MAX = 70.0;
   const double HOMEWORK_WEIGHT = 0.10; // 20%
   const double QUIZ_WEIGHT = 0.15;
   const double MIDTERM_WEIGHT = 0.30;
   const double FINAL_WEIGHT = 0.45;

   double homeworkScore;
   double homeworkPart;
   double midtermScore;
   double midtermPart;
   double quizScore;
   double quizPart;
   double finalScore;
   double finalPart;
   double coursePercentage;

   printf("Enter homework score:\n");
   scanf ("%lf", &homeworkScore);

   printf("Enter midterm exam score:\n");
   scanf ("%lf", &midtermScore);
   
   printf("Enter quiz score:\n");
   scanf("%lf", &quizScore);

   printf("Enter final exam score:\n");
   scanf ("%lf", &finalScore);

   homeworkPart = ((homeworkScore / HOMEWORK_MAX) * HOMEWORK_WEIGHT);
   midtermPart = ((midtermScore / MIDTERM_MAX) * MIDTERM_WEIGHT);
   quizPart = ((quizScore / QUIZ_MAX) * QUIZ_WEIGHT);
   finalPart = ((finalScore / FINAL_MAX) * FINAL_WEIGHT);
   
   coursePercentage = homeworkPart + midtermPart + quizPart + finalPart;
   
   coursePercentage = coursePercentage * 100; // Convert fraction to %

   printf("Your course percentage (FIXME): ");
   printf("%lf\n", coursePercentage);

   return 0;
}