package ADT_Matrix;

import java.util.Scanner;
import Function.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan, pil1, pil2, pil3 = 0; 

        System.out.println("=== Menu Utama ===");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linier Berganda");
        System.out.println("7. Keluar");
        System.out.print("Masukkan pilihan Anda: ");
        pilihan = input.nextInt();
        System.out.println();

        if (pilihan == 1) {
            System.out.println("=== Menu Sistem Persamaan Linier ===");
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Eliminasi Gauss-Jordan");
            System.out.println("3. Metode Matriks Balikan");
            System.out.println("4. Kaidah Cramer");
            System.out.print("Pilih metode: ");
            pil1 = input.nextInt();
            System.out.println();

            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.print("Pilih metode input: ");
            pil2 = input.nextInt();
            System.out.println();
            MatrixOperasi m1 = null;

            if (pil2 == 1) {
                System.out.println("1. Masukan Matrix Biasa");
                System.out.println("2. Masukan Matrix Augmented");
                System.out.print("Pilih jenis input: ");
                pil3 = input.nextInt();
                System.out.println();
                
                double[][] m = null;
                if (pil3 == 1) {
                    m = MatrixInput.normalMatrix();
                } else if (pil3 == 2) {
                    m = MatrixInput.augmentedMatrix();
                } else {
                    System.out.println("Pilihan tidak valid.");
                    input.close();
                    return;
                }

                m1 = new MatrixOperasi(m, m.length, m[0].length);
            } else if (pil2 == 2) {
                m1 = MatrixInput.fileMatrix();
            }

            switch (pil1) {
                case 1:
                    SPL.gaussSPL(m1);
                    break;
                case 2:
                    SPL.gaussJordanSPL(m1);
                    break;
                case 3:
                    SPL.inversSPL(m1);
                    break;
                case 4:
                    SPL.cramerSPL(m1);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    input.close();
                    return;
            }

        } 
        

        else if (pilihan == 2) {

            System.out.println("=== Menu Sistem Determinan ===");
            System.out.println("1. Metode Kofaktor");
            System.out.println("2. Metode OBE");
            System.out.print("Pilih metode: ");
            pil1 = input.nextInt();
            System.out.println();

            System.out.println("=== Menu Input ===");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.print("Pilih metode input: ");
            pil2 = input.nextInt();
            System.out.println();
            MatrixOperasi m1 = null;

            if (pil2 == 1) {
                double[][] m = null;
                m = MatrixInput.normalMatrix();

                m1 = new MatrixOperasi(m, m.length, m[0].length);
            } else if (pil2 == 2) {
                m1 = MatrixInput.fileMatrix();
            }

            switch (pil1) {
                case 1:
                    double det1=Determinan.determinanKofaktor(m1);
                    if (Double.isNaN(det1)){
                        System.out.println("Bukan Matrix Persegi Sehingga Nilai Determinan tidak Dapat Ditentukan.");
                    }
                    else{
                        System.out.println("Hasil Determinan: " + Determinan.determinanKofaktor(m1));
                    }
                    break;
                case 2:
                    double det2=Determinan.determinanReduksi(m1);
                    if (Double.isNaN(det2)){
                        System.out.println("Bukan Matrix Persegi Sehingga Nilai Determinan tidak Dapat Ditentukan.");
                    }
                    else{
                        System.out.println("Hasil Determinan: " + Determinan.determinanReduksi(m1));
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    input.close();
                    return;
            }
        }


        else if (pilihan == 3) {

            System.out.println("=== Menu Sistem Invers ===");
            System.out.println("1. Metode OBE");
            System.out.println("2. Metode Adjoin");
            System.out.print("Pilih metode: ");
            pil1 = input.nextInt();
            System.out.println();

            System.out.println("=== Menu Input ===");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.print("Pilih metode input: ");
            pil2 = input.nextInt();
            System.out.println();
            MatrixOperasi m1 = null;

            if (pil2 == 1) {
                double[][] m = null;
                m = MatrixInput.normalMatrix();

                m1 = new MatrixOperasi(m, m.length, m[0].length);
            } else if (pil2 == 2) {
                m1 = MatrixInput.fileMatrix();
            }

            switch (pil1) {
                case 1:
                    Invers.inversOBE(m1);
                    break;
                case 2:
                    MatrixOperasi inv = Invers.inversAdjoin(m1);
                    if (inv==null){
                        System.out.println("Matrix singular, tidak bisa dihitung inversenya.");
                    }
                    else {
                        MatrixOutput.printMatrix(inv);
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    input.close();
                    return;
            }
        }







        else {
            System.out.println("Fitur lain belum tersedia.");
        }
        input.close();
    }
}

