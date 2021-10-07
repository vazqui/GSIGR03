/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Misc;

import GSILabs.BModel.*;
import GSILabs.BSystem.BussinessSystem;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author alumno
 */
public class SSTest04 {

    public static void main(String[] args) throws IOException {
        BussinessSystem system = introduccionDatosEjemplo();
        
        TableModel baresModel = obtenerLocales(system, "Bar");
        TableModel restaurantesModel = obtenerLocales(system, "Restaurante");
        TableModel pubsModel = obtenerLocales(system, "Pub");
        
        
        SpreadSheet sheet = SpreadSheet.createEmpty(baresModel);
        sheet.getSheet(0).setName("Bares");
        sheet.addSheet("Restaurantes").merge(restaurantesModel,0,0);
        sheet.addSheet("Pubs").merge(pubsModel,0,0);
        
        final File file = new File("Locales.ods");
        sheet.saveAs(file);
        OOUtils.open(file);
    }

    /* 
     * Obtiene todos los locales segun su tipo de local
     * Salida: tableModel con los locales
     */
    private static TableModel obtenerLocales(BussinessSystem system,String tipo){
        Set<Local> locales = system.almacenamiento.locales;
        Object[][] matrizBar = new String[locales.size()][];
        int i = 0;
        for (Local local : locales) {
            if (local.getClass().getName().contentEquals("GSILabs.BModel."+tipo)) {
                String[] aux = {local.nombre, local.direccion.provincia, local.direccion.localidad, local.direccion.calle, local.propietarios.toString()};
                matrizBar[i] = aux;
                i++;
            }
        }
        String[] columns = new String[]{"Nombre", "Provincia", "Localidad", "Calle", "Propietarios"};
        TableModel si = new DefaultTableModel(); 
        
        return new DefaultTableModel(matrizBar, columns);
    }
    
    /* 
     * Inserción de datos
     * Inserta datos de ejemplo dentro del sistem
     */
    private static BussinessSystem introduccionDatosEjemplo() {
        /* Instancia de BussinessSystem*/
        BussinessSystem system = new BussinessSystem();

        /* Bares */
        Bar b1 = new Bar("Cañas", "Comunidad Foral de Navarra, Navarra, Calle Perez Goyena, 16");
        system.nuevoLocal(b1);
        Bar b2 = new Bar("Erreleku", "Comunidad Foral de Navarra, Navarra, Cordovilla, 45");
        system.nuevoLocal(b2);
        Bar b3 = new Bar("Aintzane", "Comunidad Foral de Navarra, Navarra, Barañain, 2");
        system.nuevoLocal(b3);
        Bar b4 = new Bar("Goñi", "Comunidad Foral de Navarra, Navarra, Beriain, 32");
        system.nuevoLocal(b4);

        /* Restaurantes */
        Restaurante r1 = new Restaurante("18", "50", "5", "Goiko", "Comunidad Foral de Navarra, Navarra, Paseo Sarasate, 1");
        system.nuevoLocal(r1);
        Restaurante r2 = new Restaurante("13", "20", "5", "Bodeguita", "Comunidad Foral de Navarra, Navarra, Calle mayor, 89");
        system.nuevoLocal(r2);
        Restaurante r3 = new Restaurante("20", "100", "10", "Asador mutiloa", "Comunidad Foral de Navarra, Navarra, Zizur, 26");
        system.nuevoLocal(r3);

        /* Pubs */
        Pub p1 = new Pub("20:00", "05:00", "Kaixo", "Comunidad Foral de Navarra, Navarra, Calle almendra, 3");
        system.nuevoLocal(p1);
        Pub p2 = new Pub("15:00", "24:00", "Irish", "Comunidad Foral de Navarra, Navarra, Esquiroz, 54");
        system.nuevoLocal(p2);
        Pub p3 = new Pub("24:00", "07:00", "Canalla", "Comunidad Foral de Navarra, Navarra, Calle extintor, 45");
        system.nuevoLocal(p3);
        Pub p4 = new Pub("17:00", "22:00", "La Esquina", "Comunidad Foral de Navarra, Navarra, PLaza del castillo, 69");
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
        return system;
    }
}
