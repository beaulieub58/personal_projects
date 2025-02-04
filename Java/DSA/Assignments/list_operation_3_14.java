package Java.DSA.Assignments;
import java.util.Scanner;


public class list_operation_3_14 {

    public static int[] createList(int listSize, Scanner scnr) {

        int[] list = new int[listSize];
        //loop through a specified list size and enter values to add to the list
        for (int i = 0; i < listSize; ++i) {
            System.out.println("Enter list input (integer)");
            list[i] = scnr.nextInt();
        }

        return list;

    }
    //return an intersected list, "assuming the size of L1 is n and the size of L2 is n, too"
    public static int[] unionedList(int[] list1, int[]list2) {
        
        int dominantLength = 0;

        //the intersected list (for non duplicate values, needs to be initiated to the bigger list)
        if(list1.length >= list2.length) {
            dominantLength = list1.length;
        }
        else {
            dominantLength = list2.length;
        }
        int[] intersection = new int[dominantLength];

        int counter = 0;

        //loop through the first list
        for (int i = 0; i < list1.length; ++i) {

            //loop through the second list while looping through the first
            for (int j = 0; j < list2.length; ++j) {
                //if list1[i] == any value in the length of list w, add it to the intersection at position "counter"
                if (list1[i] == list2[j]) {
                    intersection[counter] = list1[i];
                    //increment counter if we add it in
                    counter += 1;
                }

            }
        }
        //if counter never reaches past 0, the two lists didnt have an intersection
        if (counter == 0) {
            System.out.println("No intersection exists:");
            return intersection;
        }
        //instantiate a resized list the size of counter
        else {
            System.out.println("The following is an intersection of list 1 and list 2:");
            int[] resizedIntersection = new int[counter];
            for (int k = 0; k < counter; ++k) {
                //give the shortened array the first "counter" number of items and return that array
                resizedIntersection[k] = intersection[k];
            }
            return resizedIntersection;

        }
    }
    
    //dedupe the concatted list of common integers
    public static int[] dedupeList(int[] list) {
        //instantiate a new list to hold the deduped values
        int[] placeholder = new int[list.length];
        //declare a counter variable that will hold the position of the deduped variable added
        //this will also eventually be the length of the reduced primative array
        int counter = 0;

        //for i in the argument
        for (int i = 0; i < list.length; ++i) {
            //declare a boolean in each outter loop that is set to false
            //duplicate is initiated as false, because we haven't determined if list[i] is a duplicate yet
            boolean duplicate = false;
            //loop through through it placeholder list
            //when it's empty, there will be nothing to loop through yet
            for (int j = 0; j < counter; ++j) {

                //if list[i] is equal to any of the values in placeholder, turn the duplicate boolean to true
                if(list[i] == placeholder[j]) {
                    duplicate = true;
                }
            }

            //in each loop, when duplicate is false, we can add that value to the placeholder list
            if(!duplicate) {
                placeholder[counter] = list[i];
                //increment counter only when we add something to the placeholder list, so we can keep track of the length of the
                //new array size and the number of times the inner loop above needs to iterate
                counter += 1;
            }
        }
        //instantiate a new list that will be the size of counter, to account for the number of non-duplicate values
        int[] outputList = new int[counter];

        //loop through counter times
        for (int k = 0; k < counter; ++k) {
            //give the shortened array the first "counter" number of items and return that array
            outputList[k] = placeholder[k];
        }

        return outputList;

    }
    //I did not have good intuition on how to dedupe the intersected lists, I did research and google how to do this using primative array types only
    
    public static void printList(int[] list) {
        for (int i = 0; i < list.length; ++i) {
            System.out.print(list[i]);
            if(i != (list.length - 1)) {
            System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String args[]) {

        //build the first list
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a list size for your first list:");
        int firstListSize = scnr.nextInt();
        int[] firstList = new int[firstListSize];
        firstList = createList(firstListSize, scnr);
        System.out.println("The first list is as follows:");
        list_operation_3_14.printList(firstList);

        System.out.println();

        //build the second list
        System.out.println("Enter a list size for your second list:");
        int secondListSize = scnr.nextInt();
        int[] secondList = new int[secondListSize];
        secondList = createList(secondListSize, scnr);
        System.out.println("The second list is as follows:");
        list_operation_3_14.printList(secondList);

        scnr.close();

        
        System.out.println("The intersection of the two lists is:");
        //find the intersection of the two lists
        printList(unionedList(firstList,secondList));

        System.out.println();

        //deduped union
        System.out.println("The deduped intersection is:");
        printList(dedupeList(unionedList(firstList, secondList)));
        ;
        

    }
    
}