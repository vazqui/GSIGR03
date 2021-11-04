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
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
/**
 * Clase para el Cliente
 *
 * @author GR03
 * @version 1.0
 */
public class Cliente extends Usuario implements XMLRepresentable {

    /**
     * Propiedades *
     */
    @XmlAnyElement
    public Set<Review> reviews;     //Reviews escritas por el cliente
    @XmlAnyElement
    public Set<Reserva> reservas;   //Reservas de los clientes

    /**
     * Constructor
     *
     * @param nick Nick del usuario
     * @param password password del usuario
     * @param fechaNacimiento fecha de nacimiento del usuario
     */
    public Cliente(String nick, String password, String fechaNacimiento) {
        super(nick, password, fechaNacimiento);
        reviews = new HashSet<>();
        reservas = new HashSet<>();
    }

    public Cliente() {
    }
     
    public Cliente(String xmlString) throws XMLParsingException{
        
        super("", "", "");
        
        JAXBContext jaxbContext;
        
        try{
            
            jaxbContext = JAXBContext.newInstance(Cliente.class);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            Cliente u = (Cliente) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
            
            this.nick = u.nick;
            this.password = u.password;
            this.fechaNacimiento = u.fechaNacimiento;
            this.reviews = u.reviews;
            this.reservas = u.reservas;
            
            
        }
        catch(JAXBException e){
            throw new XMLParsingException("Fallo al leer el String");
        }
    }

    public String getNick() {
        return nick;
    }

    @Override
    public String toXML() {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Cliente.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Cliente.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Cliente.class);

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
