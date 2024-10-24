package ADT_Matrix;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;

public class MatrixInput {

    public static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static double[][] normalMatrix() { // Input Matriks Biasa
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
    }
    
    // Memasukkan matriks augmented dimana terdiri dari matriks dan matriks hasil
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
        String path = "test/input/" + file;
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

    public static double[][] readInterpolasiKeyboard(){
        int i, j;
        int n;
        double [][] matrix;

        // Input Derajat Polinom
        System.out.print("Masukkan derajat polinom (n): ");
        n = input.nextInt();

        // Membuat Matriks dengan Titik X dan Y
        matrix = new double[n + 1][2];
        System.out.println("Masukkan titik x dan y: ");
        for(i = 0; i < n + 1; i++){
            for(j = 0; j < 2; j++){
                matrix[i][j] = input.nextDouble(); // Membaca angka dengan titik sebagai pemisah desimal
            }
        }
        MatrixOperasi m = new MatrixOperasi(matrix, n+1, 2);
        // System.out.println("\nini hasilnya\n");
        // MatrixOutput.printMatrix(m);
        
        return matrix;
    }

    public static double[][] regresiMatrix(boolean isQuadratic) {
        int i, j;
        int n, m;
        double[][] matrix;
    
        // Input Jumlah Peubah dan Sampel
        System.out.print("Masukkan jumlah peubah (x): ");
        n = input.nextInt();
        System.out.print("Masukkan jumlah sampel (m): ");
        m = input.nextInt();
    
        // Tentukan ukuran matriks berdasarkan tipe regresi
        if (isQuadratic) {
            matrix = new double[m][2 * n + 1]; // 2 * n untuk x dan x^2, dan 1 untuk y
        } else {
            matrix = new double[m][n + 1]; // n untuk x dan 1 untuk y
        }
    
        // Membuat Matriks dengan Titik X dan Y
        System.out.println("Masukkan titik x dan y: ");
        for (i = 0; i < m; i++) {
            for (j = 0; j < n + 1; j++) {
                matrix[i][j] = input.nextDouble();
    
                // Jika regresi kuadratik, tambahkan nilai kuadrat x
                if (isQuadratic && j < n) {
                    matrix[i][n + j] = Math.pow(matrix[i][j], 2);
                }
            }
        }
        return matrix;
    }

    public static double [][] hilbertMatrix() { // Input Hilbert
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Masukan jumlah ordo : ");
            int ordo = scanner.nextInt();
            double [][] matrixHilbert = new double [ordo][ordo+1];

            // Mengisi matriks Hilbert, kecuali kolom terakhir
            for (int i = 0; i < ordo; i++) {
                for (int j = 0; j < ordo; j++) {
                    matrixHilbert[i][j] = 1.0 / (i + j + 1);
                }
                // Kolom terakhir: [1, 0, 0, 0...]
                matrixHilbert[i][ordo] = (i == 0) ? 1 : 0;
            }
            //MatrixOperasi m = new MatrixOperasi(matrixHilbert, matrixHilbert.length, matrixHilbert[0].length);
            //MatrixOutput.printMatrix(m);
            return matrixHilbert;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}