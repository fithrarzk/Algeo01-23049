package ADT_Matrix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Call normalMatrix to get the matrix from user input
        double[][] m = normalMatrix(scanner);
        if (m == null) {
            System.out.println("Matriks tidak valid. Program dihentikan.");
            scanner.close();
            return; // Exit if the matrix input is invalid
        }

        // Initialize matrix object
        MatrixOperasi matrix = new MatrixOperasi(m, m.length, m[0].length);

        // Call gaussSPL method and accumulate the result in StringBuilder
        StringBuilder resultToFile = gaussSPL(matrix);

        // Consume any leftover newline
        scanner.nextLine();

        // Display the result before asking for the file name
        System.out.println(resultToFile.toString());

        // Ask the user for the output file name after the operation is done
        System.out.print("Enter the name for the output file (e.g., solutions.txt): ");
        String fileName = "SPL.txt";

        // Specify the output directory (output/test)
        String directory = "test/output";
        // Ensure that the directory exists or create it
        new File(directory).mkdirs();

        // Save the accumulated results to the file inside the output/test directory
        saveToFile(resultToFile.toString(), directory + "/" + fileName);

        scanner.close();
    }

    public static StringBuilder gaussSPL(MatrixOperasi matrix) {
        // Use StringBuilder to accumulate the output
        StringBuilder resultToFile = new StringBuilder();

        // Perform Gauss elimination
        matrix = MatrixOperasi.gaussElimination(matrix);
        double x[] = new double[matrix.getRowEff()];
        // Analyze the matrix after Gauss elimination
        int solutionType = MatrixOperasi.solutionType(matrix);

        resultToFile.append("Hasil Matrix Setelah Gauss Elimination:\n");
        resultToFile.append(MatrixOutput.matrixToString(matrix));  // Ensure this is formatted with 3 decimal places
        resultToFile.append("\n");

        if (solutionType == 0) {
            resultToFile.append("Solusi tidak ada.\n");
        } else if (solutionType == 1) {
            MatrixOperasi.backSubstitution(matrix, x);
            resultToFile.append("Solusi tunggal:\n");
            for (int i = 0; i < matrix.getRowEff(); i++) {
                resultToFile.append(String.format("X[%d] = %.4f%n", i + 1, x[i]));
            }
        } else {
            resultToFile.append("Solusi banyak (parametrik): \n");
            resultToFile.append(parametrik(matrix));
        }

        return resultToFile;
    }

    public static String parametrik(MatrixOperasi m) {
        StringBuilder result = new StringBuilder();
        String[] solusi = new String[m.getColEff() - 1];
        String[] par = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int i, j, a, b;
        int x = 0;

        for (j = 0; j < m.getColEff() - 1; j++) {
            if (MatrixOperasi.KolomNol(m, j)) {
                solusi[x] = par[x];
                result.append("x").append(j + 1).append(" = ").append(par[x]).append("\n");
                x++;
            } else {
                for (i = m.getRowEff() - 1; i >= 0; i--) {
                    if (m.getElmt(i, j) != 0) {
                        for (b = 0; b < m.getColEff() - 1; b++) {
                            if (m.getElmt(i, b) != 0) {
                                break;
                            }
                        }
                        if (j != b) {
                            solusi[x] = par[x];
                            result.append("x").append(j + 1).append(" = ").append(par[x]).append("\n");
                            x++;
                        } else {
                            if (m.getElmt(i, m.getColEff() - 1) != 0) {
                                result.append(String.format("%.3f", m.getElmt(i, m.getColEff() - 1)));
                            }
                            for (a = b + 1; a < m.getColEff() - 1; a++) {
                                if (m.getElmt(i, a) > 0) {
                                    result.append(" - ").append(String.format("%.3f", m.getElmt(i, a))).append("x").append(a + 1);
                                } else if (m.getElmt(i, a) < 0 && m.getElmt(i, m.getColEff() - 1) != 0) {
                                    result.append(" + ").append(String.format("%.3f", (-1 * m.getElmt(i, a)))).append("x").append(a + 1);
                                } else if (m.getElmt(i, a) < 0 && m.getElmt(i, m.getColEff() - 1) == 0) {
                                    result.append("").append(String.format("%.3f", (-1 * m.getElmt(i, a)))).append("x").append(a + 1);
                                }
                            }
                            result.append("\n");
                            x++;
                        }
                        break;
                    }
                }
            }
        }
        return result.toString();
    }

    public static void saveToFile(String content, String fileName) {
        try {
            FileWriter writer = new FileWriter(new File(fileName));
            writer.write(content);
            writer.close();
            System.out.println("Content successfully written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double[][] normalMatrix(Scanner scanner) { // Input Biasa
        try {
            System.out.print("Masukan jumlah baris: ");
            int baris = scanner.nextInt();
            System.out.print("Masukan jumlah kolom: ");
            int kolom = scanner.nextInt();

            // Create the matrix
            double[][] matrix = new double[baris][kolom];

            System.out.println("Masukan elemen matriks: ");
            for (int i = 0; i < baris; i++) {
                for (int j = 0; j < kolom; j++) {
                    System.out.printf("Elemen [%d][%d]: ", i, j);
                    matrix[i][j] = scanner.nextDouble();
                }
            }
            return matrix;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan dalam input. Silakan coba lagi.");
            e.printStackTrace();
            return null;
        }
    }
}
