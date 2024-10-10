package ADT_Matrix;
import java.text.*;

public class MatrixOutput {
    public static void printMatrix(double[][] matrix) {
        DecimalFormat df = new DecimalFormat("0.000");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(df.format(matrix[i][j]) + "\t");
            }
            System.out.println();
        }
    }
}