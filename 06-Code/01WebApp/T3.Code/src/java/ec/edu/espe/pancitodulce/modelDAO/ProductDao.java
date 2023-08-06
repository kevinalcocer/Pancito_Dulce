/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.pancitodulce.modelDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author pc
 */
public class ProductDao {
    public float calcularSubTotal(int quantity, float price) {
        float subTotal;
        subTotal = (quantity * price);
        return subTotal;
    }
    public float calculaIva(int quantity, float price) {
        float resutado;
        float IVA = (float) 0.12;
        resutado = (quantity * price) * IVA;
        return resutado;
    }
    public float calculaTotal(int quantity, float price) {
        float total;
        float iva = (float) 1.12;
        total = (quantity * price) * iva;
        return total;
    }
    public String caducidad(String fecha) throws ParseException {
         SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaActual = new Date();
        Date fechaParametroDate = formatoFecha.parse(fecha);
        
        if (fechaParametroDate.compareTo(fechaActual) > 0) {
            return "Vigente";
        } else if (fechaParametroDate.compareTo(fechaActual) < 0) {
            return "Caducado";
        } else {
            return "Hoy Caducado";
            
        }
    }
    
    public double calculaPerdida(double total) {
        double perdida;
        perdida =+ total;
        return perdida;
    }
    
    public double valorCaducados(double total) {
        double perdida;
        perdida =+ total;
        return perdida;
    }
    public double valorVigentes(double total) {
        double vigentes;
        vigentes =+ total;
        return vigentes;
    }
}
