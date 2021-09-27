/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BSystem;

import GSILabs.BModel.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author alumno
 */
public class BusinessData {
    
    public Set<Usuario> usuarios;
    public Set<Local> locales;
    public Set<Review> reviews;
    public Set<Reserva> reservas;

    public BusinessData() {
        this.usuarios = new HashSet<>();
        this.locales = new HashSet<>();
        this.reviews = new HashSet<>();
        this.reservas = new HashSet<>();
    }
        
}
