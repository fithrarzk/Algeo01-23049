package ADT_Matrix;
//import java.text.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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


    public static void writeStringArrayToFile(String[] content, String fileName) {
        try {
            File outputFile = new File("test/output/" + fileName);
            if (outputFile.createNewFile() || outputFile.exists()) {
                FileWriter writer = new FileWriter(outputFile);
                for (int i = 0; i < content.length; i++) {
                    writer.write(content[i]);
                    if (i != content.length - 1) {
                        writer.write("\n");
                    }
                }
                writer.close();
                System.out.println("File " + fileName + " berhasil dibuat di directory test/output.");
            } else {
                System.out.println("File " + fileName + " gagal dibuat.");
            }
        } catch (IOException e) {
            System.out.println("Error dalam menulis file: " + e.getMessage());
        }
    }

    public static void saveFile(String[] results) {
        int pil;
        Scanner input = new Scanner(System.in);
        System.out.println("Apakah File ingin di save?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        pil = input.nextInt();
        input.nextLine();
        if (pil == 1) {
            System.out.print("Masukkan nama file keluaran: ");
            String fileName = input.nextLine();
            writeStringArrayToFile(results, fileName);
        }
        else {
            System.out.println("Terima Kasih");
        }
        input.close(); // Close the scanner after use
    }
    

    public static String[] matrixToStringArray(MatrixOperasi m) {
        List<String> result = new ArrayList<>();
        int i, j;
        
        // Convert the matrix to a list of strings with formatted elements
        for (i = 0; i < m.getRowEff(); i++) {
            StringBuilder row = new StringBuilder();
            for (j = 0; j < m.getColEff(); j++) {
                row.append(String.format("%.4f ", m.getElmt(i, j)));
            }
            result.add(row.toString().trim());  // Add each row as a separate string
        }
        
        // Convert the list to a String array and return
        return result.toArray(new String[0]);
    }
}