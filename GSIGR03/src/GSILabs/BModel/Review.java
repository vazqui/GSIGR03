/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import java.time.LocalDate;

/**
 * Clase para la Review
 * @author GR03
 * @version 1.0
 */
public class Review {
    
    /** Propiedades **/
    
    final public int[] tipoValoracion = {0, 1, 2, 3, 4, 5};     //Valoración del local
    public String nickUsuario;                                         //Nick del usuario que escribe la review 
    public String comentario;                                          //Comentario describiendo la opinión del usuario
    public Local local;//Convertir esta direccion a un Local       //Local al que se le está escribiendo el comentario
    public Contestacion respuesta;                                     //Respuesta del propietrio a la review
    public LocalDate fecha; 

    /**
     * Constructor
     *
     * @param nickUsuario nick del usuario que realiza la review
     * @param comentario comentario describiendo la opinión del usuario
     */
    public Review(String nickUsuario, String comentario, Local local) {                      //Local al que se le deja la review??

        if (comprobarReview(comentario)) {
            this.nickUsuario = nickUsuario;
            this.comentario = comentario;
            this.fecha = LocalDate.now();
            this.local = local;
        } else {
            System.out.println("No se puede crear la review. Numero maximo de caracteres alcanzado.");
        }
    }
    
    /**
     * Función para añadir una contestación a la review
     * @param c La contestación publicada
     */
    public void añadirContestacion(Contestacion c) {

        if (respuesta == null) {
            respuesta = c;
        } else {
            System.out.println("Ya existe respuesta del propietario a esta review.");
        }
    }

    /**
     * Comprueba el tamaño de la review
     *
     * @param comentario comentario escrito por el cliente
     * @return True si y solo sí se ha completado la accion
     */
    public boolean comprobarReview(String comentario) {
        if (comentario.length() >= 500) {
            System.out.println("ERROR mas de 500 caracteres...");
            return false;
        }
        return true;
    }
    
    /**
     * Obtiene el nick del usuario que hace la review
     * @return Devuelve el nick del usuario
     */
    public String getNickUsuario() {
        return nickUsuario;
    }
    
    /**
     * Obtiene el nombre del local del al que se le hace la review
     * @return Devuelve el nombre del local
     */
    public Local getLocal() {
        return local;
    }

    /**
     * Obtiene la valoración del local
     *
     * @param i valoración del local 
     * @return la valoracion
     */
    public int getValoracion(int i) {
        if (i >= 0 && i <= 5) {
            return tipoValoracion[i];
        } else {
            System.out.println("ERROR valoración debe ser entre 0 y 5");
            return -1;
        }
    }

    /**
     * Comprueba si la contestación se puede realizar
     * 
     * @param review review que ha sido comentada
     * @param contestacion contestación a realizar
     */
    public void ComoprobarContestacion(Review review, Contestacion contestacion) {
        if (review.respuesta.equals(null)) {
            review.respuesta = contestacion;
        } else {
            System.out.println("ERROR ya existe contestación");
        }
    }
}
