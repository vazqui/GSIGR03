/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BSystem;

import GSILabs.BModel.*;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase para el BusinessData
 * @author GR03
 * @version 1.0
 */
@XmlRootElement
public class BusinessData implements XMLRepresentable{
    
    /** Propiedades **/
    
    public Set<Usuario> usuarios;           //Set de usuarios
    public Set<Local> locales;              //Set de locales
    public Set<Review> reviews;             //Set de reviews
    public Set<Reserva> reservas;           //Set de reservas

    /** Constructor **/
    public BusinessData() {
        this.usuarios = new HashSet<>();
        this.locales = new HashSet<>();
        this.reviews = new HashSet<>();
        this.reservas = new HashSet<>();
    }

    @Override
    public String toXML() {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(BusinessData.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(BusinessData.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(BusinessData.class);

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
