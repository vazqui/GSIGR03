/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.Misc;



import GSILabs.BModel.*;
import GSILabs.BSystem.BusinessSystem;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * @author GR03
 * @version 1.0
 */
public class SSTest04 {

    public static void main(String[] args) throws IOException {
        BusinessSystem system = introduccionDatosEjemplo();
        
        //Crea la tabla con los datos del array
        TableModel baresModel = obtenerLocales(system, "Bar");
        TableModel restaurantesModel = obtenerLocales(system, "Restaurante");
        TableModel pubsModel = obtenerLocales(system, "Pub");
        
        //Creamos el sheet
        SpreadSheet sheet = SpreadSheet.create(1,1,1);
        sheet.getSheet(0).setName("Bares");
        sheet.getSheet(0).merge(baresModel, 0, 0);
        sheet.addSheet("Restaurantes").merge(restaurantesModel,0,0);
        sheet.addSheet("Pubs").merge(pubsModel,0,0);
        
        //Guarda los datos en el fichero "test01.ods"
        final File file = new File("Locales.ods");
        sheet.saveAs(file);
        
        //Abre el fichero en el programa de OpenOffice
        OOUtils.open(file);
    }

    /* 
     * Obtiene todos los locales segun su tipo de local
     * Salida: tableModel con los locales
     */
    private static TableModel obtenerLocales(BusinessSystem system,String tipo){
        Set<Local> locales = system.almacenamiento.locales;
        Object[][] matrizBar = new String[locales.size()][99];
        int i = 0;
        int jmax = 0;
        for (Local local : locales) {
            if (local.getClass().getName().contentEquals("GSILabs.BModel."+tipo)) {
                List<String> aux = new ArrayList<>();
                aux.add(local.nombre);
                aux.add(local.direccion.calle + " " + local.direccion.numero);
                aux.add(local.direccion.localidad);
                aux.add(local.direccion.provincia);
                aux.add(local.propietarios.toString());
                if ("Bar".equals(tipo)){
                    Bar bar = (Bar) local;
                    bar.tags.forEach((tag) -> {
                        aux.add(tag);
                    });
                }
                int j = 0;
                
                for(String nombre : aux){
                    matrizBar[i][j] = nombre;
                    j++;
                }
                if (jmax < j){
                    jmax = j;
                }
                i++;
               
            }
        }
        String[] columns =  new String[jmax];
        columns[0] = "Nombre";
        columns[1] = "Calle";
        columns[2] = "Localidad";
        columns[3] = "Provincia";
        columns[4] = "Propietarios";
        
        return new DefaultTableModel(matrizBar, columns);
    }
    
    /* 
     * Inserción de datos
     * Inserta datos de ejemplo dentro del sistem
     */
    private static BusinessSystem introduccionDatosEjemplo() {
        /* Instancia de BussinessSystem*/
        BusinessSystem system = new BusinessSystem();

        /* Bares */
        Bar b1 = new Bar("Cañas", "Comunidad Foral de Navarra, Navarra, Calle Perez Goyena, 16");
        b1.tags.add("calamares");
        b1.tags.add("tarta");
        b1.tags.add("chuleton");
        system.nuevoLocal(b1);
        Bar b2 = new Bar("Erreleku", "Comunidad Foral de Navarra, Navarra, Cordovilla, 45");
        b2.tags.add("ensalada");
        b2.tags.add("ventresca con aceitillo");
        b2.tags.add("butifarra");
        system.nuevoLocal(b2);
        Bar b3 = new Bar("Aintzane", "Comunidad Foral de Navarra, Navarra, Barañain, 2");
        b3.tags.add("fabada");
        system.nuevoLocal(b3);
        Bar b4 = new Bar("Goñi", "Comunidad Foral de Navarra, Navarra, Beriain, 32");
        b4.tags.add("caravinero fresco del cantabrico");
        b4.tags.add("pulpo gallego con patatas panaderas");
        b4.tags.add("confit de conejo con romero y tomillo");
        b4.tags.add("croquetas");
        b4.tags.add("caracoles");
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
