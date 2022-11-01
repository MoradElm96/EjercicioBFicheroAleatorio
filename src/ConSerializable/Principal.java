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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("datosAutomovilSerializable.dat");
        Automovil auto = null;

        int opcion;

        opcion = Menu(sc);

        while (opcion != 3) {
            switch (opcion) {
                case 1:
                    escribir(f, sc, auto);
                    break;
                case 2:
                    leer(f, auto);
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

    public static void escribir(File f, Scanner sc, Automovil auto) {

        try {

            System.out.println("Introduce matricula");
            matricula = sc.next();
            System.out.println("Introduce marca");
            marca = sc.next();
            System.out.println("Introduce tamaño del deposito");
            tamanioDeposito = sc.nextDouble();
            System.out.println("Introduce el modelo");
            modelo = sc.next();

            auto = new Automovil(matricula, marca, tamanioDeposito, modelo);

            FileOutputStream fos = new FileOutputStream(f, true);
            //para la cabecera
            ClaseOutput oos = new ClaseOutput(fos);

            oos.writeObject(auto);

            oos.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void leer(File f, Automovil auto) {

        try {

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                while (true) {

                    auto = (Automovil) ois.readObject();
                    System.out.println(auto.toString());

                }

            } catch (EOFException e) {
                System.out.println("Fin Del Fichero");
            }

            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
