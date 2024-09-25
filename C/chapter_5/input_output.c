#include <stdio.h>

readFile(char filename[50]) {

    FILE* inputFile = NULL;
    char letter = ' ';

    inputFile = fopen(filename,"r");

    if (inputFile == NULL) {

        printf("Could not read file %s",filename);
        return -1;
    }

    fscanf(inputFile,"%c", &letter);
    fclose(inputFile);
    return letter;

}