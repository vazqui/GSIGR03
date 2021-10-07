/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para el Bar
 * @author GR03
 * @version 1.0
 */
public class Bar extends Local implements Reservable{
    
    /** Propiedades **/
    
    public List<String> tags;        //Especialidades del bar
    
    /** Constructor
     * @param nombre nombre del bar
     * @param direccion dirección del bar
    **/

    public Bar(String nombre,  String direccion) {
        super(nombre, direccion);
        tags = new ArrayList<>();
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getNombre() {
        return nombre;
    }
    
    


    
    

    
    
}
