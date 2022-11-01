/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConSerializable;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


/**
 * Crea una aplicación que almacene los datos básicos de un vehículo como la
 * matrícula (string), marca (String), tamaño del depósito (double) y modelo
 * (string) en ese orden. Los datos se irán pidiendo por teclado. Cada vez que
 * se ejecute el programa irá añadiendo los datos al archivo, no se
 * sobrescribirán.
 *
 * @author Morad
 */
public class Principal {

    private static String matricula;
    private static String marca;
    private static Double tamanioDeposito;
    private static String modelo;

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        File f = new File("datosAutomovilSerializable.dat");
        Automovil auto = null;

        int opcion;

        opcion = Menu(sc);

        while (opcion != 4) {
            switch (opcion) {
                case 1:
                   crearFichero(f);
                    break;
                case 2:
                    escribir(f, sc, auto);
                    break;
                case 3:
                     leer(f);
                default:
                    System.out.println("Operacion Incorrecta");
                    break;
            }
            opcion = Menu(sc);
        }
    }

    private static int Menu(Scanner sc) {
        int opcion;
        System.out.println("1)Crear fichero");
        System.out.println("2)Añadir vehiculos");
        System.out.println("3)Mostrar informacion");
        System.out.println("4)Salir");

        opcion = sc.nextInt();

        return opcion;
    }

    private static void escribir(File f, Scanner sc, Automovil auto) throws IOException {

        if(!f.exists())
            crearFichero(f);
        
        FileOutputStream fos = new FileOutputStream(f,true);
        ClaseOutput oos = new ClaseOutput(fos);
        
        System.out.println("Introduce matricula");
        matricula = sc.next();
        System.out.println("Introduce marca");
        marca = sc.next();
        System.out.println("Introduce tamaño del deposito");
        tamanioDeposito = sc.nextDouble();
        System.out.println("Introduce el modelo");
        modelo = sc.next();
        
        auto = new Automovil(matricula, marca, tamanioDeposito, modelo);
        oos.writeObject(auto);
        oos.close();
        fos.close();

    }

    private static void leer(File f) throws FileNotFoundException, IOException, ClassNotFoundException {

        Automovil auto;
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try{
            while(true){
                auto= (Automovil)ois.readObject();
                System.out.println(auto.toString());
            }
            
        }catch(EOFException e){System.out.println("Fin del fichero");}
        
        
        
        
    }

    
    
    private static void crearFichero(File f) throws FileNotFoundException, IOException {

        if(f.exists()){
            System.out.println("El fichero ya esta creado");
        }else{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            System.out.println("fichero creado");
            oos.close();
            fos.close();
            
        }

    }

}
