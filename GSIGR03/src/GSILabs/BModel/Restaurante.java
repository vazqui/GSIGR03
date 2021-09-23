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
public class Restaurante extends Local{
    
    //Propiedas del restaurante
    
    public String preciomenu;                 //Precio del menu
    public String CapMaxComensalesTotales;    //Capacidad maxima de comensales totales
    public String CapMaxComensalesMesa;       //Capacidad maxima de comensales por mesa

    //Constructor
    public Restaurante(String preciomenu, String CapMaxComensalesTotales, String CapMaxComensalesMesa, String nombre, String descripcion, String direccion) {
        super(nombre, descripcion, direccion);
        this.preciomenu = preciomenu;
        this.CapMaxComensalesTotales = CapMaxComensalesTotales;
        this.CapMaxComensalesMesa = CapMaxComensalesMesa;
    } 
    
}
