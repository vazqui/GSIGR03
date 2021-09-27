/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BSystem;

import GSILabs.BModel.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

/**
 *
 * @author alumno
 */
public class BussinessSystem implements LeisureOffice {

    public BusinessData almacenamiento;

    public BussinessSystem() {
        this.almacenamiento = new BusinessData();
    }

    //**USUARIOS**//
    @Override
    public boolean nuevoUsuario(Usuario u) {
        if(existeNick(u.nick)){
            System.out.println("No se puede añadir el usario. El nick ya existe.");
            return false;
        } else {
            almacenamiento.usuarios.add(u);
            System.out.println("Usuario " + u.nick + " añadido correctamente.");
            return true;
        }
    }

    @Override
    public boolean eliminaUsuario(Usuario u) {
        return almacenamiento.usuarios.remove(u);
    }

    @Override
    public boolean modificaUsuario(Usuario u, Usuario nuevoU) {
        if (almacenamiento.usuarios.remove(u)) {
            return nuevoUsuario(u);
        } else {
            return false;
        }
    }

    @Override
    public boolean existeNick(String nick) {
        if (almacenamiento.usuarios.stream().anyMatch((u) -> (nick.contentEquals(u.nick)))) {
            return true;
        }
        return false;
    }

    @Override
    public Usuario obtenerUsuario(String nick) {
        for (Usuario u : almacenamiento.usuarios) {
            if (nick.contentEquals(u.nick)) {
                return u;
            }
        }
        return null;
    }

    //**REVIEWS**//
    /**
     * Incorpora una nueva review al sistema, en caso de que sus datos (Usuario,
     * Local) sean correctos y no haya otra introducida para la misma fecha.
     *
     * @param r La review a introducir al sistema.
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean nuevaReview(Review r) {
        if(true){   
            return true;
        }else{
            return false;
        }
    }

    public boolean eliminaReview(Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //**CONTESTACIONES**//
    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean tieneContestacion(Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contestacion obtenerContestacion(Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminaContestacion(Contestacion c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminaContestacion(Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //**LOCALES**//
    @Override
    public boolean nuevoLocal(Local l) {
        //Funcion para verificar que no hayan dos locales en la misma direccion
//        if(ComprobarUbicacion(this)){
//            this.direccion = new Direccion(direccion);
//            propietarios = new HashSet<>();
//            System.out.println("no hay ningun local en esta direccion");
//        }
//        else{
//            System.out.println("En esta direccion ya hay un local.");
//        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarLocal(Local l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Local obtenerLocal(Direccion d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean asociarLocal(Local l, Propietario p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarLocal(Local viejoL, Local nuevoL) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //**Locales**//
    @Override
    public Review[] verReviews(Local l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Reserva[] obtenerReservas(Cliente c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva[] obtenerReservas(Reservable r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Reserva[] obtenerReservas(LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarReserva(Reserva r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Local[] listarLocales(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bar[] listarBares(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pub[] listarPubs(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
