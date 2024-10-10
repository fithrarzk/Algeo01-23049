package ADT_Matrix;
import java.io.*;
import java.util.*;

public class MatrixInput {
    public static double[][] normalMatrix() { //Input Biasa
            Scanner scanner = new Scanner(System.in);
        try{
            System.out.print ("Masukan jumlah baris : ");
            int baris = scanner.nextInt();
            System.out.print ("Masukan jumlah kolom : ");
            int kolom = scanner.nextInt();
            double [][] matrix = new double [baris][kolom];
            System.out.println ("Masukan elemen matriks : ");
            for (int i = 0; i < baris; i++){
                for (int j = 0; j < kolom; j++){
                    matrix[i][j] = scanner.nextDouble();
                }
            }
            return matrix;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;            
        } 
        finally{
            scanner.close();
        }
    }
    public static double [][] hilbertMatrix() { // Input Hilbert
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print ("Masukan jumlah ordo : ");
            int ordo = scanner.nextInt();
            double [][] matrixHilbert = new double [ordo][ordo+1];
            for (int i = 0; i < ordo; i++){
                for (int j = 0; j < ordo+1; j++){
                    matrixHilbert[i][j] = 1/(i+j+1);
                }
            }
            return matrixHilbert;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
        finally {
            scanner.close(); 
        }            
    }
    
    public class ReadMatrix { //input file
        public static double[][] readMatrixFile (String filePath) {
            double [][] matrix = null;
            try {
                File file = new File (filePath);
                Scanner scanner = new Scanner (file);
                int baris = 0;
                int kolom = 0;
                while (scanner.hasNextLine()){
                    String bar = scanner.nextLine();
                    String [] kol = bar.trim().split("\\s+");
                    kolom = kol.length;
                    baris++;
                }
                matrix = new double[baris][kolom];
                scanner.close();
                
                scanner = new Scanner (file);

                //input elemen
                int i = 0;
                while (scanner.hasNextLine()){
                    String bar = scanner.nextLine();
                    String [] kol = bar.trim().split("\\s+");
                    for (int j=0; j<baris;j++){
                        matrix[i][j] = Double.parseDouble(kol[j]);
                    } 
                    i++;
                } 
                scanner.close();
            }
            catch (FileNotFoundException e){
                System.out.println("File Tidak Ditemukan");
                e.printStackTrace();
            }
            return matrix;
        }
    }
}
