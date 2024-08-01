/*
Jovani Boyd
11/14/2023
2251 Interm Programming
*/

//To fix my previous code I did three things
//1)Instead of printing all the quadrants, I printed C as a whole. This is super obvious now in retrospect.
//2)I changed the run in ThreadOperation to basically be the addSubmatrix method without printing.
//3)I then moved the location where I declared MatrixC in main, I suspect I was getting errors because it's row and columns were zero, so the rest was out of bounds


// In instances like this assigment, the amount of time and data being saved may not be obvious, but the bigger the output the more likely it is to see the effects.
// Multi-threading a matrix like this, there is more access given to more spread-out, random locations, as opposed to one element to the next, counting up by 1. This is better for data and statistics.
// When the different pinpoints on the grid can be accessed early it can be more efficient in predicting output, this would especially be useful with sorted data and data that wants to be collected or predicted
// Multi-threading also shares resources and memory at different instances, this can help save time when the processor decides to switch and work on other tasks while waiting for its previous one.

import java.io.File;
import java.util.Scanner;
import java.io.IOException;


public class Main {
    public static void main(String args[]) {

        int row = 0, column = 0;
        int[][] matrixA = new int[row][column];
        int[][] matrixB = new int[row][column];
        //int[][] matrixC = new int[row][column];


        try {
            Scanner input = new Scanner(new File(args[0]));//Open up file via cmd prompt

            row = input.nextInt();
            column = input.nextInt();// read row and column from text

            matrixA = matrixFromFile(row, column, input);// matrix a
            matrixB = matrixFromFile(row, column, input);// matrix b


        } catch (IOException e) {
            System.out.println("error IO exception: " + e.getMessage());
            System.exit(1);
        }
        int[][] matrixC = new int[row][column];// I MOVED THIS FROM LINE 28 TO HERE AND THE WHOLE THING STARTED WORKING

        print2dArray(matrixA);
        System.out.println();
        print2dArray(matrixB);
        System.out.println();

        //creating a total of 4 thread objects
        ThreadOperation T1 = new ThreadOperation(matrixA, matrixB, matrixC, "UL");
        ThreadOperation T2 = new ThreadOperation(matrixA, matrixB, matrixC, "LL");
        ThreadOperation T3 = new ThreadOperation(matrixA, matrixB, matrixC, "UR");
        ThreadOperation T4 = new ThreadOperation(matrixA, matrixB, matrixC, "LR");


        T1.start();
        T2.start();
        T3.start();
        T4.start();

        try {
            T1.join();//won't always print in numerical order
            T2.join();
            T3.join();
            T4.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("\nFINISHED\n");

        System.out.println("Sum:");
        print2dArray(matrixC);


    }


    public static int[][] matrixFromFile(int row, int column, Scanner input) {// should be called twice

        int[][] matrix = new int[row][column];

        for (int i = 0; i < row; ++i)// verified technique from here, minus exception e : https://stackoverflow.com/questions/4769976/reading-2-d-array-from-a-file
        {
            for (int j = 0; j < column; ++j) {
                if (input.hasNextInt()) {
                    matrix[i][j] = input.nextInt();
                }
            }
        }

        return matrix;

    }

    private static void print2dArray(int[][] array) {
        for (int row = 0; row < array.length; row++) {// https://stackoverflow.com/questions/49782681/how-to-print-an-array-using-printf
            for (int col = 0; col < array[0].length; col++) {// UPDATE: changed to 2D Array : https://medium.com/buzz-code/java-8-two-dimensional-array-35542b4ced9
                System.out.printf("%5d", array[row][col]);
            }
            System.out.println();

        }

    }


}
