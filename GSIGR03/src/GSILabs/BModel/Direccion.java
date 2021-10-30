/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.StringWriter;
import java.util.Objects;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase para la Dirección
 * @author GR03
 * @version 1.0
 */

@XmlRootElement

public class Direccion implements XMLRepresentable{
    
    /** Propiedades **/
    
    public String localidad;     //Localidad donde se ubica el local
    public String provincia;     //Provincia donde se ubica el local
    public String calle;         //Calle donde se ubica el local 
    public String numero;        //Numero donde se ubica el local
    
    /** Constructor
     * 
     * @param direccion dirección del local
     */
    public Direccion(String direccion) {
        String[] partes = direccion.split(", ");
        localidad = partes[0];
        provincia = partes[1];
        calle = partes[2];
        numero = partes[3];
    }
    
    /** Constructor
     * 
     * @param localidad localidad donde se ubica el local
     * @param provincia provincia donde se ubica el local
     * @param calle calle donde se ubica el local
     * @param numero numero donde se ubica el local
     */
    public Direccion(String localidad, String provincia, String calle, String numero) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.calle = calle;
        this.numero = numero;
    }

    public Direccion(){
        
    }
    
    
    @Override
    public int hashCode(){
        int hash = 7;
        return hash;
    }

    /** Metodos **/
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
        final Direccion other = (Direccion) obj;
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        if (!Objects.equals(this.provincia, other.provincia)) {
            return false;
        }
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.localidad + ", " + this.provincia + ", " + this.calle + ", " + this.numero;
    }

    @Override
    public String toXML() {
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Direccion.class);
             
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
            System.out.println( xmlContent );
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
