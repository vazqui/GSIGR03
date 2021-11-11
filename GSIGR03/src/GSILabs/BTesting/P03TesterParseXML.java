package GSILabs.BTesting;

import GSILabs.BModel.Cliente;
import GSILabs.BModel.Local;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
import static GSILabs.BSystem.BusinessSystem.parseXMLFile;
import java.io.File;

/**
 *
 * @author GR03
 */

/*

Se puede mejorar la representacion siguiendo la misma jerarquia que en la creacion
de XML

Tambien queda arreglar el tipo Cliente, ya que solo almacena como usuario o Propietario

Igual tiene algo que ver con las etiquetas del XML

*/
public class P03TesterParseXML {
    public static void main(String[] args){
        String ruta = "local.xml";
        File f = new File (ruta);
        BusinessSystem system;
        system = parseXMLFile(f);
        System.out.println("LOCALES:");
        for (Local local : system.almacenamiento.locales) {
            System.out.println("\t" + local.nombre + ", " + local.direccion.calle + ", " + local.descripcion);
        }
        
         System.out.println("\n\nPropietarios");
        
        
        for (Usuario u : system.almacenamiento.usuarios) {
            if(u instanceof Propietario){
                System.out.println(u);
            }
        }
         System.out.println("\n\nClientes");
        
        
        for (Usuario u : system.almacenamiento.usuarios) {
            if(!(u instanceof Propietario)){
                System.out.println(u);
            }
        }
       
        
        
        System.out.println();
        System.out.println("\n\nREVIEWS");
        for (Review r : system.almacenamiento.reviews) {
            System.out.println("\t" + r.nickUsuario + ", " + r.local + ", " + r.comentario + ", " + r.fecha + ", " + r.respuesta.respuesta);
        }
        System.out.println("\n\nRESERVAS");
        System.out.println(system.almacenamiento.reservas);
    }
}
