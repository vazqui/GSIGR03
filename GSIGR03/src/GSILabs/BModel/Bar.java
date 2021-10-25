/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Clase para el Bar
 * @author GR03
 * @version 1.0
 */
public class Bar extends Local implements Reservable,XMLRepresentable {
    
    /** Propiedades **/
    
    public List<String> tags;        //Especialidades del bar
    
    /** Constructor
     * @param nombre nombre del bar
     * @param direccion dirección del bar
    **/
    public Bar(String nombre,  String direccion) {
        super(nombre, direccion);
        tags = new ArrayList<>();
    }
    
    
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    @Override
    public String toXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    
    
}
