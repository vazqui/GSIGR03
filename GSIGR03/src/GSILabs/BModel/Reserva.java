package GSILabs.BModel;

import java.util.Calendar;

/*
 * La Reserva se realiza para un Cliente, pudiendo reservar un Bar o Restaurante
 * Esta clase crea una relacion entre Cliente y Reservable (Bar o Restaurante)
 * No se pueden hacer reservas para un local inexistente.
 * No se pueden hacer reservas para un local inexistente, aunque este en la
 * misma direccion que otro existente.
 */

/*
    La Clase Calendar es una clase abstracta con campos como
    YEAR, MONTH, DAY_OF_MONTH y HOUR

    Para mas informacion consultar:
    https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
*/

/**
 *
 * @author David Arbea
 */
public class Reserva{
    
    private Cliente cliente;
    
    private Calendar fecha;
    
    private double descuento;
    
    private Reservable local;
    //En reservable tendremos una clase que puede ser Bar o Restaurante

    public Reserva(Cliente cliente, Calendar fecha, Reservable reservable) {
        this.cliente = cliente;
        this.fecha = fecha;
        
        //No se pueden hacer reservas para un local inexistente.
        //No se pueden hacer reservas para un local inexistente, aunque este en la
        //misma direccion que otro existente.
        this.local = reservable;
        
        this.descuento = 0.00;
        
    }
    
    public Reserva(Cliente cliente, Calendar fecha, Reservable reservable, double descuento) {
        this.cliente = cliente;
        this.fecha = fecha;
        
        //No se pueden hacer reservas para un local inexistente.
        //No se pueden hacer reservas para un local inexistente, aunque este en la
        //misma direccion que otro existente.
        this.local = reservable;
        
        this.descuento = descuento;
        
    }

    //Getters and Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Reservable getReservable() {
        return local;
    }

    public void setReservable(Reservable reservable) {
        this.local = reservable;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    

    
    @Override
    public String toString() {
        return new String("El cliente " + cliente + " tiene una reserva en " + local + " con fecha " + fecha + " ."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
