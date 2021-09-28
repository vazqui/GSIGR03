/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

/**
 * Clase para la Dirección
 * @author GR03
 * @version 1.0
 */
public class Direccion {
    
    /** Propiedades **/
    
    public String localidad;     //Localidad donde se ubica el local
    public String provincia;     //Provincia donde se ubica el local
    public String calle;         //Calle donde se ubica el local 
    public String numero;        //Numero donde se ubica el local
    
    /** Constructor
     * 
     * @param direccion dirección del local
     */
    public Direccion(String direccion) {
        String[] partes = direccion.split(", ");
        localidad = partes[0];
        provincia = partes[1];
        calle = partes[2];
        numero = partes[3];
    }
    
    /** Constructor
     * 
     * @param localidad localidad donde se ubica el local
     * @param provincia provincia donde se ubica el local
     * @param calle calle donde se ubica el local
     * @param numero numero donde se ubica el local
     */
    public Direccion(String localidad, String provincia, String calle, String numero) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.calle = calle;
        this.numero = numero;
    }
    
   
    /** Metodos **/
    
    @Override
    public String toString(){
        return this.localidad + ", " + this.provincia + ", " + this.calle + ", " + this.numero;
    }

}
