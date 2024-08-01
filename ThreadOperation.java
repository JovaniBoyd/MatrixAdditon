public class ThreadOperation extends Thread {
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] matrixC;
    private String indicator;

    public ThreadOperation(int[][] matrixA, int[][] matrixB, int[][] matrixC, String indicator) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixC = matrixC;
        this.indicator = indicator;
    }

    @Override
    public void run() {

        int[] q1 = getQuadrantIndexes(matrixA.length, matrixA[0].length, indicator);// 1) Grab Quadrant initated by string passed through indicator

     /*   int row = matrixA.length;// this helped a lot
        int col = matrixA[0].length;
        matrixC = new int[row][col];
        matrixC = addSubmatrix(q1[0], q1[1], q1[2], q1[3], matrixA, matrixB, matrixC);// 2) Use return value of quadrant to pass though method and add quadrants of both matrix
                                                                                        // 3) matrixC added to provide a return value (sum of matrix A and B)
                                                                                        */
      //instead of calling addSubmatrix as shown above, added a simplied version to run()
        int rowS = q1[0];
        int rowE = q1[1];
        int columnS = q1[2];
        int columnE = q1[3];

        for (int i = rowS; i < rowE; ++i)// verified technique from here, minus exception e : https://stackoverflow.com/questions/4769976/reading-2-d-array-from-a-file
        {
            for (int j = columnS; j < columnE; ++j) {
                matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }


    }

    public static int[] getQuadrantIndexes(int rows, int columns, String quadrant) {
        int startRow = 0;
        int endRow = 0;
        int startColumn = 0;
        int endColumn = 0;
        switch (quadrant) {// trying out a switch
            case "UL":
                endRow = rows / 2;
                endColumn = columns / 2;

                break;
            case "UR":
                endRow = rows / 2;
                startColumn = columns / 2;
                endColumn = columns;//removed minus 1

                break;
            case "LL":
                startRow = rows / 2;
                endRow = rows;//
                endColumn = columns / 2;

                break;
            case "LR":
                startRow = rows / 2;
                endRow = rows;//
                startColumn = columns / 2;
                endColumn = columns;//

                break;
        }
        int[] nQuadrant = {startRow, endRow, startColumn, endColumn};
        return nQuadrant;
    }

    /*
    public static int[][] addSubmatrix(int rowS, int rowE, int colS, int colE, int[][] matrixA, int[][] matrixB, int[][] matrixC) {// modify to add

        int row = matrixA.length;// Matrix A, B, and C all have same rows/columns
        int col = matrixA[0].length;
        //      int[][] matrix = new int[row][col];

        for (row = rowS; row < rowE; row++) {
            for (col = colS; col < colE; col++) {
                matrixC[row][col] = matrixA[row][col] + matrixB[row][col];
                //  System.out.printf("%5d", matrix[row][col]);// here add matrix a index plus matrix b index equals matrix c


            }
            //   System.out.println();
        }
        return matrixC;

    }*/


}
