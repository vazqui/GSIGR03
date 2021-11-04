/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BTesting;

import GSILabs.BModel.*;
import GSILabs.BSystem.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author fiall
 */
public class P01Tester {

    public static void main(String[] args) {
        BussinessSystem system = new BussinessSystem();

        /*
         * S1
         * Si introduce a un usuario, este puede ser luego localizado a partir de su ID
         */
        System.out.println("S1\n");
        
        Usuario u1 = new Usuario("efseven", "1234", "2001/12/22");
        system.nuevoUsuario(u1);
        
        System.out.println(system.obtenerUsuario("efseven").toString());

        /*
         * S2
         * Si busca a un usuario que no existe con findClient, el resultado es null
         */
        System.out.println("S2\n");
        
        if (system.existeNick("nada")) {
            System.out.println("existe");
        } else {
            System.out.println("no existe");
        }

        /*
         * S3
         * No se pueden introducir dos locales en la misma dirección
         */
        System.out.println("S3\n");
        
        Local l1 = new Local("Cañas", "Comunidad Foral de Navarra, Navarra, Calle Perez Goyena, 16");
        Local l2 = new Local("Brujas", "Comunidad Foral de Navarra, Navarra, Calle Perez Goyena, 16");

        system.nuevoLocal(l1);
        system.nuevoLocal(l2);

        /*
         * S4
         * Si se añade un local, y se elimina posteriormente, se puede introducir un bar en la misma dirección
         */
        System.out.println("S4\n");
        
        system.eliminarLocal(l1);
        system.nuevoLocal(l2);

        /*
         * S5
         * No se puede introducir un usuario menor de edad
         */
        System.out.println("S5\n");
        
        Usuario u2 = new Usuario("efseven2", "1234", "2012/12/01");
        system.nuevoUsuario(u2);

        /*
         * S6-S7
         * No se pueden hacer reservas para un local inexistente
         * No se pueden hacer reservas para un local inexistente, aunque esté en la misma dirección que otro existente
         */
        System.out.println("S6-S7\n");
        
        Cliente c1 = new Cliente("efseven2", "1234", "2001/12/01");
        Bar b1 = new Bar("Cañas", "Comunidad Foral de Navarra, Navarra, Calle Perez Goyena, 16");
        system.nuevaReserva(c1, b1, LocalDate.now(), LocalTime.NOON);
        
        /*
         * S8
         * No se pueden añadir comentarios para Reviews que no existen
         */
        System.out.println("S8\n");
        
        Propietario p1 = new Propietario("pe1", "1234", "10/02/1968");
        system.nuevoUsuario(p1);
        system.asociarLocal(b1, p1);
        Review r1 = new Review("efseven2", "la comida estaba muy mala", b1);
        Contestacion con1 = new Contestacion(r1, p1, "Eso es mentira");
        system.nuevaContestacion(con1, r1);
        
        system.nuevaReview(r1);
        system.nuevaContestacion(con1, r1);
        
        /*
         * S9
         * No se pueden añadir cuatro dueños a un bar
         */
        System.out.println("S9\n");
        
        Propietario p2 = new Propietario("pe2", "1234", "11/02/1968");
        system.nuevoUsuario(p2);
        system.asociarLocal(b1, p2);
        Propietario p3 = new Propietario("pe3", "1234", "12/02/1968");
        system.nuevoUsuario(p3);
        system.asociarLocal(b1, p3);
        Propietario p4 = new Propietario("pe4", "1234", "13/02/1968");
        system.nuevoUsuario(p4);
        system.asociarLocal(b1, p4);
        
        /*
         * S10
         * No se pueden añadir dos reviews del mismo usuario, el mismo día para el mismo local
         */
        System.out.println("S10\n");
        
        Review r2 = new Review("efseven2", "la comida estaba rica", b1);
        system.nuevaReview(r2);
        
        system.saveToXML("local.xml");
    }
}
