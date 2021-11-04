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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase para la Review
 *
 * @author GR03
 * @version 1.0
 */
@XmlRootElement
public class Review implements XMLRepresentable {

    /**
     * Propiedades *
     */
    final public int[] tipoValoracion = {0, 1, 2, 3, 4, 5};     //Valoración del local
    public String nickUsuario;                                         //Nick del usuario que escribe la review 
    public String comentario;                                          //Comentario describiendo la opinión del usuario
    @XmlTransient
    public Local local;//Convertir esta direccion a un Local       //Local al que se le está escribiendo el comentario
    public Contestacion respuesta;                                     //Respuesta del propietrio a la review
    public String fecha;

    /**
     * Constructor
     *
     * @param nickUsuario nick del usuario que realiza la review
     * @param comentario comentario describiendo la opinión del usuario
     * @param local local donde tiene la review
     */
    public Review(String nickUsuario, String comentario, Local local) {

        if (comprobarReview(comentario)) {
            this.nickUsuario = nickUsuario;
            this.comentario = comentario;
            this.fecha = LocalDate.now().toString();
            this.local = local;
        } else {
            System.out.println("No se puede crear la review. Numero maximo de caracteres alcanzado.");
        }
    }

    public Review() {

    }

    public Review(String xmlString) throws XMLParsingException {
        JAXBContext jaxbContext;

        try {

            jaxbContext = JAXBContext.newInstance(Review.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Review u = (Review) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

            this.nickUsuario = u.nickUsuario;
            this.comentario = u.comentario;
            this.fecha = u.fecha;
            this.local = u.local;

        } catch (JAXBException e) {
            throw new XMLParsingException("Fallo al leer el String");
        }
    }

    /**
     * Función para añadir una contestación a la review
     *
     * @param c La contestación publicada
     */
    public void añadirContestacion(Contestacion c) {

        if (respuesta == null) {
            respuesta = c;
        } else {
            System.out.println("Ya existe respuesta del propietario a esta review.");
        }
    }

    /**
     * Comprueba el tamaño de la review
     *
     * @param comentario comentario escrito por el cliente
     * @return True si y solo sí se ha completado la accion
     */
    public boolean comprobarReview(String comentario) {
        if (comentario.length() >= 500) {
            System.out.println("ERROR mas de 500 caracteres...");
            return false;
        }
        return true;
    }

    /**
     * Obtiene el nick del usuario que hace la review
     *
     * @return Devuelve el nick del usuario
     */
    public String getNickUsuario() {
        return nickUsuario;
    }

    /**
     * Obtiene el nombre del local del al que se le hace la review
     *
     * @return Devuelve el nombre del local
     */
    public Local getLocal() {
        return local;
    }

    /**
     * Obtiene la valoración del local
     *
     * @param i valoración del local
     * @return la valoracion
     */
    public int getValoracion(int i) {
        if (i >= 0 && i <= 5) {
            return tipoValoracion[i];
        } else {
            System.out.println("ERROR valoración debe ser entre 0 y 5");
            return -1;
        }
    }

    /**
     * Comprueba si la contestación se puede realizar
     *
     * @param review review que ha sido comentada
     * @param contestacion contestación a realizar
     */
    public void ComoprobarContestacion(Review review, Contestacion contestacion) {
        if (review.respuesta.equals(null)) {
            review.respuesta = contestacion;
        } else {
            System.out.println("ERROR ya existe contestación");
        }
    }

    @Override
    public String toXML() {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Review.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Review.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(Review.class);

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
