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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/*
 * La Reserva se realiza para un Cliente, pudiendo reservar un Bar o Restaurante
 * Esta clase crea una relacion entre Cliente y Reservable (Bar o Restaurante)
 * No se pueden hacer reservas para un local inexistente.
 * No se pueden hacer reservas para un local inexistente, aunque este en la
 * misma direccion que otro existente.
 */

 /*
    La Clase Calendar es una clase abstracta con campos como
    YEAR, MONTH, DAY_OF_MONTH y HOUR

    Para mas informacion consultar:
    https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 */
/**
 * Clase para el local
 *
 * @author GR03
 * @version 1.0
 */
@XmlRootElement
//    @XmlJavaTypeAdapter(AnyTypeAdapter.class)

public class Reserva implements XMLRepresentable {

    /**
     * Propiedades *
     */
    @XmlAnyElement
    public Cliente cliente;            //Cliente
    public String fecha;            //Fecha
    public String hora;             //Hora
    public double descuento;           //Descuento
    @XmlElement(type = Local.class)
    public Reservable local;           //Local

    //En reservable tendremos una clase que puede ser Bar o Restaurante
    /**
     * Constructor
     *
     * @param cliente cliente que realiza la reserva
     * @param fecha fecha de la reserva
     * @param hora hora de la reserva
     * @param reservable si se trata de bar o restaurante
     */
    public Reserva(Cliente cliente, LocalDate fecha, LocalTime hora, Reservable reservable) {
        this.cliente = cliente;
        this.fecha = fecha.toString();
        this.hora = hora.toString();
        this.local = reservable;
        this.descuento = 0.00;

    }

    /**
     * Constructor
     *
     * @param cliente cliente que reserva
     * @param fecha fecha de reserva
     * @param hora hora de la reserva
     * @param reservable si es bar o restaurante
     * @param descuento descuento aplicado
     */
    public Reserva(Cliente cliente, LocalDate fecha, LocalTime hora, Reservable reservable, double descuento) {
        this.cliente = cliente;
        this.fecha = fecha.toString();
        this.hora = hora.toString();
        this.local = reservable;
        this.descuento = descuento;

    }

    public Reserva() {

    }

    public Reserva(String xmlString) throws XMLParsingException {
        JAXBContext jaxbContext;

        try {

            jaxbContext = JAXBContext.newInstance(Reserva.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Reserva u = (Reserva) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

            this.cliente = u.cliente;
            this.fecha = u.fecha;
            this.hora = u.hora;
            this.local = u.local;
            this.descuento = u.descuento;

        } catch (JAXBException e) {
            throw new XMLParsingException("Fallo al leer el String");
        }
    }

    public Reservable getReservable() {
        return local;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return new String("El cliente " + cliente.getNick() + " tiene una reserva en " + local.getNombre() + " con fecha " + fecha + " " + hora + ".");
    }

    @Override
    public String toXML() {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Reserva.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Reserva.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Reserva.class);

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
