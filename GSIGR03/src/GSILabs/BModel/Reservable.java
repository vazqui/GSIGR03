/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */

package GSILabs.BModel;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/*
 * Esta interfaz recoge la creacion de reservas para Bares y Restaurantes
 * 
 * Cada Reserva incluye la fecha y hora en que se va a efectuar
 * Y un posible porcentaje de descuento
 * 
 * @author GR03
 * @version 1.0
 */
public interface Reservable {
    
    public String getNombre();
    
    
}
