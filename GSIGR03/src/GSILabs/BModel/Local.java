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
 * Clase para el local
 * @author GR03
 * @version 1.0
 */
public class Local {
    
    //Propiedades del local 
    
    public String nombre;        //Nombre del local
    public String descripcion;   //Breve descripcion del local, no mas de 300 caracteres
    
    public Direccion direccion;                     //Direccion del local
    ArrayList<Local> locales = new ArrayList<>();   //Arraylist de locales 
    List<Propietario> propietarios;                 //Lista con los propietarios que tiene el local
    
    //Constructor de la clase Local
    public Local(String nombre, String descripcion, String direccion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        if(ComprobarUbicacion(direccion)){
            this.direccion = new Direccion(direccion);;
            locales.add(this);
            propietarios = new ArrayList<>();
        }
        else{
            System.out.println("En esta direccion ya hay un local.");
        }
    }
    
    //Metodos de la clase Local
    
    
    //Funcion para verificar que no hayan dos locales en la misma direccion
    public boolean ComprobarUbicacion(String d){
        return !locales.contains(d);    
    }
    
    //Funcion para comprobar la longitud de la descripcion
    public void ComprobarDescripcion(String descripcion){
        if (descripcion.length() > 300){
            System.out.println("La descripcion es demasiado larga, tiene mas de 300 caracteres");
        }else{
            System.out.println("Descripción guardada");
        }
    }
    
    
    /**
     * Devuelve una lista con los propietarios del local
     * @return 
     */
    public List<Propietario> getPropietarios() {
        return propietarios;
    }
    
    /**
     * Añade un propietario a la lista de propietarios del local
     * @param p propietario que se va a añadir 
     */
    public void añadirPropietario(Propietario p){
        
        if(propietarios.size() > 2){
            System.out.println("No se puede añadir propietario. Numero maximo ya alcanzado.");
        }else{
            propietarios.add(p);
        }
    }
    
    public void eliminarPropietario(Propietario p){
        if(propietarios.isEmpty()){
            System.out.println("No se puede eliminar propietario. La lista esta vacia");
        }
    }
    
}
