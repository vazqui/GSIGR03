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
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase para la Contestacion
 *
 * @author GR03
 * @version 1.0
 */
@XmlRootElement

public class Contestacion implements XMLRepresentable {

    /**
     * Propiedades *
     */
    @XmlTransient
    public Review r;            //Review a la que se está contestando
    public Propietario p;       //Usuario que está respondiendo la review
    public String respuesta;    //La respuesta del dueño del local sobre la review

    /**
     * Constructor
     *
     * @param r review a la que se está contestando
     * @param p propietario que está respondiendo la review
     * @param respuesta respuesta del dueño del local
     *
     */
    public Contestacion(Review r, Propietario p, String respuesta) {
        if (p.locales.contains(r.local)) {
            this.r = r;
            this.p = p;
            this.respuesta = respuesta;
        } else {
            System.out.println("No se puede crear la contestacion. El local no pertenece al propietario.");
        }
    }

    public Contestacion() {

    }
    
    public Contestacion(String xmlString) throws XMLParsingException{
        
        
        JAXBContext jaxbContext;
        
        try{
            
            jaxbContext = JAXBContext.newInstance(Bar.class);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            Contestacion contestacion = (Contestacion)jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
           
            if (contestacion.p.locales.contains(contestacion.r.local)) {
                this.p = contestacion.p;
                this.r = contestacion.r;
                this.respuesta = contestacion.respuesta;
            } else {
                System.out.println("No se puede crear la contestacion. El local no pertenece al propietario.");
            }
            
            
        }
        catch(JAXBException e){
            throw new XMLParsingException("Fallo al leer el String");
        }
        
    }

    @Override
    public String toXML() {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Contestacion.class);

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
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Contestacion.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Contestacion.class);

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
