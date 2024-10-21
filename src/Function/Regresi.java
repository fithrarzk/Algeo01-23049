package Function;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import ADT_Matrix.*;
import ADT_Matrix.MatrixInput;
import ADT_Matrix.MatrixOperasi;

public class Regresi {       
    public static void regresiLinearKeyboard(MatrixOperasi m){
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        MatrixOperasi mTemp;
        double [] x;

        x = new double [m.col - 1];
        
        //Input nilai-nilai xk yang ingin ditaksir
        System.out.println("Masukkan nilai x yang ingin ditaksir: ");
        for(i = 0; i < x.length; i++){
            x[i] = MatrixInput.input.nextDouble();
        }

        //Membuat matriks baru semacam SPL
        mTemp = new MatrixOperasi (m.col, m.col + 1);

        for(i = 0 ; i < mTemp.row ; i++){
            for(j = 0 ; j < mTemp.col ; j++){
                if(i == 0 && j == 0){
                    temp = m.row;
                    
                }
                else if (i == 0 && j > 0){
                    temp = MatrixOperasi.sumCol(m, j - 1);
                }
                else if (j == 0 && i > 0){
                    temp = MatrixOperasi.sumCol(m, i - 1);
                }
                else if (i > 0 && j > 0) {
                    temp = MatrixOperasi.sumMultiplyCol(m, i-1, j-1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }

        // Melakukan Eliminasi Gauss
        mTemp = MatrixOperasi.gaussElimination(mTemp);
        double[] m1 = new double [mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, m1); 

        System.out.print("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0){
                result = m1[i];
                if (m1[i] > 0){
                    System.out.printf("%.4f ", m1[i]);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4f ", m1[i]);
                }
            } else if ( i > 0 && i < mTemp.row - 1){
                result = m1[i] * x[i - 1];
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx%d ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx%d ", m1[i], i);
                }
            } else if (i == mTemp.row - 1){
                result = m1[i] * x[i - 1];
                if (m1[i] > 0){
                    System.out.printf("+ %.4fx%d, ", m1[i], i);
                } else {
                    m1[i] *= -1;
                    System.out.printf("- %.4fx%d, ", m1[i], i);
                }
            }
            sum += result;
        }

        System.out.printf("f(xk) = %.4f\n", sum);
    }

    public static void regresiLinearFile (MatrixOperasi m){
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        MatrixOperasi mTemp;
        MatrixOperasi m1;
        double[] m2;
        double[] x;

        // Membuat Matriks dari File yang Diinput
        m1 = new MatrixOperasi(m.row - 1, m.col);
        for(i = 0; i < m.row - 1; i++){
            for(j = 0; j < m.col; j++){
                m1.setElmt(i, j, m.matrix[i][j]);
            }
        }

        m2 = new double[m.col - 1];
        for(i = 0; i < m2.length; i++){ 
            m2[i] = m.getElmt(m.row - 1, i);
        }

        //Membuat matriks baru semacam SPL
        mTemp = new MatrixOperasi(m.col, m.col + 1);

        for(i = 0 ; i < mTemp.row  ; i++){
            for(j = 0 ; j < mTemp.col ; j++){
                if(i == 0 && j == 0){
                    temp = m1.row;
                }
                else if (i == 0 && j > 0){
                    temp = MatrixOperasi.sumCol(m1, j - 1);
                }
                else if (j == 0 && i > 0){
                    temp = MatrixOperasi.sumCol(m1, i - 1);
                }
                else if (i > 0 && j > 0) {
                    temp = MatrixOperasi.sumMultiplyCol(m1, i-1, j-1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }
        
        // Melakukan Eliminasi Gauss
        mTemp = MatrixOperasi.gaussElimination(mTemp);
        x = new double [mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, x); 

        System.out.print("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0){
                result = x[i];
                if (x[i] > 0){
                    System.out.printf("%.4f ", x[i]);
                } else {
                    x[i] *= -1;
                    System.out.printf("- %.4f ", x[i]);
                }
            } else if ( i > 0 && i < mTemp.row - 1){
                result = x[i] * m2[i - 1];
                if (x[i] > 0){
                    System.out.printf("+ %.4fx%d ", x[i], i);
                } else {
                    x[i] *= -1;
                    System.out.printf("- %.4fx%d ", x[i], i);
                }
            } else if (i == mTemp.row - 1){
                result = x[i] * m2[i - 1];
                if (x[i] > 0){
                    System.out.printf("+ %.4fx%d, ", x[i], i);
                } else {
                    x[i] *= -1;
                    System.out.printf("- %.4fx%d, ", x[i], i);
                }
            }
            sum += result;
        }
         System.out.printf("f(xk) = %.4f\n", sum);
    }

    public static void regresiLinearQuadraticKeyboard(MatrixOperasi m){
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        MatrixOperasi mTemp;
        double[] x;
        x = new double[m.col / 2]; 
        System.out.println("Masukkan nilai x yang ingin ditaksir: ");
        for(i = 0; i < x.length; i++){
            x[i] = MatrixInput.input.nextDouble();
        }
        
        // Membuat matriks baru semacam SPL
        mTemp = new MatrixOperasi(2 * m.col - 1, 2 * m.col); // Menampung x, x^2 dan konstanta

        for(i = 0; i < mTemp.row; i++){
            for(j = 0; j < mTemp.col; j++){
                if(i == 0 && j == 0){
                    temp = m.row;
                }
                else if (i == 0 && j > 0){
                    temp = MatrixOperasi.sumCol(m, j - 1);
                }
                else if (j == 0 && i > 0){
                    temp = MatrixOperasi.sumCol(m, i - 1);
                }
                else if (i > 0 && j > 0) {
                    temp = MatrixOperasi.sumMultiplyCol(m, i - 1, j - 1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }
        
        // Melakukan Eliminasi Gauss
        mTemp = MatrixOperasi.gaussElimination(mTemp);
        double[] coefficients = new double[mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, coefficients); 

        System.out.print("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0){
                result = coefficients[i];
                if (coefficients[i] > 0){
                    System.out.printf("%.4f ", coefficients[i]);
                } else {
                    coefficients[i] *= -1;
                    System.out.printf("- %.4f ", coefficients[i]);
                }
            } else if (i > 0 && i < mTemp.row - 1){
                result = coefficients[i] * x[(i - 1) % x.length];
                if (coefficients[i] > 0){
                    System.out.printf("+ %.4fx%d ", coefficients[i], i);
                } else {
                    coefficients[i] *= -1;
                    System.out.printf("- %.4fx%d ", coefficients[i], i);
                }
            } else if (i == mTemp.row - 1){
                result = coefficients[i] * x[(i - 1) % x.length];
                if (coefficients[i] > 0){
                    System.out.printf("+ %.4fx%d^2, ", coefficients[i], i);
                } else {
                    coefficients[i] *= -1;
                    System.out.printf("- %.4fx%d^2, ", coefficients[i], i);
                }
            }
            sum += result;
        }
        System.out.printf("f(xk) = %.4f\n", sum);
    }

    public static void regresiLinearQuadraticFile(MatrixOperasi m) {
        int i, j;
        double temp = 0;
        double result = 0;
        double sum = 0;
        MatrixOperasi mTemp;
        MatrixOperasi m1;
        double[] x;
        double[] coefficients;
    
        // Membuat Matriks dari File yang Diinput
        m1 = new MatrixOperasi(m.row - 1, 2 * m.col - 1); // Menggunakan x dan x^2
        for (i = 0; i < m.row - 1; i++) {
            for (j = 0; j < m.col; j++) {
                m1.setElmt(i, j, m.matrix[i][j]);
                // Menambahkan nilai kuadrat dari x pada kolom berikutnya
                if (j < m.col - 1) {
                    m1.setElmt(i, m.col + j, Math.pow(m.matrix[i][j], 2));
                }
            }
        }
    
        x = new double[m.col - 1];
        for (i = 0; i < x.length; i++) {
            x[i] = m.getElmt(m.row - 1, i);
        }
    
        // Membuat matriks baru semacam SPL
        mTemp = new MatrixOperasi(2 * m.col - 1, 2 * m.col);
    
        for (i = 0; i < mTemp.row; i++) {
            for (j = 0; j < mTemp.col; j++) {
                if (i == 0 && j == 0) {
                    temp = m1.row;
                } else if (i == 0 && j > 0) {
                    temp = MatrixOperasi.sumCol(m1, j - 1);
                } else if (j == 0 && i > 0) {
                    temp = MatrixOperasi.sumCol(m1, i - 1);
                } else if (i > 0 && j > 0) {
                    temp = MatrixOperasi.sumMultiplyCol(m1, i - 1, j - 1);
                }
                mTemp.setElmt(i, j, temp);
            }
        }
    
        // Melakukan Eliminasi Gauss
        mTemp = MatrixOperasi.gaussElimination(mTemp);
        coefficients = new double[mTemp.getRowEff()];
        MatrixOperasi.backSubstitution(mTemp, coefficients);
    
        // Menampilkan fungsi regresi kuadratik
        System.out.print("f(x) = ");
        for (i = 0; i < mTemp.row; i++) {
            if (i == 0) {
                result = coefficients[i];
                if (coefficients[i] > 0) {
                    System.out.printf("%.4f ", coefficients[i]);
                } else {
                    coefficients[i] *= -1;
                    System.out.printf("- %.4f ", coefficients[i]);
                }
            } else if (i > 0 && i < mTemp.row - 1) {
                result = coefficients[i] * x[(i - 1) % x.length];
                if (coefficients[i] > 0) {
                    System.out.printf("+ %.4fx%d ", coefficients[i], i);
                } else {
                    coefficients[i] *= -1;
                    System.out.printf("- %.4fx%d ", coefficients[i], i);
                }
            } else if (i == mTemp.row - 1) {
                result = coefficients[i] * x[(i - 1) % x.length];
                if (coefficients[i] > 0) {
                    System.out.printf("+ %.4fx%d^2, ", coefficients[i], i);
                } else {
                    coefficients[i] *= -1;
                    System.out.printf("- %.4fx%d^2, ", coefficients[i], i);
                }
            }
            sum += result;
        }
        System.out.printf("f(xk) = %.4f\n", sum);       
    }
}

