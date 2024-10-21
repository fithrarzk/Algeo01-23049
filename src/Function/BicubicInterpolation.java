package Function;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import ADT_Matrix.*;
import Function.*;

public class BicubicInterpolation {
    public static double bicubicVal(double[][] values, double x, double y) {
        double[] arr = new double[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = InterpolasiPolinomial.InterPolimVal(values[i], y);
        }
        return InterpolasiPolinomial.InterPolimVal(arr, x);
    }
    // Membuat Matriks dasar f(x,y)
    public static MatrixOperasi createMatrixF() {
        int row, col;
        MatrixOperasi F = new MatrixOperasi(4, 16); // Matriks 4x16
        int x, y;

        // Mengisi nilai matriks F berdasarkan posisi (x, y)
        for (row = 0; row < 4; row++) {
            if (row == 0) {
                x = 0; y = 0;
            } else if (row == 1) {
                x = 0; y = 1;
            } else if (row == 2) {
                x = 1; y = 0;
            } else {
                x = 1; y = 1;
            }
            int i = 0, j = 0;
            for (col = 0; col < 16; col++) {
                F.setElmt(row, col, Math.pow(x, i) * Math.pow(y, j));
                j++;
                if (j == 4) {
                    j = 0;
                    i++;
                }
            }
        }
        return F;
    }

    // Membuat Matriks untuk fx(x,y)
    public static MatrixOperasi createMatrixFx() {
        int row, col;
        MatrixOperasi Fx = new MatrixOperasi(4, 16); // Matriks 4x16
        int x, y;

        // Mengisi nilai matriks Fx berdasarkan posisi (x, y)
        for (row = 0; row < 4; row++) {
            if (row == 0) {
                x = 0; y = 0;
            } else if (row == 1) {
                x = 1; y = 0;
            } else if (row == 2) {
                x = 0; y = 1;
            } else {
                x = 1; y = 1;
            }
            int i = 0, j = 0;
            for (col = 0; col < 16; col++) {
                if (i == 0) {
                    Fx.setElmt(row, col, 0.0000); // Derivatif pertama fx pada (0,0)
                } else {
                    Fx.setElmt(row, col, Math.pow(x, i - 1) * Math.pow(y, j) * i);
                }
                i++;
                if (i > 3) {
                    i = 0;
                    j++;
                }
            }
        }
        return Fx;
    }

    // Membuat Matriks untuk fy(x,y)
    public static MatrixOperasi createMatrixFy() {
        int row, col;
        MatrixOperasi Fy = new MatrixOperasi(4, 16); // Matriks 4x16
        int x, y;

        // Mengisi nilai matriks Fy berdasarkan posisi (x, y)
        for (row = 0; row < 4; row++) {
            if (row == 0) {
                x = 0; y = 0;
            } else if (row == 1) {
                x = 1; y = 0;
            } else if (row == 2) {
                x = 0; y = 1;
            } else {
                x = 1; y = 1;
            }
            int i = 0, j = 0;
            for (col = 0; col < 16; col++) {
                if (j == 0) {
                    Fy.setElmt(row, col, 0.0000); // Derivatif pertama fy pada (0,0)
                } else {
                    Fy.setElmt(row, col, Math.pow(x, i) * Math.pow(y, j - 1) * j);
                }
                i++;
                if (i > 3) {
                    i = 0;
                    j++;
                }
            }
        }
        return Fy;
    }

    // Membuat Matriks untuk fxy(x,y)
    public static MatrixOperasi createMatrixFxy() {
        int row, col;
        MatrixOperasi Fxy = new MatrixOperasi(4, 16); // Matriks 4x16
        int x, y;

        // Mengisi nilai matriks Fxy berdasarkan posisi (x, y)
        for (row = 0; row < 4; row++) {
            if (row == 0) {
                x = 0; y = 0;
            } else if (row == 1) {
                x = 1; y = 0;
            } else if (row == 2) {
                x = 0; y = 1;
            } else {
                x = 1; y = 1;
            }
            int i = 0, j = 0;
            for (col = 0; col < 16; col++) {
                if (i == 0 || j == 0) {
                    Fxy.setElmt(row, col, 0.0000); // Derivatif kedua pada (0,0)
                } else {
                    Fxy.setElmt(row, col, Math.pow(x, i - 1) * Math.pow(y, j - 1) * j * i);
                }
                i++;
                if (i >= 4) {
                    i = 0;
                    j++;
                }
            }
        }
        return Fxy;
    }

    // Membuat Matriks 16x16 yang berisi f, fx, fy, dan fxy
    public static MatrixOperasi createMatrixX() {
        MatrixOperasi X = new MatrixOperasi(16, 16);
        int i, j;

        // Mengisi nilai matriks X
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {
                if (i >= 0 && i <= 3) {
                    X.setElmt(i, j, createMatrixF().getElmt(i, j));
                } else if (i >= 4 && i <= 7) {
                    X.setElmt(i, j, createMatrixFx().getElmt(i % 4, j));
                } else if (i >= 8 && i <= 11) {
                    X.setElmt(i, j, createMatrixFy().getElmt(i % 4, j));
                } else if (i >= 12 && i <= 15) {
                    X.setElmt(i, j, createMatrixFxy().getElmt(i % 4, j));
                }
            }
        }
        return X;
    }

    // Metode untuk melakukan interpolasi bicubic
    public static void bicubicInterpolation(MatrixOperasi inputMatrix) {
        BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));
        int i, j;
        MatrixOperasi m1;
        MatrixOperasi m2;

        // Menginput matriks 4x4 dari inputMatrix
        m1 = new MatrixOperasi(4, 4);
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                m1.setElmt(i, j, inputMatrix.matrix[i][j]);
            }
        }

        // Menginput nilai a dan b
        m2 = new MatrixOperasi(1, 2);
        for (j = 0; j < 2; j++) {
            m2.setElmt(0, j, inputMatrix.matrix[4][j]);
        }

        // Mengubah bentuk matriks 4x4 menjadi 16x1
        MatrixOperasi tempY = new MatrixOperasi(16, 1);
        int index = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                tempY.setElmt(index, 0, m1.getElmt(i, j));
                index++;
            }
        }

        // Menghitung A = (invers X) * Y
        MatrixOperasi X = createMatrixX();
        MatrixOperasi inversX = Invers.inversIdentitas(X); // Menghitung invers dari matriks X
        MatrixOperasi A = MatrixOperasi.multiplyMatrix(inversX, tempY); // Perkalian matriks

        // Menjumlahkan hasil perkalian A dengan a pangkat i dan b pangkat j
        Double result = 0.0;
        index = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                result += A.getElmt(index, 0) * Math.pow(m2.matrix[0][0], i) * Math.pow(m2.matrix[0][1], j);
                index++;
            }
        }
        // Output hasil interpolasi
        System.out.println("f(" + m2.matrix[0][0] + "," + m2.matrix[0][1] + ") = " + String.format("%.4f", result));
    }
}
