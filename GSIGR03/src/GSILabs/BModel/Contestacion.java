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
public class Contestacion {

    public Review r; //Review a la que se esta contestando
    public Propietario p; //Usuario que esta respondiendo la review
    public String respuesta; //La respuesta del dueño del local sobre la review

    /**
     * Constructor
     *
     * @param r
     * @param p
     * @param respuesta
     */
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
