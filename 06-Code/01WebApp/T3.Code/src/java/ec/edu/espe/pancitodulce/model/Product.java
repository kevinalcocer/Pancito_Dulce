/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.pancitodulce.model;

/**
 *
 * @author pc
 */
public class Product {
    //private int id;
    private String nombre;
    private int cantidad;
    private String medida;
    private float valor;
    private String fechaCaducidad;
    private String factura;

    public Product(String nombre, int cantidad, String medida, float valor, String fechaCaducidad, String factura) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.medida = medida;
        this.valor = valor;
        this.fechaCaducidad = fechaCaducidad;
        this.factura = factura;
    }

    
    
    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

   
    
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Product() {
        
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
