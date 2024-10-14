package ADT_Matrix;
//import java.text.*;


public class MatrixOutput {
    public static void printMatrix(MatrixOperasi m) {
        int i,j;
        for (i = 0; i < m.getRowEff(); i++) {
            for (j = 0; j < m.getColEff(); j++) {
                System.out.printf("%.4f ",m.getElmt(i, j));
            }
            System.out.println();
        }
    }

    public static String matrixToString(MatrixOperasi matrix) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrix.getRowEff(); i++) {
            for (int j = 0; j < matrix.getColEff(); j++) {
                result.append(String.format("%.3f ", matrix.getElmt(i, j))); // Format each element with 3 decimal places
            }
            result.append("\n");  // Move to the next line after each row
        }
        return result.toString();
    }
}