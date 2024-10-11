#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define arraySize 3
#define MIN 1
#define MAX 100

void populateArrays(int array1[arraySize], int array2[arraySize]) {

    int i;

    for (i = 0; i < arraySize; ++i) {

        array1[i] = (rand() % MAX) + MIN;
        printf("%d ",array1[i]);
    }

    printf("\n");

      for (i = 0; i < arraySize; ++i) {

        array2[i] = (rand() % MAX) + MIN;
        printf("%d ",array2[i]);
    }



}

void mergeArrays(int array1[arraySize], int array2[arraySize]) {

    int array3[arraySize * 2];
    int i;

    for (i = 0; i < arraySize; ++i) {

        array3[i] = array1[i];
        array3[arraySize + i] = array2[i];
        

    }

    for (i = 0; i < (arraySize * 2); ++i)

        printf("%d ",array3[i]);


}

int main(void) {

    int arr1[arraySize];
    int arr2[arraySize];

    srand(time(0));

    populateArrays(arr1,arr2);

    printf("\n");

    mergeArrays(arr1,arr2);
    
    getchar();

    return 0;




}