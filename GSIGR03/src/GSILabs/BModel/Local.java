/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import GSILabs.persistence.XMLParsingException;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
 *
 * @author GR03
 * @version 1.0
 */
@XmlRootElement
public class Local implements XMLRepresentable {

    /**
     * Propiedades *
     */
    public String nombre;                          //Nombre del local
    public String descripcion;                     //Breve descripción del local, no más de 300 carácteres

    public Direccion direccion;                    //Dirección del local
    public Set<Propietario> propietarios;          //Lista con los propietarios que tiene el local
    public Set<Review> reviews;                    //Lista con las reviews

    /**
     * Constructor
     *
     * @param nombre nombre del local
     * @param direccion dirección del local
     */
    public Local(String nombre, String direccion, String descripcion) {
        this.nombre = nombre;
        String[] partes = direccion.split(", ");
        String localidad = partes[0];
        String provincia = partes[1];
        String calle = partes[2];
        String numero = partes[3];
        this.direccion = new Direccion(localidad, provincia, calle, numero);
        this.descripcion = descripcion;
        propietarios = new HashSet<>();
        reviews = new HashSet<>();
    }

    public Local() {

    }

    public Local(String xmlString) throws XMLParsingException{
        JAXBContext jaxbContext;
        
        try{
            
            jaxbContext = JAXBContext.newInstance(Cliente.class);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            Local local = (Local) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
            
            this.descripcion = local.descripcion;
            this.direccion = local.direccion;
            this.nombre = local.nombre;
            this.propietarios = local.propietarios;
            this.reviews = local.reviews;
            
        }
        catch(JAXBException e){
            throw new XMLParsingException("Fallo al leer el String");
        }
    }

    /**
     * Función para comprobar la longitud de la descripción
     *
     * @param descripcion descripción del local
     */
    public void ComprobarDescripcion(String descripcion) {
        if (descripcion.length() > 300) {
            System.out.println("La descripcion es demasiado larga, tiene mas de 300 caracteres");
        } else {
            System.out.println("Descripción guardada");
        }
    }

    /**
     * Devuelve una lista con los propietarios del local
     *
     * @return propietarios
     */
    public Set<Propietario> getPropietarios() {
        return propietarios;
    }

    /**
     * Añade un propietario a la lista de propietarios del local
     *
     * @param p propietario que se va a añadir
     * @return true
     */
    public boolean addPropietario(Propietario p) {

        if (propietarios.size() > 2) {
            System.out.println("No se puede añadir propietario. Numero maximo ya alcanzado.");
            return false;
        } else {
            propietarios.add(p);
            return true;
        }
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Elimina un propietario de la lista de propietarios del local
     *
     * @param p propietario que se va a eliminar
     */
    public void eliminarPropietario(Propietario p) {
        if (propietarios.isEmpty()) {
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
            return xmlContent;

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveToXML(File f) {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Local.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Store XML to File
            jaxbMarshaller.marshal(this, f);

            return true;

        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveToXML(String filePath) {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Local.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Store XML to File
            File f = new File(filePath);
            jaxbMarshaller.marshal(this, f);

            return true;

        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }

}
