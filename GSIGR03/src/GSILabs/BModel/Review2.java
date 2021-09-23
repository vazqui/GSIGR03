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
public class Review2 {

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
    public Review2(String nickUsuario, String comentario) {

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
     * @param c String que contine la review a comprobar
     * @return 
     */
    public boolean comprobarReview(String c){
        return comentario.length() <= 500;
    }
}
