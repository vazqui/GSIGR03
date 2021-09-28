/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

/**
 * Clase para la Contestacion
 * @author GR03
 * @version 1.0
 */
public class Contestacion {
    
    /** Propiedades **/
    
    public Review r;            //Review a la que se está contestando
    public Propietario p;       //Usuario que está respondiendo la review
    public String respuesta;    //La respuesta del dueño del local sobre la review

    /** Constructor
     * @param r review a la que se está contestando
     * @param p propietario que está respondiendo la review
     * @param respuesta respuesta del dueño del local
     **/
    
    public Contestacion(Review r, Propietario p, String respuesta) {
        if (p.locales.contains(r.local)) {    
            this.r = r;
            this.p = p;
            this.respuesta = respuesta;
        } else {
            System.out.println("No se puede crear la contestacion. El local no pertenece al propietario.");
        }
    }

}
