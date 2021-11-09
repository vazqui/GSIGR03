/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BTesting;

import GSILabs.BModel.Local;
import GSILabs.BModel.Review;
import GSILabs.BSystem.BusinessSystem;
import static GSILabs.BSystem.BusinessSystem.parseXMLFile;
import java.io.File;

/**
 *
 * @author alumno
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
        System.out.println("\n\nUSUARIOS");
       
        System.out.println(system.almacenamiento.usuarios);
        System.out.println("\n\nREVIEWS");
        for (Review r : system.almacenamiento.reviews) {
            System.out.println("\t" + r.nickUsuario + ", " + r.local + ", " + r.comentario + ", " + r.fecha + ", " + r.respuesta.respuesta);
        }
        System.out.println("\n\nRESERVAS");
        System.out.println(system.almacenamiento.reservas);
    }
}
