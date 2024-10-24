// package ADT_Matrix;

// import java.util.Scanner;
// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
// import Function.*;

// public class test1 {
//     public static void clearConsole() {
//         String os = System.getProperty("os.name").toLowerCase();
//         try {
//             if (os.contains("windows")) {
//                 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//             } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
//                 new ProcessBuilder("clear").inheritIO().start().waitFor();
//             } else {
//                 System.out.println("Fungsi clear console tidak didukung di sistem operasi ini.");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public static void saveToFile(String content, String fileName, String directory) {
//         try {
//             // Create directory if it doesn't exist
//             File dir = new File(directory);
//             if (!dir.exists()) {
//                 dir.mkdirs();
//             }

//             // Create full file path
//             String fullPath = directory + File.separator + fileName;
            
//             // Create FileWriter
//             FileWriter writer = new FileWriter(fullPath);
//             writer.write(content);
//             writer.close();
            
//             System.out.println("Results have been saved to: " + fullPath);
//         } catch (IOException e) {
//             System.out.println("Error saving file: " + e.getMessage());
//         }
//     }
    
//     public static void main(String[] args) {
//         clearConsole();
//         Scanner input = new Scanner(System.in);
//         Scanner baby = new Scanner(System.in);
//         int pilihan, pil1, pil2, pil3 = 0; 

//         System.out.println("=== Menu Utama ===");
//         System.out.println("1. Sistem Persamaan Linier");
//         System.out.println("2. Determinan");
//         System.out.println("3. Matriks Balikan");
//         System.out.println("4. Interpolasi Polinom");
//         System.out.println("5. Interpolasi Bicubic Spline");
//         System.out.println("6. Regresi Linier Berganda");
//         System.out.println("7. Interpolasi Gambar");
//         System.out.println("8. Keluar");
//         System.out.print("Masukkan pilihan Anda: ");
//         pilihan = input.nextInt();
//         input.nextLine();
//         clearConsole();
//         System.out.println();

//         if (pilihan == 1) {
//             System.out.println("=== Menu Sistem Persamaan Linier ===");
//             System.out.println("1. Metode Eliminasi Gauss");
//             System.out.println("2. Metode Eliminasi Gauss-Jordan");
//             System.out.println("3. Metode Matriks Balikan");
//             System.out.println("4. Kaidah Cramer");
//             System.out.print("Pilih metode: ");
//             pil1 = input.nextInt();
//             input.nextLine();
//             clearConsole();
//             System.out.println();

//             System.out.println("1. Masukan dari Keyboard");
//             System.out.println("2. Masukan dari File");
//             System.out.print("Pilih metode input: ");
//             pil2 = input.nextInt();
//             input.nextLine();
//             clearConsole();
//             System.out.println();
//             MatrixOperasi m1 = null;

//             if (pil2 == 1) {
//                 System.out.println("1. Masukan Matrix Biasa");
//                 System.out.println("2. Masukan Matrix Augmented");
//                 System.out.print("Pilih jenis input: ");
//                 pil3 = input.nextInt();
//                 input.nextLine();
//                 clearConsole();
//                 System.out.println();
                
//                 double[][] m = null;
//                 if (pil3 == 1) {
//                     m = MatrixInput.normalMatrix();
//                 } else if (pil3 == 2) {
//                     m = MatrixInput.augmentedMatrix();
//                 } else {
//                     System.out.println("Pilihan tidak valid.");
//                     input.close();
//                     baby.close();
//                     return;
//                 }

//                 m1 = new MatrixOperasi(m, m.length, m[0].length);
//             } else if (pil2 == 2) {
//                 m1 = MatrixInput.fileMatrix();
//             }

//             StringBuilder resultToFile = new StringBuilder();
//             String result = "";
            
//             try {
//                 switch (pil1) {
//                     case 1:
//                         resultToFile = SPL.gaussSPL(m1);
//                         result = resultToFile.toString();
//                         System.out.println(result);
//                         break;
//                     // case 2:
//                     //     resultToFile = SPL.gaussJordanSPL(m1);
//                     //     result = resultToFile.toString();
//                     //     System.out.println(result);
//                     //     break;
//                     // case 3:
//                     //     resultToFile = SPL.inversSPL(m1);
//                     //     result = resultToFile.toString();
//                     //     System.out.println(result);
//                     //     break;
//                     // case 4:
//                     //     resultToFile = SPL.cramerSPL(m1);
//                     //     result = resultToFile.toString();
//                     //     System.out.println(result);
//                     //     break;
//                     // default:
//                     //     System.out.println("Pilihan tidak valid.");
//                     //     input.close();
//                     //     baby.close();
//                     //     return;
//                 }

//                 System.out.print("Enter the name for the output file (e.g., solutions.txt): ");
//                 String fileName = baby.nextLine();

//                 // Specify the output directory
//                 String directory = "test/output";
                
//                 // Save the results to file
//                 saveToFile(result, fileName, directory);

//             } finally {
//                 input.close();
//                 baby.close();
//             }
//         }
//     }
// }