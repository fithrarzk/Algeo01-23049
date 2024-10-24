package Function;
import java.io.BufferedReader;
import ADT_Matrix.*;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SPL {
    public static String[] gaussSPL(MatrixOperasi matrix) {
        // Use a List to accumulate the output
        List<String> resultToFile = new ArrayList<>();

        // Perform Gauss elimination
        matrix = MatrixOperasi.gaussElimination(matrix);
        double[] x = new double[matrix.getRowEff()];

        // Analyze the matrix after Gauss elimination
        int solutionType = MatrixOperasi.solutionType(matrix);

        // Add results to the List
        resultToFile.add("Hasil Matrix Setelah Gauss Elimination:");
        resultToFile.add(MatrixOutput.matrixToString(matrix));  // Ensure this is formatted with 3 decimal places
        resultToFile.add(""); // Add a blank line

        if (solutionType == 0) {
            resultToFile.add("Solusi tidak ada.");
        } 
        else if (solutionType == 1) {
            MatrixOperasi.backSubstitution(matrix, x);
            resultToFile.add("Solusi tunggal:");
            for (int i = 0; i < matrix.getRowEff(); i++) {
                resultToFile.add(String.format("X[%d] = %.4f", i + 1, x[i]));
            }
        } 
        else {
            resultToFile.add("Solusi banyak (parametrik):");
            resultToFile.add(MatrixOperasi.parametrik(matrix));
        }

        // Convert List to String array and return
        return resultToFile.toArray(new String[0]);
    }
    

    public static String[] gaussJordanSPL(MatrixOperasi Mgaussjordan) {
    // Use a List to accumulate the output
        List<String> resultToFile = new ArrayList<>();

        // Perform Gauss-Jordan elimination
        Mgaussjordan = MatrixOperasi.gaussJordanElimination(Mgaussjordan);

        // Analyze the matrix after Gauss-Jordan elimination
        int solutionType = MatrixOperasi.solutionType(Mgaussjordan);

        // Add results to the List
        resultToFile.add("Hasil Matrix Setelah Gauss Jordan Elimination:");
        resultToFile.add(MatrixOutput.matrixToString(Mgaussjordan));  // Ensure this is formatted with 3 decimal places
        resultToFile.add(""); // Add a blank line

        if (solutionType == 0) {
            resultToFile.add("Solusi tidak ada.");
        } 
        else if (solutionType == 1) {
            resultToFile.add("Solusi tunggal:");
            int n = Mgaussjordan.getColEff();  // The last column contains the constants
            for (int i = 0; i < Mgaussjordan.getRowEff(); i++) {
                resultToFile.add(String.format("X[%d] = %.4f", i + 1, Mgaussjordan.getElmt(i, n - 1)));
            }
        } 
        else {
            // If the solution is parametric, call the parametrik method and add the result
            resultToFile.add("Solusi banyak (parametrik):");
            resultToFile.add(MatrixOperasi.parametrik(Mgaussjordan));  // Assuming this method returns a String
        }

        // Convert List to String array and return
        return resultToFile.toArray(new String[0]);
    }


public static String[] cramerSPL(MatrixOperasi m) {
        MatrixOperasi matMain;
        MatrixOperasi matRes;
        MatrixOperasi temp;

        // Create matMain and matRes from the input matrix m
        matMain = new MatrixOperasi(m.getRowEff(), m.getColEff() - 1);
        matRes = new MatrixOperasi(m.getRowEff(), 1);

        // Fill matMain with the coefficients and matRes with the results
        for (int i = 0; i < m.getRowEff(); i++) {
            for (int j = 0; j < m.getColEff(); j++) {
                if (j != m.getColEff() - 1) {
                    matMain.setElmt(i, j, m.getElmt(i, j));
                } else {
                    matRes.setElmt(i, 0, m.getElmt(i, j));
                }
            }
        }

        // Calculate determinant of matMain
        double det = Determinan.determinanKofaktor(matMain);

        // Prepare the output
        List<String> resultToFile = new ArrayList<>();

        // If determinant is zero or the number of equations does not match the number of variables
        if (det == 0 || (m.getRowEff() != m.getColEff() - 1)) {
            resultToFile.add("SPL tidak dapat diselesaikan dengan metode cramer.");
        } 
        else {
            // Solve using Cramer's rule
            for (int i = 0; i < matMain.getRowEff(); i++) {
                temp = new MatrixOperasi(matMain.matrix, matMain.getRowEff(), matMain.getColEff());

                // Replace the i-th column of temp with the result matrix
                for (int j = 0; j < matMain.getColEff(); j++) {
                    temp.setElmt(j, i, matRes.getElmt(j, 0));
                }

                // Calculate solution for X[i]
                double solution = Determinan.determinanKofaktor(temp) / det;
                resultToFile.add(String.format("X%d = %.4f", i + 1, solution));

                // Restore the original i-th column of temp
                for (int k = 0; k < matMain.getRowEff(); k++) {
                    temp.setElmt(k, i, m.getElmt(k, i));
                }
            }
        }

        // Convert List to String array and return
        return resultToFile.toArray(new String[0]);
    }


    public static String[] inversSPL(MatrixOperasi m) {
    MatrixOperasi matMain, matRes;
    List<String> resultToFile = new ArrayList<>();  // Use a list to accumulate output

    matMain = new MatrixOperasi(m.getRowEff(), m.getColEff() - 1);
    matRes = new MatrixOperasi(m.getRowEff(), 1);

    // Check if the coefficient matrix is square
    if (!MatrixOperasi.isSquare(matMain)) {
        resultToFile.add("Persamaan tidak dapat diselesaikan dengan metode invers SPL karena matrix koefisien bukan matrix persegi sehingga invers tidak dapat ditentukan.");
        return resultToFile.toArray(new String[0]);
    }

    // Fill in the coefficient matrix and result matrix
    for (int i = 0; i < m.getRowEff(); i++) {
        for (int j = 0; j < m.getColEff(); j++) {
            if (j != m.getColEff() - 1) {
                matMain.setElmt(i, j, m.getElmt(i, j));
            } else {
                matRes.setElmt(i, 0, m.getElmt(i, j));
            }
        }
    }

    // Calculate the inverse of the coefficient matrix
    MatrixOperasi matMainInvers = Invers.inversAdjoin(matMain);

    // If the inverse doesn't exist, return an error message
    if (matMainInvers == null) {
        resultToFile.add("SPL tidak dapat diselesaikan dengan matriks balikan karena nilai balikan matriks tidak terdefinisi.");
    } 
    else {
        // Multiply the inverse by the result matrix
        MatrixOperasi result = MatrixOperasi.multiplyMatrix(matMainInvers, matRes);

        // Add each solution to the output
        for (int i = 1; i <= m.getRowEff(); i++) {
            resultToFile.add(String.format("X%d = %.4f", i, result.getElmt(i - 1, 0)));
        }
    }

    // Convert List to String array and return
    return resultToFile.toArray(new String[0]);
    }
}
