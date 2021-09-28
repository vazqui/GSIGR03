/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

/**
 * Clase para el Pub
 * @author GR03
 * @version 1.0
 */
public class Pub extends Local{

    /** Propiedades **/
    
    public String HoraApertura;         //Hora de apertura del Pub
    public String HoraCierre;           //Hora de cierra del Pub
    
    /** Constructor
     * 
     * @param HoraApertura hora de apertura del pub
     * @param HoraCierre hora de cierre del pub
     * @param nombre nombre del pub
     * @param direccion dirección del pub
     */
    
    public Pub(String HoraApertura, String HoraCierre, String nombre, String direccion) {
        super(nombre, direccion);
        this.HoraApertura = HoraApertura;
        this.HoraCierre = HoraCierre;
    }
    
    public String getNombre() {
        return nombre;
    }
   

}
