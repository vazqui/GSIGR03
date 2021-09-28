/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

/**
 * Clase para el restaurante
 * @author GR03
 * @version 1.0
 */
public class Restaurante extends Local implements Reservable{
    
    /** Propiedades **/
    
    public String preciomenu;                   //Precio del menú
    public String CapMaxComensalesTotales;      //Capacidad máxima de comensales totales
    public String CapMaxComensalesMesa;         //Capacidad máxima de comensales por mesa

    /** Constructor
     * 
     * @param preciomenu precio del menú
     * @param CapMaxComensalesTotales capacidad máxima de comensales totales
     * @param CapMaxComensalesMesa capacidad máxima de comensales por mesa
     * @param nombre nombre del restaurante
     * @param direccion dirección del restaurante
     */
    public Restaurante(String preciomenu, String CapMaxComensalesTotales, String CapMaxComensalesMesa, String nombre, String direccion) {
        super(nombre, direccion);
        this.preciomenu = preciomenu;
        this.CapMaxComensalesTotales = CapMaxComensalesTotales;
        this.CapMaxComensalesMesa = CapMaxComensalesMesa;
    } 
    
    public String getNombre() {
        return nombre;
    }
}
