/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BSystem;

import GSILabs.BModel.*;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase para el BusinessData
 * @author GR03
 * @version 1.0
 */
public class BusinessData implements XMLRepresentable{
    
    /** Propiedades **/
    
    public Set<Usuario> usuarios;           //Set de usuarios
    public Set<Local> locales;              //Set de locales
    public Set<Review> reviews;             //Set de reviews
    public Set<Reserva> reservas;           //Set de reservas

    /** Constructor **/
    public BusinessData() {
        this.usuarios = new HashSet<>();
        this.locales = new HashSet<>();
        this.reviews = new HashSet<>();
        this.reservas = new HashSet<>();
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
