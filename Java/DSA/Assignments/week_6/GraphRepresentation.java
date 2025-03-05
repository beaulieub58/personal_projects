package Java.DSA.Assignments.week_6;

public class GraphRepresentation {


    private static int[][] matrixBuild(char[] vertexValues) {

        int[][] matrix = new int[vertexValues.length][vertexValues.length];

        for(int i = 0; i < vertexValues.length; ++i) {
            for(int j = 0; j < vertexValues.length; ++j) {

                if(i == j) {
                    matrix[i][j] = 0;
                }
                else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }

            }
        }
        return matrix;

    }

    private static void printMatrix(int[][] matrix, char[] vertices, int vertLength) {
        System.out.print("  ");
        for(int h = 0; h < vertLength; ++h) {
            System.out.print(vertices[h] + " ");
        }
        System.out.println();
        for(int i = 0; i < vertLength; ++i) {
            System.out.print(vertices[i] + " ");
            for(int j = 0; j < matrix[i].length; ++j) {

                if(matrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("ø ");

                }
                else {

                System.out.print(matrix[i][j] + " ");

                }
                
            } 
            System.out.println();
        }
        System.out.println();
    }

    private static char[] topoSort(int[][] matrix, char[] vertices) {
        int[] inDegrees = new int[matrix.length];
        //giving extra room just in case with + 1
        //this is where we send the temporary indegrees = 0
        char[] queue = new char[vertices.length+1];
        //this is where we will place the dequeued nodes in sorted order
        char[] topSorted = new char[vertices.length];
        

        //calc inDegrees
        for(int i = 0; i < matrix.length; ++i) {
            for(int j = 0; j < matrix.length; ++j) {
                //if it's not the max integer value, we know it is an indegree because we created
                //the graph edges in our adjacency matrix
                if(i != j && matrix[i][j] != Integer.MAX_VALUE) {
                    inDegrees[j]++;
                }
            }
        }
        System.out.println("The starting inDegrees for the graph are: ");
        for (int h = 0; h < inDegrees.length; ++h) {
            System.out.print(inDegrees[h] + " ");
        }
        System.out.println();
        System.out.println();

        //list adt with starting indegree 0's
        //iinitialize queue head and tail
        int head = 0;
        int tail = 0;
        //for each item in the starting in degrees array, we want to add all integer positions = 0 to the initial queue
        for (int k = 0; k < matrix.length; ++k) {
            if(inDegrees[k] == 0) {
                //increment tail AFTER assigning it the Character from the given graph
                queue[tail++] = vertices[k];
            }
        }
        int charTracker = 0;

        while (head < tail) {
            //first in first out, so we dequeue the front of the queue by popping head and incrementing it after
            char node = queue[head++];
            //we now append the popped element to the topo sort list, and increment the position we will be adding to next time
            topSorted[charTracker++] = node;

            //we added the actual character node in, so now we have to find the integer position of the character
            //node in the vertices array to ensure we look at the right address in the matrix in following steps
            int charPosition = -1;
            for (int l = 0; l < matrix.length; ++l) {
                //if vertices[index] == the dequeued node character, assign chracter position to that index
                if(vertices[l] == node) {
                    charPosition = l;
                    break;
                }
            }
            //now we loop through each column intersection of that characters row
            //and we're decrementing the neighboring indegrees
            //if the indegrees of the next array item is 0 then we add that character from vertices to the end of the queue
            //we then increment tail
            for (int m = 0; m < matrix.length; ++m) {
                if (matrix[charPosition][m] != Integer.MAX_VALUE && matrix[charPosition][m] != 0) {
                    inDegrees[m]--;
                    System.out.println("The latest inDegrees array is as follows:");
                    for (int n = 0; n < inDegrees.length; ++n) {
                        System.out.print(inDegrees[n] + " ");
                    }
                    System.out.println();
                    if (inDegrees[m] == 0) {
                        queue[tail++] = vertices[m];
                    }
                }
            }
     
        }
        System.out.println();
        System.out.println("The topologically sorted DAG is: ");
        return topSorted;
    }

    
    public static void main(String[] args) {
        char[] vertices = {'S','A','B','C','D','E','F','G','H','I','T'};
        int[][] matrix = GraphRepresentation.matrixBuild(vertices);
        //matrix no weights
        System.out.println("question 1: ");
        printMatrix(matrix, vertices ,vertices.length);

        //add  S->A
        matrix[0][1] = 1;
         //add  S->G
        matrix[0][7] = 6;
        //add  S->D
        matrix[0][4] = 4;
        //add  D->A
        matrix[4][1] = 3;
        //add  D->E
        matrix[4][5] = 3;
        //add  G->D
        matrix[7][4] = 2;
        //add  A->B
        matrix[1][2] = 2;
        //add  A->E
        matrix[1][5] = 2;
        //add  G->E
        matrix[7][5] = 1;
        //add  G->H
        matrix[7][8] = 6;
        //add  H->E
        matrix[8][5] = 2;
        //add  H->I
        matrix[8][9] = 6;
        //add  B->C
        matrix[2][3] = 2;
        //add  E->C
        matrix[5][3] = 2;
        //add  E->F
        matrix[5][6] = 3;
        //add  E->I
        matrix[5][9] = 3;
        //add  I->F
        matrix[9][6] = 1;
        //add  I->T
        matrix[9][10] = 4;
        //add  F->C
        matrix[6][3] = 1;
        //add  F->T
        matrix[6][10] = 3;
        //add  C->T
        matrix[4][10] = 4;

        //matrix with weighted edges
        System.out.println("question 1: ");
        printMatrix(matrix, vertices ,vertices.length);

        
        System.out.println("question 2: ");
        System.out.println(topoSort(matrix, vertices));

        System.out.println();



    }
}
