package ADT_Matrix;
//import java.text.*;


public class MatrixOutput {
    public static void printMatrix(Matrix m) {
        int i,j;
        for (i = 0; i < m.getRowEff(); i++) {
            for (j = 0; j < m.getColEff(); j++) {
                System.out.printf("%.4f ",m.getElmt(i, j));
            }
            System.out.println();
        }
    }
}