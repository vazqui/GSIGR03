package GSILabs.BTesting;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author GR03
 */
public class P03TesterCreateXML {
    public static void main(String[] args) {
        BusinessSystem system = new BusinessSystem();

        /* Bares */
        Bar b1 = new Bar("Cañas", "Comunidad Foral de Navarra, Navarra, Calle Perez Goyena, 16", "Muy malo todo");
        b1.tags.add("calamares");
        b1.tags.add("tarta");
        b1.tags.add("chuleton");
        system.nuevoLocal(b1);
        Bar b2 = new Bar("Erreleku", "Comunidad Foral de Navarra, Navarra, Cordovilla, 45", "Muy malo todo");
        b2.tags.add("ensalada");
        b2.tags.add("ventresca con aceitillo");
        b2.tags.add("butifarra");
        system.nuevoLocal(b2);
        Bar b3 = new Bar("Aintzane", "Comunidad Foral de Navarra, Navarra, Barañain, 2", "Muy malo todo");
        b3.tags.add("fabada");
        system.nuevoLocal(b3);
        Bar b4 = new Bar("Goñi", "Comunidad Foral de Navarra, Navarra, Beriain, 32", "Muy malo todo");
        b4.tags.add("caravinero fresco del cantabrico");
        b4.tags.add("pulpo gallego con patatas panaderas");
        b4.tags.add("confit de conejo con romero y tomillo");
        b4.tags.add("croquetas");
        b4.tags.add("caracoles");
        system.nuevoLocal(b4);

        /* Restaurantes */
        Restaurante r1 = new Restaurante("18", "50", "5", "Goiko", "Comunidad Foral de Navarra, Navarra, Paseo Sarasate, 1", "Muy malo todo");
        system.nuevoLocal(r1);
        Restaurante r2 = new Restaurante("13", "20", "5", "Bodeguita", "Comunidad Foral de Navarra, Navarra, Calle mayor, 89", "Muy malo todo");
        system.nuevoLocal(r2);
        Restaurante r3 = new Restaurante("20", "100", "10", "Asador mutiloa", "Comunidad Foral de Navarra, Navarra, Zizur, 26", "Muy malo todo");
        system.nuevoLocal(r3);

        /* Pubs */
        Pub p1 = new Pub("20:00", "05:00", "Kaixo", "Comunidad Foral de Navarra, Navarra, Calle almendra, 3", "Muy malo todo");
        system.nuevoLocal(p1);
        Pub p2 = new Pub("15:00", "24:00", "Irish", "Comunidad Foral de Navarra, Navarra, Esquiroz, 54", "Muy malo todo");
        system.nuevoLocal(p2);
        Pub p3 = new Pub("24:00", "07:00", "Canalla", "Comunidad Foral de Navarra, Navarra, Calle extintor, 45", "Muy malo todo");
        system.nuevoLocal(p3);
        Pub p4 = new Pub("17:00", "22:00", "La Esquina", "Comunidad Foral de Navarra, Navarra, PLaza del castillo, 69", "Muy malo todo");
        system.nuevoLocal(p4);

        /* Propietarios */
        Propietario d1 = new Propietario("Daniel", "1234", "24/05/1980");
        system.nuevoUsuario(d1);
        Propietario d2 = new Propietario("ivan", "Almendras123", "21/07/1999");
        system.nuevoUsuario(d2);
        Propietario d3 = new Propietario("Alex", "extintor", "20/01/2003");
        system.nuevoUsuario(d3);
        Propietario d4 = new Propietario("Oscar", "si", "08/2/1991");
        system.nuevoUsuario(d4);
        Propietario d5 = new Propietario("Luis", "no", "01/01/1988");
        system.nuevoUsuario(d5);
        Propietario d6 = new Propietario("Miguel", "contraseña", "12/12/2000");
        system.nuevoUsuario(d6);

        
        /*Usuarios normales*/
        Cliente c1 = new Cliente("IbanRuizDeGalarreta", "1234", "27/07/1928");
        system.nuevoUsuario(c1);
        Cliente c2 = new Cliente("anfetik", "1234", "27/07/1950");
        system.nuevoUsuario(c2);
        Cliente c3 = new Cliente("luis", "12345", "27/08/1928");
        system.nuevoUsuario(c3);
        Cliente c4 = new Cliente("pedro", "12347", "02/07/1928");
        system.nuevoUsuario(c4);
        
        
        
        /* Asociación propietarios con locales*/
        system.asociarLocal(b1, d1);
        system.asociarLocal(b2, d2);
        system.asociarLocal(r1, d2);
        system.asociarLocal(p1, d2);
        system.asociarLocal(r2, d3);
        system.asociarLocal(r3, d3);
        system.asociarLocal(p2, d4);
        system.asociarLocal(p3, d4);
        system.asociarLocal(p4, d4);
        system.asociarLocal(b3, d5);
        system.asociarLocal(b4, d6);
        system.asociarLocal(b1, d6);
        system.asociarLocal(p2, d6);
        system.asociarLocal(p4, d6);
        system.asociarLocal(r3, d6);
        system.asociarLocal(b4, d6);
        
        
        /*Reviews*/
        
        Review rr1 = new Review("luis", "comida muy rica", r1);
        system.nuevaReview(rr1);
        Review rr2 = new Review("anfetik", "comida muy rica2", r2);
        system.nuevaReview(rr2);
        Review rr3 = new Review("pedro", "comida muy rica3", r3);
        system.nuevaReview(rr3);
        
        /*Contestacion*/
        
        Contestacion c11 = new Contestacion(rr1, d2, "gracias");
        system.nuevaContestacion(c11, rr1);
         Contestacion c12 = new Contestacion(rr2, d3, "gracias2");
        system.nuevaContestacion(c12, rr2);
         Contestacion c13 = new Contestacion(rr3, d3, "gracias3");
        system.nuevaContestacion(c13, rr3);
        
        /*Reservas*/
        
        system.nuevaReserva(c1, r1, LocalDate.now(), LocalTime.NOON); 
        system.nuevaReserva(c2, b1, LocalDate.now(), LocalTime.NOON); 
        system.nuevaReserva(c3, r2, LocalDate.now(), LocalTime.NOON); 
        system.nuevaReserva(c4, b3, LocalDate.now(), LocalTime.NOON);
        
        System.out.println(r1.toXML());
        system.saveToXML("local.xml");
    }
}
