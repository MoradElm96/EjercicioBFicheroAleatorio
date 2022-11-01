/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConSerializable;

import java.io.Serializable;

/**Crea una aplicación que almacene los datos básicos de un vehículo como la
 * matrícula (string), marca (String), tamaño del depósito (double) y modelo
 * (string) en ese orden. Los datos se irán pidiendo por teclado. Cada vez que
 * se ejecute el programa irá añadiendo los datos al archivo, no se
 * sobrescribirán.
 *
 * @author Morad
 */
public class Automovil implements Serializable {
    
    private static final long serialVersionUID = 1L;// como un id
    
    private String matricula;
    private String marca;
    private Double tamanioDeposito;
    private String modelo;

    public Automovil(String matricula, String marca, Double tamanioDeposito, String modelo) {
        this.matricula = matricula;
        this.marca = marca;
        this.tamanioDeposito = tamanioDeposito;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Automovil{" + "matricula=" + matricula + ", marca=" + marca + ", tamanioDeposito=" + tamanioDeposito + ", modelo=" + modelo + '}';
    }

    
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getTamanioDeposito() {
        return tamanioDeposito;
    }

    public void setTamanioDeposito(Double tamanioDeposito) {
        this.tamanioDeposito = tamanioDeposito;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    
    
}
