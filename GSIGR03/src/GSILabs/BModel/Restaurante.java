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
 * Clase para el restaurante
 *
 * @author GR03
 * @version 1.0
 */
@XmlRootElement
public class Restaurante extends Local implements Reservable, XMLRepresentable {

    /**
     * Propiedades *
     */
    public String preciomenu;                   //Precio del menú
    public String CapMaxComensalesTotales;      //Capacidad máxima de comensales totales
    public String CapMaxComensalesMesa;         //Capacidad máxima de comensales por mesa

    /**
     * Constructor
     *
     * @param preciomenu precio del menú
     * @param CapMaxComensalesTotales capacidad máxima de comensales totales
     * @param CapMaxComensalesMesa capacidad máxima de comensales por mesa
     * @param nombre nombre del restaurante
     * @param direccion dirección del restaurante
     */
    public Restaurante(String preciomenu, String CapMaxComensalesTotales, String CapMaxComensalesMesa, String nombre, String direccion, String descripcion) {
        super(nombre, direccion, descripcion);
        this.preciomenu = preciomenu;
        this.CapMaxComensalesTotales = CapMaxComensalesTotales;
        this.CapMaxComensalesMesa = CapMaxComensalesMesa;
    }

    public Restaurante() {
    }

    public Restaurante(String xmlString) throws XMLParsingException {
        super(" ", " ", " ");

        JAXBContext jaxbContext;

        try {

            jaxbContext = JAXBContext.newInstance(Restaurante.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Restaurante u = (Restaurante) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

            this.nombre = u.nombre;
            this.direccion = u.direccion;
            this.preciomenu = u.preciomenu;
            this.CapMaxComensalesMesa = u.CapMaxComensalesMesa;
            this.CapMaxComensalesTotales = u.CapMaxComensalesTotales;

        } catch (JAXBException e) {
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
            JAXBContext jaxbContext = JAXBContext.newInstance(Restaurante.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Restaurante.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Restaurante.class);

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
