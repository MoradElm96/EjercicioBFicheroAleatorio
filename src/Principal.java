
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Crea una aplicación que almacene los datos básicos de un vehículo como la
 * matrícula (string), marca (String), tamaño del depósito (double) y modelo
 * (string) en ese orden. Los datos se irán pidiendo por teclado. Cada vez que
 * se ejecute el programa irá añadiendo los datos al archivo, no se
 * sobrescribirán.
 *
 *
 * @author Morad
 */
public class Principal {

    static String matricula;
    static String marca;
    static Double tamanioDeposito;
    static String modelo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("datosCoches.bin");
        RandomAccessFile raf = null;

        int opcion;

        opcion = Menu(sc);

        while (opcion != 3) {
            switch (opcion) {
                case 1:
                    escribirFichero(raf, f, sc);
                    break;
                case 2:
                    leerFichero(raf, f);
                    break;
                default:
                    System.out.println("Operacion Incorrecta");
                    break;
            }
            opcion = Menu(sc);
        }
    }

    public static int Menu(Scanner sc) {
        int opcion;
        System.out.println("1)Añadir vehiculos");
        System.out.println("2)Mostrar informacion");
        System.out.println("3)Salir");

        opcion = sc.nextInt();

        return opcion;
    }

    public static void escribirFichero(RandomAccessFile raf, File f, Scanner sc) {

        try {
            raf = new RandomAccessFile(f, "rw");

            System.out.println("Introduce Matricula");
            matricula = sc.next();
            System.out.println("Introduce Marca");
            marca = sc.next();
            System.out.println("Introduce tamaño del deposito");
            tamanioDeposito = sc.nextDouble();
            System.out.println("Introduce modelo");
            modelo = sc.next();

          
            try {
                raf.seek(f.length());//para no sobreescribir
                raf.writeUTF(matricula);
                raf.writeUTF(marca);
                raf.writeDouble(tamanioDeposito);
                raf.writeUTF(modelo);
               
                raf.close();
               

            } catch (IOException e) {
                System.out.println("error " + e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println("error no se ha encontrado el fichero" + e.getMessage());
        }

    }

    public static void leerFichero(RandomAccessFile raf, File f) {

        try {

            raf = new RandomAccessFile(f, "r");

            try {
                raf.seek(0);
                
                while (true) {

   
 
                    matricula = raf.readUTF();
                    marca = raf.readUTF();
                    tamanioDeposito = raf.readDouble();
                    modelo = raf.readUTF();
                    
                    System.out.println("Matricula: " + matricula + " Marca: " + marca + " Tamaño del deposito: " + tamanioDeposito + " Modelo: " + modelo);

                   // raf.seek(puntero(raf));
                    
                  
                     
                   
                }

               
            }catch(EOFException e){
                System.out.println("Fin del fichero");
            }
             raf.close();

        }catch (FileNotFoundException e) {
            System.out.println("error no se ha encontrado el fichero" + e.getMessage());
        }catch (IOException e) {
                System.out.println("error " + e.getMessage());

    }
    }
/*
    private static void pointer(RandomAccessFile raf) {

        try {

            System.out.println(raf.getFilePointer());

        } catch (IOException e) {
            System.out.println("error puntero" + e.getMessage());
        }
    }
    
     private static int puntero(RandomAccessFile raf) {

        long puntero = 0;
        try {

            puntero = raf.getFilePointer();
            System.out.println(raf.getFilePointer());

        } catch (IOException e) {
            System.out.println("error puntero" + e.getMessage());
        }
        
        return (int) puntero;
    }
    

    private static void seekPos(RandomAccessFile raf, int pos) {

        try {

            raf.seek(pos);

        } catch (IOException e) {
            System.out.println("error puntero" + e.getMessage());
        }
    }
*/
}
