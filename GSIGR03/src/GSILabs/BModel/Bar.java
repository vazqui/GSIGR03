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
 * Clase para el Pub
 * @author GR03
 * @version 1.0
 */
public class Bar extends Local implements Reservable{
    
    //Propiedades del Bar
    
    List<String> tags;

    public Bar(String nombre, String descripcion, String direccion) {
        super(nombre, descripcion, direccion);
        tags = new ArrayList<>();
    }


    
    

    
    
}
