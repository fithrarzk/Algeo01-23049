package Function;
import java.text.DecimalFormat;
import ADT_Matrix.*;

public class InterpolasiPolinomial {
    public static double[][] InterPolim (double[][] matrix){ //Membentuk solusi masing-masing dari a0 - aN
        int n = MatrixOperasi.getRowEff(matrix);
        double[][] matrixPolim = new double [n][n+1];
        for (int i=0; i<n; i++){
            for (int j=0; j<=n; j++){
                if (j == n){
                    matrixPolim [i][0] = matrix [i][1];
                }
                else {
                    matrixPolim [i][j] = Math.pow(matrix[i][0], j);
                }
            }
        } 
        MatrixOperasi.gaussJordanElimination(matrixPolim);
        return matrixPolim;
    }
    
    public static int InterPolimVal (double[][] matrix, int x){ //mencari nilai y dari suatu x  
        double [][] matrixPolim = InterPolim(matrix);
        int val = 0, pangkat = 0;
        for (int i=0; i<MatrixOperasi.getRowEff(matrixPolim); i++){
            val += matrixPolim[i][MatrixOperasi.getLastIdxCol(matrixPolim)] * Math.pow(x, pangkat);
            pangkat++;
        }
        return val;
    }
    
    public static String hasilInterPolim (double[][] matrix){ //membentuk string berisi hasil
        double [][] matrixPolim = InterPolim(matrix);
        String nilai = "P(x) = ";
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i=0; i<MatrixOperasi.getRowEff(matrix); i++){
            if (i == 0){
                nilai += df.format(matrixPolim [i][MatrixOperasi.getColEff(matrix)-1]);
            }
            else if (i == 1){
                if (matrixPolim [i][MatrixOperasi.getColEff(matrix)-1] < 0){
                    nilai += "-" + df.format(Math.abs(matrixPolim [i][MatrixOperasi.getColEff(matrix)-1])) + "x";
                }
                else {
                    nilai += "+" + df.format(matrixPolim [i][MatrixOperasi.getColEff(matrix)-1]) + "x";
                }
            }
            else{
                if (matrixPolim [i][MatrixOperasi.getColEff(matrix)-1] < 0){
                    nilai += "-" + df.format(Math.abs(matrixPolim [i][MatrixOperasi.getColEff(matrix)-1])) + "x^" + i;
                }
                else {
                    nilai += "+" + df.format(matrixPolim [i][MatrixOperasi.getColEff(matrix)-1]) + "x^" + i;
                }                
            }
        }
        return nilai;
    }
}
