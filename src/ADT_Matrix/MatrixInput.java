package ADT_Matrix;
//import java.util.*;
import java.io.*;
//import java.nio.file.Paths;
import java.util.Scanner;

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
    

    public static double[][] augmentedMatrix(){
        Scanner scanner = new Scanner(System.in);
        int i,j;
        double [][] matrix1, matrix2, matrix;
        int baris, kolom;

        // Input Jumlah Baris dan Kolom
        System.out.print("Masukkan jumlah baris: ");
        baris = scanner.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        kolom = scanner.nextInt();
        
        // Membuat Matriks 1
        matrix1 = new double[baris][kolom];
        int expectedElements = baris * kolom;
        System.out.println("Masukkan " + expectedElements + " elemen matriks utama: ");
        for(i = 0; i < baris; i++){
            for(j = 0; j < kolom; j++){
                matrix1[i][j] = scanner.nextDouble();
            }
        }

        // Membuat Matriks 2 dengan ukuran nx1
        matrix2 = new double[baris][1];
        System.out.println("Masukkan elemen matriks hasil: ");
        for(i = 0; i < baris; i++){
            for(j = 0; j < 1; j++){
                matrix2 [i][j] = scanner.nextDouble();
            }
        }
        matrix = new double [baris][kolom + 1];
        for (i = 0; i < baris; i++){
            System.arraycopy(matrix1[i], 0, matrix[i], 0, kolom);
            System.arraycopy(matrix2[i], 0, matrix[i], kolom, 1);
        }
        return matrix;
    }



    public static MatrixOperasi fileMatrix(){
        int i;
        MatrixOperasi matrix;
        // Input Nama File
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama file: ");
        String file = input.nextLine();
        String path = "test/Input/" + file;
        System.out.println(path);

        // Mencari File
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String s;
            String[] x;
            double[] y;
            double[][] mTemp;

            // Membaca Baris 1
            s = br.readLine();
            
            // Mengubah Baris 1 Menjadi Array of String
            x = s.split("\\s+");
            y = new double[x.length];

            // Mengubah Array menjadi Array of Double
            for(i = 0; i < y.length; i++){
                y[i] = Double.parseDouble(x[i]);
            }

            // Memindahkan Array of Double ke Matriks
            mTemp = new double[1][x.length];
            for(i = 0; i < y.length; i++){
                mTemp[0][i] = y[i];
            }
            // Membuat matriks 
            matrix = new MatrixOperasi(mTemp, 1, y.length);

            while((s = br.readLine()) != null){
                x = s.split("\\s+");
                y = new double [x.length];
                for(i = 0; i < x.length; i++){
                    y[i] = Double.parseDouble(x[i]);
                }
                if(x.length < matrix.col){
                    double[] z = new double[matrix.col];
                    for(i = 0; i < matrix.col;i++){
                        if(i >= y.length){
                            z[i] = 0;
                        }else{
                            z[i] = y[i];
                        }
                    }
                    matrix = MatrixOperasi.addRow(matrix, z);
                }else{
                    matrix = MatrixOperasi.addRow(matrix, y);
                }
            }
            br.close();
            return matrix;
            
        // Ketika File Tidak Ditemukan
        }catch(Exception ex){
            System.out.println("File not found");
            System.out.println("Returning a matrix with no value");
            matrix = new MatrixOperasi(1, 1);
            return matrix;
        }
    }
    // public class ReadMatrix { //input file
    //     public static double[][] readMatrixFile (String filePath) {
    //         double [][] matrix = null;
    //         try {
    //             File file = new File (filePath);
    //             Scanner scanner = new Scanner (file);
    //             int baris = 0;
    //             int kolom = 0;
    //             while (scanner.hasNextLine()){
    //                 String bar = scanner.nextLine();
    //                 String [] kol = bar.trim().split("\\s+");
    //                 kolom = kol.length;
    //                 baris++;
    //             }
    //             matrix = new double[baris][kolom];
    //             scanner.close();
                
    //             scanner = new Scanner (file);

    //             //input elemen
    //             int i = 0;
    //             while (scanner.hasNextLine()){
    //                 String bar = scanner.nextLine();
    //                 String [] kol = bar.trim().split("\\s+");
    //                 for (int j=0; j<baris;j++){
    //                     matrix[i][j] = Double.parseDouble(kol[j]);
    //                 } 
    //                 i++;
    //             } 
    //             scanner.close();
    //         }
    //         catch (FileNotFoundException e){
    //             System.out.println("File Tidak Ditemukan");
    //             e.printStackTrace();
    //         }
    //         return matrix;
    //     }
    // }
}


