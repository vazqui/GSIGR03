/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para el Propietario
 * @author GR03
 * @version 1.0
 */
public class Propietario extends Usuario implements XMLRepresentable{
    
    /** Propiedades **/
    
    List<Local> locales;            //Lista con los locales que tiene el usuario.

    /** Constructor
     * 
     * @param nick nick del usuario
     * @param password password del usuario
     * @param fechaNacimiento fecha de nacimiento del usuario
     */
    
    public Propietario(String nick, String password, String fechaNacimiento) {
        super(nick, password, fechaNacimiento);
        locales = new ArrayList<>();
    }

    /**
     * Devuelve una lista con los locales que tiene el usuario
     * @return lista de locales
     */
    public List<Local> getLocales() {
        return locales;
    }
    
    /**
     * Añade un local a la lista de locales del usuario
     * @param local local que se quiere añadir
     */
    public void addLocal (Local local){
            locales.add(local);
    }
    
    /**
     * Elimina un local de la lista de locales del usuario
     * @param local local que se quiere eliminar
     */
    public void eliminarLocal (Local local){
        locales.remove(local);
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
