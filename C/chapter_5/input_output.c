#include <stdio.h>

char readFile(char filename[50]) {

    FILE* inputFile = NULL;
    char letter = ' ';

    inputFile = fopen(filename,"r");

    if (inputFile == NULL) {

        printf("Could not read file %s\n",filename);
        return -1;
    }

    fscanf(inputFile,"%c", &letter);
    fclose(inputFile);
    return letter;

}

void printTriange(char inputLetter, char filename[50],const char *operation) {

    FILE* outputFile = fopen(filename,operation);
    
    fprintf(outputFile,"  %c\n",inputLetter);
    fprintf(outputFile," %c%c%c\n",inputLetter,inputLetter,inputLetter);
    fprintf(outputFile,"%c%c%c%c%c\n",inputLetter,inputLetter,inputLetter,inputLetter,inputLetter);
    fprintf(outputFile,"\n");
    fclose(outputFile);

}

void printSquare(char inputLetter,char filename[50],const char *operation) {

    FILE* outputFile = fopen(filename,operation);
    
    fprintf(outputFile,"%c%c%c%c%c\n",inputLetter,inputLetter,inputLetter,inputLetter,inputLetter);
    fprintf(outputFile,"%c   %c\n",inputLetter,inputLetter);
    fprintf(outputFile,"%c   %c\n",inputLetter,inputLetter);
    fprintf(outputFile,"%c%c%c%c%c\n",inputLetter,inputLetter,inputLetter,inputLetter,inputLetter);
    fprintf(outputFile,"\n");
    fclose(outputFile);



}

void printDiamond(char inputLetter,char filename[50],const char *operation) {

    FILE* outputFile = fopen(filename,operation);

    fprintf(outputFile,"  %c\n",inputLetter);
    fprintf(outputFile," %c%c%c\n",inputLetter,inputLetter,inputLetter);
    fprintf(outputFile,"%c%c%c%c%c\n",inputLetter,inputLetter,inputLetter,inputLetter,inputLetter);
    fprintf(outputFile," %c%c%c\n",inputLetter,inputLetter,inputLetter);
    fprintf(outputFile,"  %c\n",inputLetter);
    fprintf(outputFile,"\n");
    fclose(outputFile);
    
}

int main(void) {

    char letter = readFile("input.txt");
    printTriange(letter,"output.txt","w");
    printSquare(letter,"output.txt","a");
    printDiamond(letter,"output.txt","a");

    return 0;

}