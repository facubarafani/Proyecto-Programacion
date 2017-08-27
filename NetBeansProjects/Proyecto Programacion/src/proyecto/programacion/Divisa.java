/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.programacion;

/**
 *
 * @author facundo
 */
public class Divisa {
    private double peso;
    private double dolar;

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getDolar() {
        return dolar;
    }

    public void setDolar(double dolar) {
        this.dolar = dolar;
    }
    public Divisa() {
    }
    @Override
    public String toString() {
        return "Divisa{" + "peso=" + peso + ", dolar=" + dolar + '}';
    }
    
}
