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
public class Review {

    final public int[] tipoValoracion = {0, 1, 2, 3, 4, 5};
    String nickUsuario; //Nick del usuario que escribe la review 
    String comentario; //Comentario describiendo la opinion del usuario
    Direccion local; //Local al que le esta escribiendo el comentario
    Contestacion respuesta; //Respuesta del propietrio a la review

    /**
     * Constructor
     *
     * @param nickUsuario
     * @param comentario
     */
    public Review(String nickUsuario, String comentario) {

        if (comprobarReview(comentario)) {
            this.nickUsuario = nickUsuario;
            this.comentario = comentario;
        } else {
            System.out.println("No se puede crear la review. Numero maximo de caracteres alcanzado.");
        }
    }

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
     * @param c String que contine la review a comprobar
     * @return
     */
    public boolean comprobarReview(String comentario) {
        if (comentario.length() >= 500) {
            System.out.println("ERROR mas de 500 caracteres...");
            return false;
        }
        return true;
    }

    /**
     * Obtiene la valoracion del local
     *
     * @param i
     * @return
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
     * Comprueba si la contstacion se puede realizar
     * 
     * @param review
     * @param contestacion
     */
    public void ComoprobarContestacion(Review review, Contestacion contestacion) {
        if (review.respuesta.equals(null)) {
            review.respuesta = contestacion;
        } else {
            System.out.println("ERROR ya existe contestación");
        }
    }
}
