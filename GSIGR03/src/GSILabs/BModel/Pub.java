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

    //Propiedades del Pub
    
    public String HoraApertura;     //Hora de apertura del Pub
    public String HoraCierre;       //Hora de cierra del Pub
    
    //Constructor
    public Pub(String HoraApertura, String HoraCierre, String nombre, String descripcion, String direccion) {
        super(nombre, descripcion, direccion);
        this.HoraApertura = HoraApertura;
        this.HoraCierre = HoraCierre;
    }
   

}
