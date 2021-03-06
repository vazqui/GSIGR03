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
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;


@XmlRootElement
/**
 * Clase para el Usuario
 * @author GR03
 * @version 1.0
 */
public class Usuario implements XMLRepresentable, Serializable{
    
    /** Propiedades **/
    
    public String nick;                 //Nick del usuario
    public String password;             //Contraseña del usuario
    public String fechaNacimiento;      //Fecha de nacimiento del usuario

    /** Constructor
     * 
     * @param nick nick del usuario
     * @param password password del usuario
     * @param fechaNacimiento fecha de nacimiento del usuario
     */
    public Usuario(String nick, String password, String fechaNacimiento) {
        this.nick = nick;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        
    }
    
    public Usuario(){       
    }
    
    /** Constructor
     * 
     * @param xmlString String xml con la informacion del objeto a instanciar
     * @throws XMLParsingException 
     */
    public Usuario(String xmlString) throws XMLParsingException{
        JAXBContext jaxbContext;
        
        try{
            
            jaxbContext = JAXBContext.newInstance(Usuario.class);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            Usuario u = (Usuario) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
            
            this.nick = u.nick;
            this.password = u.password;
            this.fechaNacimiento = u.fechaNacimiento;
            
        }
        catch(JAXBException e){
            throw new XMLParsingException("Fallo al leer el String");
        }
    }

    /**
     * Comprueba que la longitud del nick sea menor que 3
     */
    public void comprobarLongitudNick() {
        if (nick.length() < 3) {
            System.out.println("ERROR el nombre debe tener minimo 3 caracteres");
        }
    }

    /**
     * Comprueba que los usuarios sean mayores de 14 años
     * @param fecha fecha de nacimiento del usuario
     */
    public static void comprobarEdadUsuarios(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            int edad = (int) ((TimeUnit.DAYS.convert(((new Date()).getTime() - sdf.parse(fecha).getTime()), TimeUnit.MILLISECONDS)) / 365);
            if (edad < 14) {
                System.out.println("ERROR el usuario debe tener mínimo 14 años");
            }
        } catch (ParseException e) {
            System.out.println("ERROR la fecha introducida no es correcta");
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nick, other.nick)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  nick;
    }

    
    public String toXML() {
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Usuario.class);
             
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            //Print XML String to Console
            StringWriter sw = new StringWriter();
             
            //Write XML to StringWriter
            jaxbMarshaller.marshal(this , sw);
             
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
            JAXBContext jaxbContext = JAXBContext.newInstance(Usuario.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Usuario.class);

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
