/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Clase para el Local
 * @author GR03
 * @version 1.0
 */
@XmlRootElement
public class Local implements XMLRepresentable{
    
    /** Propiedades **/
    
    public String nombre;                          //Nombre del local
    public String descripcion;                     //Breve descripción del local, no más de 300 carácteres
    
    public Direccion direccion;                    //Dirección del local
    public Set<Propietario> propietarios;          //Lista con los propietarios que tiene el local
    public Set<Review> reviews;                    //Lista con las reviews
    /** Constructor
     * 
     * @param nombre nombre del local
     * @param direccion dirección del local
    */
    public Local(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = new Direccion(direccion);
        propietarios = new HashSet<>();
        reviews = new HashSet<>();
    }
    
    public Local(){
        
    }
    
    public Local(String XMLfile) throws ParserConfigurationException, SAXException{
        Document document;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(XMLfile);
            document.getDocumentElement().normalize();
            
            NodeList lista_locales = document.getElementsByTagName("local");
            int i = 0;
            while (i < lista_locales.getLength()){
                Node node_local = lista_locales.item(i);
                if (node_local.getNodeType() == Node.ELEMENT_NODE) {
                     Element element = (Element) node_local;
                     this.nombre = element.getAttribute("nombre");
                     this.descripcion = element.getAttribute("descripcion");
                  
                    i++;
                }
                
            }
            System.out.println(nombre + descripcion);
            
        } catch (IOException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
   
    /** 
     * Función para comprobar la longitud de la descripción
     * @param descripcion descripción del local
     */
        
    public void ComprobarDescripcion(String descripcion){
        if (descripcion.length() > 300){
            System.out.println("La descripcion es demasiado larga, tiene mas de 300 caracteres");
        }else{
            System.out.println("Descripción guardada");
        }
    }
    
    
    /**
     * Devuelve una lista con los propietarios del local
     * @return propietarios
     */
    public Set<Propietario> getPropietarios() {
        return propietarios;
    }
    
    /**
     * Añade un propietario a la lista de propietarios del local
     * @param p propietario que se va a añadir 
     * @return  true
     */
    public boolean addPropietario(Propietario p){
        
        if(propietarios.size() > 2){
            System.out.println("No se puede añadir propietario. Numero maximo ya alcanzado.");
            return false;
        }else{
            propietarios.add(p);
            return true;
        }
    }

    public String getNombre() {
        return nombre;
    }
    
    /**
     * Elimina un propietario de la lista de propietarios del local
     * @param p propietario que se va a eliminar 
     */
    public void eliminarPropietario(Propietario p){
        if(propietarios.isEmpty()){
            System.out.println("No se puede eliminar propietario. La lista esta vacia");
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Local other = (Local) obj;
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toXML() {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Local.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(this, sw);

            //Verify XML Content
            String xmlContent = sw.toString();
            System.out.println(xmlContent);
            return xmlContent;

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
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
