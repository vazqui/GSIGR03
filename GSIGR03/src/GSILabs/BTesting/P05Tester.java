/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BTesting;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
import GSILabs.BSystem.PublicBusinessSystem;
import GSILabs.persistence.XMLParsingException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author alumno
 */
public class P05Tester {
    public static void main(String[] args) throws XMLParsingException {
        PublicBusinessSystem system = new PublicBusinessSystem();


        Usuario u2 = new Usuario("efseven2", "1234", "2012/12/01");
        system.nuevoUsuario(u2);

        Bar b1 = new Bar("Cañas", "Comunidad Foral de Navarra, Navarra, Calle Perez Goyena, 16", "Bueno bonito barato");
        Bar b2 = new Bar("Cañas2", "Comunidad Foral de Navarra, Navarra, Calle Gomez Goyena, 162", "Bueno bonito barato");
        Bar b3 = new Bar("Cañas3", "Comunidad Foral de Navarra, Navarra, Calle Pepe Goyena, 163", "Bueno bonito barato");
        Bar b4 = new Bar("Cañas4", "Comunidad Foral de Navarra, Navarra, Calle Alma Goyena, 164", "Bueno bonito barato");
        
        system.nuevoLocal(b1);
        system.nuevoLocal(b2);
        system.nuevoLocal(b3);
        system.nuevoLocal(b4);
        
        //puede ser util un constructor de Cliente(Usuario usuario) para conversiones
        Cliente c1 = new Cliente(u2.nick, u2.password, u2.fechaNacimiento);
        
        system.nuevaReserva(c1, b1, LocalDate.now(), LocalTime.NOON);
        system.nuevaReserva(c1, b4, LocalDate.now(), LocalTime.NOON);
//        system.nuevaReserva(c1, b3, LocalDate.now(), LocalTime.NOON);
        system.nuevaReserva(c1, b2, LocalDate.now(), LocalTime.NOON);
       
        Review r2 = new Review(c1.nick, "papopepo", b1);
        r2.setTipoValoracion(2);
        Review r3 = new Review(c1.nick, "papopepo", b3);
        r3.setTipoValoracion(2);
        Review r4 = new Review(c1.nick, "papopepo", b4);
        r4.setTipoValoracion(1);
        
        system.nuevaReview(r2);
//        system.nuevaReview(r3);
        system.nuevaReview(r4);

        System.out.println("\n" + c1.nick + "\n\n");
        
        try{
            System.out.println(system.getRecommendation("efseven2").nombre);
        }
        catch(NullPointerException ex){
            System.out.println("Ningun sitio que recomendar");
        }
        System.out.println("Mejor Bar global: " + system.mejorBar(system.almacenamiento.locales).nombre);

    }
}
