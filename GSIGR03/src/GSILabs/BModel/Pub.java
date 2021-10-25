/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;

/**
 * Clase para el Pub
 * @author GR03
 * @version 1.0
 */
public class Pub extends Local implements XMLRepresentable{

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
