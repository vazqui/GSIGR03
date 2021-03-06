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
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase para el Pub
 * @author GR03
 * @version 1.0
 */
@XmlRootElement
public class Pub extends Local implements XMLRepresentable{

    /** Propiedades **/
    
    public String HoraApertura;         //Hora de apertura del Pub
    public String HoraCierre;           //Hora de cierra del Pub
    
    /** Constructor
     * 
     * @param HoraApertura hora de apertura del pub
     * @param HoraCierre hora de cierre del pub
     * @param nombre nombre del pub
     * @param direccion dirección del pub
     */
    
    public Pub(String HoraApertura, String HoraCierre, String nombre, String direccion, String descripcion) {
        super(nombre, direccion, descripcion);
        this.HoraApertura = HoraApertura;
        this.HoraCierre = HoraCierre;
    }
    
    public Pub(){
    }
    
    public Pub(String xmlString) throws XMLParsingException{
        
        super("", "", "");
        
        JAXBContext jaxbContext;
        
        try{
            
            jaxbContext = JAXBContext.newInstance(Cliente.class);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            Pub pub = (Pub) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
            
            this.HoraApertura = pub.HoraApertura;
            this.HoraCierre = pub.HoraCierre;
            this.descripcion = pub.descripcion;
            this.direccion = pub.direccion;
            this.nombre = pub.nombre;
            this.propietarios = pub.propietarios;
            this.reviews = pub.reviews;
            
        }
        catch(JAXBException e){
            throw new XMLParsingException("Fallo al leer el String");
        }
    }
    
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toXML() {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Bar.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Pub.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Pub.class);

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
