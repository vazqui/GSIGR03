/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BSystem;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Local;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.connect.AdminGateway;
import GSILabs.connect.ClientGateway;
import java.rmi.RemoteException;
import java.time.LocalDate;

/**
 *
 * @author alumno
 */
public class PublicBusinessSystem extends BusinessSystem implements ClientGateway, AdminGateway  {

    @Override
    public boolean insertaReview(Review r) throws RemoteException {
        try{
            Usuario usuario = obtenerUsuario(r.nickUsuario);
            //Comprobamos que existe el usuario y que no tenga una review en ese local en la misma fecha
            if(existeNick(r.getNickUsuario()) && !existeRewiew(usuario, r.local, LocalDate.parse(r.fecha))){
                almacenamiento.reviews.add(r);
                for (Local l : almacenamiento.locales) {
                    l.equals(r.local);
                    l.reviews.add(r);
                }
                System.out.println("Rewiew añadida correctamente.");
                return true;
            }else{
                System.out.println("No se puede añadir la rewiew.");
                if(existeRewiew(usuario, r.local, LocalDate.parse(r.fecha))){
                    System.out.println("Ya existe una rewiew para esta fecha");
                }
                return false;
            }
        } catch (NullPointerException ex) {
            System.out.println("ERROR al crear la review.");
            return false;
        }
    }

    @Override
    public boolean quitaReview(Review r) throws RemoteException {
        return almacenamiento.reviews.remove(r);
    }

    @Override
    public Bar mejorBar(String ciudad) throws RemoteException {
        Bar maximoBar = null;
        for (Local local: almacenamiento.locales){
            float maximo = 0;
            if (local.getClass().equals(Bar.class) && local.direccion.localidad.equals(ciudad)){
                if (local.reviews.size() != 0){
                    float media = 0;
                    int i = 0;
                    for (Review review: local.reviews){
                        media = media + review.valoracion;
                        i++;
                    }
                    media = media/i;
                    if (media > maximo){
                        maximo = media;
                        maximoBar = (Bar)local;
                    }
                }
            }
        }
        return maximoBar;
    }

    @Override
    public Restaurante[] mejoresRestaurantes(String ciudad, Integer num) throws RemoteException {
        try{
            Restaurante restauranteMaximo[] = new Restaurante[num];
            float mediaValoracion[] = new float[num];
            for (Local local: almacenamiento.locales){
                float maximo = 0;
                if (local.getClass().equals(Restaurante.class) && local.direccion.localidad.equals(ciudad)){
                    if (local.reviews.size() != 0){
                        float media = 0;
                        int i = 0;
                        for (Review review: local.reviews){
                            media = media + review.valoracion;
                            i++;
                        }
                        media = media/i;
                        int j;
                        for (j = 0; j<= restauranteMaximo.length; j++){
                            if (restauranteMaximo[j] == null){
                                restauranteMaximo[j] = (Restaurante)local;
                                mediaValoracion[j] = media;
                                break;
                            } else if (mediaValoracion[j] < media){
                                for (int k = (restauranteMaximo.length - 1); k>= j; k--){
                                    restauranteMaximo[k - 1] = restauranteMaximo[k];
                                    mediaValoracion[k - 1] = mediaValoracion[k];
                                }
                                restauranteMaximo[j] = (Restaurante)local;
                                mediaValoracion[j] = media;
                                break;
                            }
                        }


                    }
                }
            }
            return restauranteMaximo;
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Local getLocal(String name) throws RemoteException {
        for (Local local : almacenamiento.locales) {
            if (local.nombre.equals(name)) {
                System.out.println("Local encontrado.");
                return local;
            }
        }
        System.out.println("Local no encontrado.");
        return null;
    }

    @Override
    public Local[] getLocals(String name) throws RemoteException {
        Local arrayLocal[] = new Local[almacenamiento.locales.size()];
        int i = 0;
        for (Local local : almacenamiento.locales) {
            if (local.nombre.contains(name)) {
                arrayLocal[i] = local;
                i++;
            }
        }
        return arrayLocal;
    }

    @Override
    public Boolean eliminaLocal(Local l) throws RemoteException {
        for (Local local : almacenamiento.locales) {
            if (local.equals(l) && local.nombre.contentEquals(l.nombre)) {
                almacenamiento.locales.remove(l);
                System.out.println("Local eliminado correctamente.");
                return true;
            }
        }
        System.out.println("Local no encontrado, no se ha podido eliminar.");
        return false;
    }

    @Override
    public Boolean eliminaReviewsDeLocal(Local l) throws RemoteException {
        try{
            if (l.reviews.size() >= 1){
                for (Review review : l.reviews) {
                    quitaReview(review);
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteReview(Review r) throws RemoteException {
        return almacenamiento.reviews.remove(r);
    }

    @Override
    public Integer eliminaReviewsDeUsuario(Cliente c) throws RemoteException {
        int contReviewEliminado = 0;
        try{
            if (c.reviews.size() >= 1){
                for (Review review : c.reviews) {
                    quitaReview(review);
                    contReviewEliminado++;
                }
            } else{
                return -1;
            }
        }catch(Exception e){
            return -1;
        }
        return contReviewEliminado;
    }

    @Override
    public Boolean insertaReviewFalsa(Local l, Integer puntuacion) throws RemoteException {
        Review reviewFalsa = new Review ("falso", "falsa", l);
        reviewFalsa.valoracion = puntuacion;
        insertaReview(reviewFalsa);
        return null;
    }
    
}
