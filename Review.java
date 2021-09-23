/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alumno
 */
public class Review {
    final public int[] tipoValoracion = {0, 1, 2, 3, 4, 5};
    public String comentario;
    public String fechaVisita;
    public Local local;
    public Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    int valoracionCliente;
    public Contestacion contestacion;

    public void setContestacion(Contestacion contestacion) {
        this.contestacion = contestacion;
    }

    public Contestacion getContestacion() {
        return contestacion;
    }
    
    public Review(Cliente cliente, Local local, String fechaVisita,int valoracionCliente) throws ParseException{
        this.cliente = cliente;
        this.local = local;
        this.fechaVisita = fechaVisita;
        this.valoracionCliente = valoracionCliente;
    }
    
    public static void main(String[] args) throws ParseException{
        Review review = new Review(new Cliente(), new Local(), "22/12/2020", 5);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        System.out.println("Local : " + review.local);
        System.out.println("fechaVisita : " +  formato.parse(review.fechaVisita));
        System.out.println("comentario : " + review.comentario);
        System.out.println("valoracionCliente : " + review.valoracionCliente);
        
    }
    
    public void setLocal(Local local) {
        this.local = local;
    }

    public Local getLocal() {
        return local;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFechaVisita(String fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public int getValoracion(int i) {
        if (i>= 0 && i<=5 ){
            return tipoValoracion[i];
        }
        else{
            System.out.println("ERROR valoración debe ser entre 0 y 5");
            return -1;
        }
    }

    public String getComentario() {
        return comentario;
    }

    public String getFechaVisita() {
        return fechaVisita;
    }
    public void ComprobarTamañoComentario(String comentario){
        if (comentario.length() >= 500){
            System.out.println("ERROR mas de 500 caracteres...");
        }
    }
    public void ComoprobarContestacion(Review review, Contestacion contestacion){
        if (review.contestacion.equals(null)){
            review.contestacion = contestacion;
        }
        else{
            System.out.println("ERROR ya existe contestación");
        }
    }
    
}
