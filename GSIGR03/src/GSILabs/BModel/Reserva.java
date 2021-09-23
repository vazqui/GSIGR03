package GSILabs.BModel;

/*
 * La Reserva se realiza para un Cliente, pudiendo reservar un Bar o Restaurante
 * Esta clase crea una relacion entre Cliente y Reservable (Bar o Restaurante)
 */

/**
 *
 * @author David Arbea
 */
public class Reserva {
    
    private Cliente cliente;
    
    private Reservable reservable;
    //En reservable tendremos una clase que puede ser Bar o Restaurante

    public Reserva(Cliente cliente, Reservable reservable) {
        this.cliente = cliente;
        this.reservable = reservable;
    }

    //Getters and Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Reservable getReservable() {
        return reservable;
    }

    public void setReservable(Reservable reservable) {
        this.reservable = reservable;
    }
    
    
    
}
