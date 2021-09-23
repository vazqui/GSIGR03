/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Propietario extends Usuario{
    List<Local> locales; //Lista con los locales que tiene el usuario.

    //Cosntructor
    public Propietario(String nick, String password, String fechaNacimiento) {
        super(nick, password, fechaNacimiento);
        locales = new ArrayList<>();
    }

    /**
     * Devuelve una lista con los locales que tiene el usuario
     * @return 
     */
    public List<Local> getLocales() {
        return locales;
    }
    
    /**
     * Añade un local a la lista de locales del usuario
     * @param local local que se quiere añadir
     */
    public void añadirLocal (Local local){
        locales.add(local);
    }
    
    /**
     * Elimina un local de la lista de locales del usuario
     * @param local local que se quiere eliminar
     */
    public void eliminarLocal (Local local){
        locales.remove(local);
    }
    
}
