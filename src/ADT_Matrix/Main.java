package ADT_Matrix;

import java.util.*;

import Function.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    // Untuk clearing terminal sesudah memasukkan input
    public static void clearConsole() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("windows")) {
                // Jika OS adalah Windows, jalankan perintah yang sesuai
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
                // Jika OS adalah MacOS atau Unix/Linux, jalankan perintah clear
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else {
                // Jika OS tidak dikenali, tidak melakukan apa-apa atau menampilkan pesan
                System.out.println("Fungsi clear console tidak didukung di sistem operasi ini.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        clearConsole();
        Pemanis.sonnyangel();
        Scanner input = new Scanner(System.in);
        int pilihan, pil1, pil2, pil3 = 0, pilsave; 
        System.out.println("------------------------------------------------------------");
        System.out.println("                        Menu Utama                          ");
        System.out.println("------------------------------------------------------------");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linier Berganda");
        System.out.println("7. Interpolasi Gambar");
        System.out.println("8. Keluar");
        System.out.print("Masukkan pilihan Anda: ");
        pilihan = input.nextInt();
        input.nextLine();
        clearConsole();
        System.out.println();

        if (pilihan == 1) {
            Pemanis.spl();
            System.out.println("------------------------------------------------------------");
            System.out.println("               Menu Sistem Persamaan Linier                 ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Metode Eliminasi Gauss");
            System.out.println("2. Metode Eliminasi Gauss-Jordan");
            System.out.println("3. Metode Matriks Balikan");
            System.out.println("4. Kaidah Cramer");
            System.out.print("Pilih metode: ");
            pil1 = input.nextInt();
            input.nextLine();
            clearConsole();
            System.out.println();

            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.println("3. Masukan Matrix Hilbert");
            System.out.print("Pilih metode input: ");
            pil2 = input.nextInt();
            input.nextLine();
            clearConsole();
            System.out.println();
            MatrixOperasi m1 = null;

            if (pil2 == 1) {
                System.out.println("1. Masukan Matrix Biasa");
                System.out.println("2. Masukan Matrix Augmented");
                System.out.print("Pilih jenis input: ");
                pil3 = input.nextInt();
                input.nextLine();
                clearConsole();
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
            } else if (pil2 == 3){
                double[][] hilbert = MatrixInput.hilbertMatrix();
                m1 = new MatrixOperasi(hilbert, hilbert.length, hilbert[0].length);
            }

            String[] res;
            switch (pil1) {
                case 1:
                res = SPL.gaussSPL(m1);
                for (String line : res) {
                    System.out.println(line);
                }
                MatrixOutput.saveFile(res);
                break;
                case 2:
                res = SPL.gaussJordanSPL(m1);
                for (String line : res) {
                    System.out.println(line);
                }
                MatrixOutput.saveFile(res);
                break;
                case 3:
                res = SPL.inversSPL(m1);
                for (String line : res) {
                    System.out.println(line);
                }
                MatrixOutput.saveFile(res);
                break;
                case 4:
                res = SPL.cramerSPL(m1);
                for (String line : res) {
                    System.out.println(line);
                }
                MatrixOutput.saveFile(res);
                break;
                default:
                System.out.println("Pilihan tidak valid.");
                input.close();
                return;
            }
        }

        else if (pilihan == 2) {
            Pemanis.deter();
            System.out.println("------------------------------------------------------------");
            System.out.println("                   Menu Sistem Determinan                   ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Metode Kofaktor");
            System.out.println("2. Metode OBE");
            System.out.print("Pilih metode: ");
            pil1 = input.nextInt();
            input.nextLine();
            clearConsole();
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("                        Menu Input                          ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.print("Pilih metode input: ");
            pil2 = input.nextInt();
            input.nextLine();
            clearConsole();
            System.out.println();
            MatrixOperasi m1 = null;

            if (pil2 == 1) {
                double[][] m = null;
                m = MatrixInput.normalMatrix();

                m1 = new MatrixOperasi(m, m.length, m[0].length);
            } else if (pil2 == 2) {
                m1 = MatrixInput.fileMatrix();
            }

            String[] res;
            switch (pil1) {
                case 1:
                    res = Determinan.handleDeterminantCases(m1, 1);
                    for (String line : res) {
                        System.out.println(line);
                    }
                    // Pilihan save file / tidak
                    MatrixOutput.saveFile(res);
                    break;
                case 2:
                    res = Determinan.handleDeterminantCases(m1, 2);
                    for (String line : res) {
                        System.out.println(line);
                    }
                    // Pilihan save file / tidak
                    MatrixOutput.saveFile(res);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    input.close();
                    return;
            }
        }


        else if (pilihan == 3) {
            Pemanis.inve();
            System.out.println("------------------------------------------------------------");
            System.out.println("                     Menu Sistem Invers                     ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Metode OBE");
            System.out.println("2. Metode Adjoin");
            System.out.print("Pilih metode: ");
            pil1 = input.nextInt();
            input.nextLine();
            clearConsole();
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("                        Menu Input                          ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.print("Pilih metode input: ");
            pil2 = input.nextInt();
            input.nextLine();
            clearConsole();
            System.out.println();
            MatrixOperasi m1 = null;

            if (pil2 == 1) {
                double[][] m = null;
                m = MatrixInput.normalMatrix();
                m1 = new MatrixOperasi(m, m.length, m[0].length);
            } else if (pil2 == 2) {
                m1 = MatrixInput.fileMatrix();
            }

            String res[]; 
            switch (pil1) {
                case 1:
                    res = Invers.handleInversCases(m1, 1);
                    for (String line : res) {
                        System.out.println(line);
                    }
                    // Pilihan save file / tidak
                    MatrixOutput.saveFile(res);
                    break;
                case 2:
                    res = Invers.handleInversCases(m1, 1);
                    for (String line : res) {
                        System.out.println(line);
                    }
                    // Pilihan save file / tidak
                    MatrixOutput.saveFile(res);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    input.close();
                    return;
            }
        }

        else if (pilihan == 4){
            Pemanis.inter();
            System.out.println("------------------------------------------------------------");
            System.out.println("                         Menu Input                         ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Masukan dari Keyboard");
            System.out.println("2. Masukan dari File");
            System.out.print("Pilih metode input: ");
            pil2 = input.nextInt();
            while (pil2 < 1 || pil2 > 2){
                pil2 = input.nextInt();
            }
            String res[];
            if (pil2 == 1){
                double[][]m = MatrixInput.readInterpolasiKeyboard();
                MatrixOperasi m1 = new MatrixOperasi(m, m.length, m[0].length+1);
                System.out.print("Masukkan nilai x yang akan ditaksir: ");
                input.useLocale(Locale.US);
                double xToEstimate = input.nextDouble();
                MatrixOperasi inter = InterpolasiPolinomial.InterPolim(m1, false);
                res = InterpolasiPolinomial.runInterpolasi(inter, xToEstimate);
                for (String line : res) {
                    System.out.println(line);
                }
                // Pilihan save file / tidak
                MatrixOutput.saveFile(res);
            }
            else if (pil2 == 2){
                MatrixOperasi m = MatrixInput.fileMatrix();
                MatrixOperasi inter = InterpolasiPolinomial.InterPolim(m, true);
                res = InterpolasiPolinomial.runInterpolasi(inter, m.getElmt(m.getRowEff()-1, 0));
                for (String line : res) {
                    System.out.println(line);
                }
                // Pilihan save file / tidak
                MatrixOutput.saveFile(res);
            }

        }

        else if (pilihan == 5){
            Pemanis.bicub();
            String [] res;
            MatrixOperasi m = MatrixInput.fileMatrix();
            res = BicubicInterpolation.bicubicInterpolation(m);
            for (String line : res) {
                System.out.println(line);
            }
            // Pilihan save file / tidak
            MatrixOutput.saveFile(res);
        }

        else if (pilihan == 6) {
            Pemanis.regre();
            System.out.println("------------------------------------------------------------");
            System.out.println("                    Menu Sistem Regresi                     ");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Regresi Linier");
            System.out.println("2. Regresi Kuadratik");
            System.out.print("Pilih metode: ");
            pil1 = input.nextInt();
            clearConsole();
            System.out.println();
            if (pil1 == 1) {
                System.out.println("------------------------------------------------------------");
                System.out.println("                        Menu Input                          ");
                System.out.println("------------------------------------------------------------");
                System.out.println("1. Masukan dari Keyboard");
                System.out.println("2. Masukan dari File");
                System.out.print("Pilih metode input: ");
                pil2 = input.nextInt();
                boolean isquad = false;
                String res[];
                if (pil2 == 1) {
                    double[][]m = MatrixInput.regresiMatrix(isquad);
                    MatrixOperasi m1 = new MatrixOperasi(m, m.length, m[0].length);
                    res = Regresi.regresiLinearKeyboard(m1);
                    for (String line : res) {
                        System.out.print(line + " ");
                    }
                    // Pilihan save file / tidak
                    MatrixOutput.saveFile(res);

                }
                else if (pil2 == 2) {
                    MatrixOperasi m = MatrixInput.fileMatrix();
                    res = Regresi.regresiLinearFile(m);
                    for (String line : res) {
                        System.out.print(line + " ");
                    }
                    System.out.println();
                    // Pilihan save file / tidak
                    MatrixOutput.saveFile(res);
                }
            }
            else if (pil1==2) {
                System.out.println("------------------------------------------------------------");
                System.out.println("                        Menu Input                          ");
                System.out.println("------------------------------------------------------------");
                System.out.println("1. Masukan dari Keyboard");
                System.out.println("2. Masukan dari File");
                System.out.print("Pilih metode input: ");
                pil2 = input.nextInt();
                boolean isquad = true;
                String res[];
                if (pil2 == 1) {
                    double[][]m = MatrixInput.regresiMatrix(isquad);
                    MatrixOperasi m1 = new MatrixOperasi(m, m.length, m[0].length);
                    res = Regresi.regresiLinearQuadraticKeyboard(m1);
                    for (String line : res) {
                        System.out.print(line + " ");
                    }
                    System.out.println();
                    // Pilihan save file / tidak
                    MatrixOutput.saveFile(res);
                }
                else if (pil2 == 2) {
                    MatrixOperasi m = MatrixInput.fileMatrix();
                    res = Regresi.regresiLinearQuadraticFile(m);
                    for (String line : res) {
                        System.out.print(line + " ");
                    }
                    System.out.println();
                    // Pilihan save file / tidak
                    MatrixOutput.saveFile(res);
                }
            }
        }

        else if (pilihan == 7){
            Pemanis.gambar();
            Scanner masuk = new Scanner(System.in); 
            System.out.print("Masukkan nama file (.jpg atau .png): ");
            String file = masuk.nextLine();
            clearConsole();
            ImageResize.resizing(file);
            masuk.close();
        }

        else if (pilihan == 8){
            Pemanis.tengkyu();
        }

        else {
            Pemanis.nofitur();
        }
        input.close();
    }
}