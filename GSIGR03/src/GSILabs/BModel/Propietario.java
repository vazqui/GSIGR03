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
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase para el Propietario
 *
 * @author GR03
 * @version 1.0
 */
@XmlRootElement
public class Propietario extends Usuario implements XMLRepresentable {

    /**
     * Propiedades *
     */
    List<Local> locales;            //Lista con los locales que tiene el usuario.

    /**
     * Constructor
     *
     * @param nick nick del usuario
     * @param password password del usuario
     * @param fechaNacimiento fecha de nacimiento del usuario
     */
    public Propietario(String nick, String password, String fechaNacimiento) {
        super(nick, password, fechaNacimiento);
        locales = new ArrayList<>();
    }

    public Propietario(){
        
    }
    
    public Propietario(String xmlString) throws XMLParsingException{
        JAXBContext jaxbContext;
        
        try{
            
            jaxbContext = JAXBContext.newInstance(Cliente.class);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            Propietario prop = (Propietario) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
            
            this.fechaNacimiento = prop.fechaNacimiento;
            this.locales = prop.locales;
            this.nick = prop.nick;
            this.password = prop.password;
            
        }
        catch(JAXBException e){
            throw new XMLParsingException("Fallo al leer el String");
        }
    }
    
    /**
     * Devuelve una lista con los locales que tiene el usuario
     *
     * @return lista de locales
     */
    public List<Local> getLocales() {
        return locales;
    }

    /**
     * Añade un local a la lista de locales del usuario
     *
     * @param local local que se quiere añadir
     */
    public void addLocal(Local local) {
        locales.add(local);
    }

    /**
     * Elimina un local de la lista de locales del usuario
     *
     * @param local local que se quiere eliminar
     */
    public void eliminarLocal(Local local) {
        locales.remove(local);
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
            JAXBContext jaxbContext = JAXBContext.newInstance(Propietario.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Propietario.class);

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
    }}
