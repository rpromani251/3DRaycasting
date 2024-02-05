package util;

public class Matrix {

    /*
     *  Works only for [3][3]  * [3][1] matrix multiplications.
     *  Note, this treats the second matrix as a 1D array
     */

    public static double[] matmul(double[][] matrixA, double[] matrixB) {
        if (matrixA[0].length == matrixB.length) {  // [m][n] * [n][p] = [m][p]
            double[] returnMatrix = new double[matrixA.length];

            for (int i = 0; i < matrixA.length; i++) { // Iterate over rows of matrixA
                for (int j = 0; j < matrixB.length; j++) { // Iterate over column of matrixB
                    returnMatrix[i] += matrixB[j] * matrixA[i][j];
                }
            }
            return returnMatrix;
        }
        // Return null 1D array of length 3
        return new double[3];
    }

    // Overloaded method, using Vec3 as multiplier matrix
    public static double[] matmul(double[][] matrixA, Vec3 vec3) {

        // Convert to regular double[]
        double[] matrixB = vec3.toArray();

        if (matrixA[0].length == matrixB.length) {
            // [m][n] * [n][p] = [m][p]
            double[] returnMatrix = new double[matrixB.length];

            for (int i = 0; i < matrixB.length; i++) {
                for (int j = 0; j < matrixA.length; j++) {
                    returnMatrix[i] += matrixB[j] * matrixA[i][j];
                }
            }
            return returnMatrix;
        }
        // Return null 1D array of length 3
        return new double[3];
    }

    // Debugging and shi:

    // public static void main(String[] args) {
    //     double[][] matrix1 = {
    //         {2, 3, 4},
    //         {5, 6, 7},
    //         {8, 9, 10}
    //     };
    //     double[] matrix2 = {1, 2, 3};
        

    //     System.out.println(Arrays.toString(matmul(matrix1, matrix2)));
    // }
}
 