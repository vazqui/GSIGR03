/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase para el Cliente
 * @author GR03
 * @version 1.0
 */
public class Cliente extends Usuario implements XMLRepresentable{
    
    /** Propiedades **/
    
    public Set<Review> reviews;     //Reviews escritas por el cliente
    public Set<Reserva> reservas;   //Reservas de los clientes
    
    /** Constructor
     * 
     * @param nick Nick del usuario
     * @param password password del usuario
     * @param fechaNacimiento fecha de nacimiento del usuario
     */
    public Cliente(String nick, String password, String fechaNacimiento) {
        super(nick, password, fechaNacimiento);
        reviews = new HashSet<>();
        reservas = new HashSet<>();
    }

    public String getNick() {
        return nick;
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
