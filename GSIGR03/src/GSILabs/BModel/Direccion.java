/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

/**
 *
 * @author alumno
 */
public class Direccion {
    
    //Propiedades de la direccion
    
    public String localidad;     //Localidad donde se ubica el local
    public String provincia;     //Provincia donde se ubica el local
    public String calle;         //Calle donde se ubica el local 
    public String numero;        //Numero donde se ubica el local

    public Direccion(String direccion) {
        /*
        Dividir string y guardar los datos
        */
    }
    
    //Constructor para la clase direccion
    public Direccion(String localidad, String provincia, String calle, String numero) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.calle = calle;
        this.numero = numero;
    }
    
    //Metodos de la direccion. Son getters y setters.
    
    //Get localidad del local
    public String getLocalidad() {
        return localidad;
    }



    //Get provincia del local
    public String getProvincia() {
        return provincia;
    }
    


    //Get calle del local
    public String getCalle() {
        return calle;
    }



    //Get numero del local
    public String getNumero() {
        return numero;
    }


}
