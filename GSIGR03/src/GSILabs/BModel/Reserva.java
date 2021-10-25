/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
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
 * Clase para el local
 * @author GR03
 * @version 1.0
 */
public class Reserva implements XMLRepresentable{
    
    /** Propiedades **/
    
    private Cliente cliente;            //Cliente
    private LocalDate fecha;            //Fecha
    private LocalTime hora;             //Hora
    private double descuento;           //Descuento
    private Reservable local;           //Local
    
    //En reservable tendremos una clase que puede ser Bar o Restaurante
    
    /** Constructor
     * 
     * @param cliente cliente que realiza la reserva
     * @param fecha fecha de la reserva
     * @param hora hora de la reserva
     * @param reservable si se trata de bar o restaurante
     */
    
    public Reserva(Cliente cliente, LocalDate fecha,LocalTime hora, Reservable reservable) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.local = reservable;
        this.descuento = 0.00;
        
    }
    
    /** Constructor
     * 
     * @param cliente cliente que reserva
     * @param fecha fecha de reserva
     * @param hora hora de la reserva
     * @param reservable si es bar o restaurante
     * @param descuento descuento aplicado
     */
    
    public Reserva(Cliente cliente, LocalDate fecha, LocalTime hora, Reservable reservable, double descuento) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.local = reservable; 
        this.descuento = descuento;
        
    }


    /** 
     * Devuelve el cliente
     * @return cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
    
    /**
     * Establece el cliente
     * @param cliente cliente que reserva
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    /** 
     * Devuelve el local reservable
     * @return local
     */
    public Reservable getReservable() {
        return local;
    }
    
    /** 
     * Establece el local reservable
     * @param reservable si es bar o restaurante
     */
    public void setReservable(Reservable reservable) {
        this.local = reservable;
    }
    
    /** 
     * Devuelve la fecha
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }
    
    /** 
     * Establece la fecha
     * @param fecha fecha de reserva
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    /** 
     * Devuelve la hora
     * @return hora
     */
    public LocalTime getHora() {
        return hora;
    }
    
    /** 
     * Establece la hora
     * @param hora hora de reserva
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    /** 
     * Devuelve el descuento
     * @return descuento
     */
    public double getDescuento() {
        return descuento;
    }
    
    /** 
     * Establece el descuento
     * @param descuento descuento establecido
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    
    @Override
    public String toString() {
        return new String("El cliente " + cliente.getNick() + " tiene una reserva en " + local.getNombre() + " con fecha " + fecha + " " + hora + ".");
    }

    @Override
    public String toXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
