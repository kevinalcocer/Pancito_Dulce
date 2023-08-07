/*
 * Click 
nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to 
change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to 
edit this template
 */
package ec.edu.espe.pancitodulce.modelDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Grupo 1 Ingenieria de Software
 * Fecha de creación: 08/01/2023
 * Historia de Modificación
 * Versión  Modificador     Fecha       Cambio                  Razón
 * 1.0      Juan Molina     08/01/2023  Nuevo campo de cálculo  Solicitud
 * 1.1      David Molineros 08/02/2023  Función de valor        Pet. cambio 214
 * 1.2      Chin Adrian     08/03/2023  Agrga funcón Iva        Pet. cambio 256
 * 
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
        SimpleDateFormat formatoFecha = new 
SimpleDateFormat("yyyy-MM-dd");

        Date fechaActual = new Date();
        Date fechaParametroDate = formatoFecha.parse(fecha);

        // Calculamos la diferencia en milisegundos entre la fecha del 
producto y la fecha actual
        long diferencia = fechaParametroDate.getTime() - 
fechaActual.getTime();

        // 3 días en milisegundos (3 días * 24 horas * 60 minutos * 60 
segundos * 1000 milisegundos)
        long tresDiasEnMilisegundos = 3 * 24 * 60 * 60 * 1000;

        if (diferencia > 0) {
            // Si la diferencia es positiva, el producto está vigente
            return "Vigente";
        } else if (diferencia < 0) {
            // Si la diferencia es negativa, el producto está caducado
            return "Caducado";
        } else if (diferencia <= tresDiasEnMilisegundos) {
            // Si la diferencia es igual o menor a tres días, el producto 
está próximo a caducar
            return "Próximo a caducar";
        } else {
            // Si la diferencia es cero, el producto caduca hoy
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
