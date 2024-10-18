package Function;
import java.text.DecimalFormat;
import ADT_Matrix.*;

public class InterpolasiPolinomial {
    public static MatrixOperasi InterPolim (MatrixOperasi matrix, Boolean m){ //Membentuk solusi masing-masing dari a0 - aN
        // m = true jika pembacaan memakai file
        int n;
        if (m){
            n = matrix.getRowEff()-1;
        }
        else{
            n = matrix.getRowEff();
        }

        MatrixOperasi matrixPolim = new MatrixOperasi(n, n+1);
        
        // Membentuk matriks augmented dari nilai x dan y
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) { // Hanya sampai kolom ke-n (bukan n+1)
                matrixPolim.setElmt(i, j, Math.pow(matrix.getElmt(i, 0), j)); // Kolom 0 pada matrix adalah nilai x
            }
            // Kolom terakhir diisi dengan nilai y
            matrixPolim.setElmt(i, n, matrix.getElmt(i, 1)); // Kolom 1 pada matrix adalah nilai y
        }
    
        //System.out.println("\nSEBELUM GAUSS\n");
        //MatrixOutput.printMatrix(matrixPolim);
        
        // Melakukan eliminasi Gauss untuk mencari koefisien polinomial
        MatrixOperasi Result = MatrixOperasi.gaussElimination(matrixPolim);
        //System.out.println("\nSESUDAH GAUSS\n");
        //MatrixOutput.printMatrix(Result);
        return Result;
    }

    public static void runInterpolasi (MatrixOperasi m, double x){
        double result = 0;
        // Eliminasi Gauss
        MatrixOperasi mTemp = MatrixOperasi.gaussElimination(m);
        double[] m1 = new double [mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, m1);

        System.out.print("f(x) = ");
        for (int i = m.getRowEff() - 1; i >= 0; i--) {
            double temp = (m1[i] * Math.pow(x, i));
            result += temp;
            if (i == m.row - 1){
                if (m1[i] > 0){
                    System.out.printf("%.4fx^%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx^%d ", m1[i], i);
                }
            }else if (i > 1 && i < m.row - 1){
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx^%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx^%d ", m1[i], i);
                }
            } else if ( i == 1){
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx ", m1[i]);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx ", m1[i]);
                }
            } else{
                if (m1[i] > 0){
                    System.out.printf("+ %.4f, ", m1[i]);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4f, ", m1[i]);
                }
            }
        }
        System.out.printf("\nf(%.4f) = %.4f\n", x, result);
    }
    
    /* 
    public static double InterPolimVal (MatrixOperasi matrix, double x){ //mencari nilai y dari suatu x  
        MatrixOperasi matrixPolim = InterPolim(matrix);
        double val = 0, pangkat = 0;
        for (int i=0; i<matrixPolim.getRowEff(); i++){
            val += matrixPolim.getElmt(i,matrixPolim.getLastIdxCol()) * Math.pow(x, pangkat);
            pangkat++;
        }
        return val;
    }
    
    public static String hasilInterPolim (MatrixOperasi matrix){ //membentuk string berisi hasil
        MatrixOperasi matrixPolim = InterPolim(matrix);
        //MatrixOutput.printMatrix(matrixPolim);
        String nilai = "P(x) = ";
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i=0; i<matrix.getRowEff()-1; i++){
            if (i == 0){
                nilai += df.format(matrixPolim.getElmt(i, matrix.getColEff()-1));
            }
            else if (i == 1){
                if (matrixPolim.getElmt(i, matrix.getColEff()-1) < 0){
                    nilai += "-" + df.format(Math.abs(matrixPolim.getElmt(i, matrix.getColEff()-1))) + "x";
                }
                else {
                    nilai += "+" + df.format(matrixPolim.getElmt(i, matrix.getColEff()-1))  + "x";
                }
            }
            else{
                if (matrixPolim.getElmt(i, matrix.getColEff()-1) < 0){
                    nilai += "-" + df.format(Math.abs(matrixPolim.getElmt(i, matrix.getColEff()-1))) + "x^" + i;
                }
                else {
                    nilai += "+" + df.format(matrixPolim.getElmt(i, matrix.getColEff()-1)) + "x^" + i;
                }                
            }
        }
        return nilai;
    }
    */
}
