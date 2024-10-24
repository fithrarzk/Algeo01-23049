package Function;
import java.text.DecimalFormat;
import ADT_Matrix.*;
import java.util.ArrayList;
import java.util.List;


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
        
        // Melakukan eliminasi Gauss untuk mencari koefisien polinomial
        MatrixOperasi Result = MatrixOperasi.gaussElimination(matrixPolim);
        return Result;
    }

    public static double interPolimVal(double[] values, double t) {
        double a0 = values[1];
        double a1 = -0.5 * values[0] + 0.5 * values[2];
        double a2 = values[0] - 2.5 * values[1] + 2 * values[2] - 0.5 * values[3];
        double a3 = -0.5 * values[0] + 1.5 * values[1] - 1.5 * values[2] + 0.5 * values[3];

        return ((a3 * t + a2) * t + a1) * t + a0;
    }

    public static String[] runInterpolasi(MatrixOperasi m, double x) {
        List<String> resultToFile = new ArrayList<>();
        double result = 0;
    
        // Eliminasi Gauss
        MatrixOperasi mTemp = MatrixOperasi.gaussElimination(m);
        double[] m1 = new double[mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, m1);
    
        // Menyusun persamaan interpolasi
        String equation = "f(x) = ";
        for (int i = m.getRowEff() - 1; i >= 0; i--) {
            double temp = (m1[i] * Math.pow(x, i));
            result += temp;
            
            if (i == m.getRowEff() - 1) {
                if (m1[i] > 0) {
                    equation += String.format("%.4fx^%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    equation += String.format("- %.4fx^%d ", m1[i], i);
                }
            } else if (i > 1 && i < m.getRowEff() - 1) {
                if (m1[i] > 0) {
                    equation += String.format("+ %.4fx^%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    equation += String.format("- %.4fx^%d ", m1[i], i);
                }
            } else if (i == 1) {
                if (m1[i] > 0) {
                    equation += String.format("+ %.4fx ", m1[i]);
                } else {
                    m1[i] *= -1;
                    equation += String.format("- %.4fx ", m1[i]);
                }
            } else {
                if (m1[i] > 0) {
                    equation += String.format("+ %.4f", m1[i]);
                } else {
                    m1[i] *= -1;
                    equation += String.format("- %.4f", m1[i]);
                }
            }
        }
    
        // Tambahkan persamaan interpolasi ke dalam list
        resultToFile.add(equation);
    
        // Hasil interpolasi pada titik x
        String resultString = String.format("f(%.4f) = %.4f", x, result);
        resultToFile.add(resultString);
    
        // Konversi list ke array string dan return
        return resultToFile.toArray(new String[0]);
    }
    
}
