package Java.DSA.Assignments.week_7;

public class APSP {

    private static int[][] matrixBuild(char[] vertexValues) {

        int[][] matrix = new int[vertexValues.length][vertexValues.length];

        for(int i = 0; i < vertexValues.length; ++i) {
            for(int j = 0; j < vertexValues.length; ++j) {

                if(i == j) {
                    matrix[i][j] = 0;
                }
                else {
                    matrix[i][j] = NOT_A_VERTEX;
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

                if(matrix[i][j] == NOT_A_VERTEX) {
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

    private static int[][] allPairs(int[][] adjMatrix) {

        int[][] shortPath = new  int [adjMatrix.length][adjMatrix.length];
        int[][] path = new  int [adjMatrix.length][adjMatrix.length];

        

        for(int i = 0; i < adjMatrix.length; ++i) {
            for(int j = 0; j < adjMatrix.length; ++j) {
                shortPath[i][j] = adjMatrix[i][j];
                path[i][j] = NOT_A_VERTEX;
            }
        }
        for(int k = 0; k < adjMatrix.length; ++k) {
            for(int l = 0; l < adjMatrix.length; ++l) {
                for(int m = 0; m < adjMatrix.length; ++m) {
                    if(shortPath[l][k] + shortPath[k][m] < shortPath[l][m])
                        shortPath[l][m] = shortPath[l][k] + shortPath[k][m];
                        path[l][m] = k;       
                    }
                }
            }
        
     return shortPath;
    }

    private static final int NOT_A_VERTEX = 100;

    public static void main(String[] args) {
        char[] vertices = {'A','B','C','D','E','F','G'};
        int[][] matrix = APSP.matrixBuild(vertices);
        //matrix no weights
        System.out.println("The dp matrix is as follows: ");
        printMatrix(matrix, vertices ,vertices.length);

        //add  A->B
        matrix[0][1] = 5;
        //add A->C
        matrix[0][2] = 3;
        //add B->C
        matrix[1][2] = 2;
        //add B->E
        matrix[1][4] = 3;
        //add B->G
        matrix[1][6] = 1;
        //add C->D
        matrix[2][3] = 7;
        //add C->E
        matrix[2][4] = 7;
        //add D->A
        matrix[3][0] = 2;
        //add D->F
        matrix[3][5] = 6;
        //add E->F
        matrix[4][5] = 1;
        //add E->D
        matrix[4][3] = 2;
        //add G->E
        matrix[6][4] = 1;


        //matrix with weighted edges
       System.out.println("The matrix with weighted edges: ");
       printMatrix(matrix, vertices ,vertices.length);
       
       System.out.println("The shortest path all pairs matrix: ");
       printMatrix(allPairs(matrix),vertices,vertices.length);
    
    }
}
