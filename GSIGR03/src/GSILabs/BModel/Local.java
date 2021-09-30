/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Clase para el Local
 * @author GR03
 * @version 1.0
 */
public class Local {
    
    /** Propiedades **/
    
    public String nombre;                          //Nombre del local
    public String descripcion;                     //Breve descripción del local, no más de 300 carácteres
    
    public Direccion direccion;                    //Dirección del local
    public Set<Propietario> propietarios;          //Lista con los propietarios que tiene el local
    public Set<Review> reviews;                    //Lista con las reviews
    /** Constructor
     * 
     * @param nombre nombre del local
     * @param descripcion descripción del local
     * @param direccion dirección del local
    */
    public Local(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = new Direccion(direccion);
        propietarios = new HashSet<>();
        reviews = new HashSet<>();
    }
    
    /** 
     * Función para comprobar la longitud de la descripción
     * @param descripcion descripción del local
     */
        
    public void ComprobarDescripcion(String descripcion){
        if (descripcion.length() > 300){
            System.out.println("La descripcion es demasiado larga, tiene mas de 300 caracteres");
        }else{
            System.out.println("Descripción guardada");
        }
    }
    
    
    /**
     * Devuelve una lista con los propietarios del local
     * @return propietarios
     */
    public Set<Propietario> getPropietarios() {
        return propietarios;
    }
    
    /**
     * Añade un propietario a la lista de propietarios del local
     * @param p propietario que se va a añadir 
     */
    public boolean addPropietario(Propietario p){
        
        if(propietarios.size() > 2){
            System.out.println("No se puede añadir propietario. Numero maximo ya alcanzado.");
            return false;
        }else{
            propietarios.add(p);
            return true;
        }
    }

    public String getNombre() {
        return nombre;
    }
    
    /**
     * Elimina un propietario de la lista de propietarios del local
     * @param p propietario que se va a eliminar 
     */
    public void eliminarPropietario(Propietario p){
        if(propietarios.isEmpty()){
            System.out.println("No se puede eliminar propietario. La lista esta vacia");
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Local other = (Local) obj;
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        return true;
    }

   
    
    
    
}
