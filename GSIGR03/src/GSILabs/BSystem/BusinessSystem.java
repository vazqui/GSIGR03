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
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;


/**
 * Clase para el BusinessSystem
 * @author GR03
 * @version 1.0
 */
@XmlRootElement
public class BusinessSystem implements LeisureOffice, XMLRepresentable {
    
    /** Propiedades **/
    public BusinessData almacenamiento;
    
    /** Constructor **/
    public BusinessSystem() {
        this.almacenamiento = new BusinessData();
    }
   
     
    //**USUARIOS**//
    
    /**
     * Da de alta un usuario, en caso de que su informacion no incumpla las 
     * normas referentes al nick o edad.
     * @param u El nuevo usuario
     * @return Cierto si el usuario pudo ser añadido.
     */
    @Override
    public boolean nuevoUsuario(Usuario u) {
        try {
            if (existeNick(u.nick)) {
                System.out.println("No se puede añadir el usario. El nick ya existe.");
                return false;
            } else {
                almacenamiento.usuarios.add(u);
                System.out.println("Usuario " + u.nick + " añadido correctamente.");
                return true;
            }
        } catch (NullPointerException ex) {
            System.out.println("ERROR al crear usuario.");
            return false;
        }
    }
    
    /**
     * Elimina al usuario que se pase como argument.
     * @param u El usuario
     * @return True si y solo si el usuario existia y pudo ser eliminado.
     */
    @Override
    public boolean eliminaUsuario(Usuario u) {
        if (existeNick(u.nick)) {
            System.out.println("El usuario se ha eliminado correctamente.");
            almacenamiento.usuarios.remove(u);
            return false;
        } else {
            System.out.println("Usuario " + u.nick + " no existe, no se puede eliminar.");
            return true;
        }
    }
    
    /**
     * Reemplaza en el sistema al usuario viejo por el nuevo. Para que esto suceda debe
     * Cumplirse que el usuario viejo exista y que el nuevo no incumpla normas
     * relativas a las politicas de Usuarios (nick y/o edad)
     * @param u El usuario
     * @param nuevoU El nuevo usuario
     * @return True si el usuario se encontro y pudo ser modificado
     */
    @Override
    public boolean modificaUsuario(Usuario u, Usuario nuevoU) {
        if (eliminaUsuario(u) && nuevoUsuario(u)) {
            System.out.println("Usuario modificado correctamente.");
            return true;
        } else {
            System.out.println("El usuario no se ha podido modificar.");
            return false;
        }
    }
    
    /**
     * Comprueba si existe algun usuario con ese mismo nick
     * @param nick nick del usuario
     * @return True si existe un usuario con ese nick
     */
    @Override
    public boolean existeNick(String nick) {
        if (!almacenamiento.usuarios.isEmpty()) {
            for (Usuario usuario : almacenamiento.usuarios) {
                if (nick.contentEquals(usuario.nick)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Recupera el usuario asociado a un nick, en caso de que exista.
     * @param nick nick del usuario
     * @return El usuario con el nick. Debe devolver null si existeNick(nick) es falso.
     */
    @Override
    public Usuario obtenerUsuario(String nick) {
        for (Usuario u : almacenamiento.usuarios) {
            if (nick.contentEquals(u.nick)) {
                return u;
            }
        }
        return null;
    }

    //**REVIEWS**//
    
    /**
     * Incorpora una nueva review al sistema, en caso de que sus datos (Usuario,
     * Local) sean correctos y no haya otra introducida para la misma fecha.
     *
     * @param r La review a introducir al sistema.
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean nuevaReview(Review r) {
        try{
            Usuario usuario = obtenerUsuario(r.nickUsuario);
            //Comprobamos que existe el usuario y que no tenga una review en ese local en la misma fecha
            if(existeNick(r.getNickUsuario()) && !existeRewiew(usuario, r.local, LocalDate.parse(r.fecha))){
                almacenamiento.reviews.add(r);
                for (Local l : almacenamiento.locales) {
                    l.equals(r.local);
                    l.reviews.add(r);
                }
                System.out.println("Rewiew añadida correctamente.");
                return true;
            }else{
                System.out.println("No se puede añadir la rewiew.");
                if(existeRewiew(usuario, r.local, LocalDate.parse(r.fecha))){
                    System.out.println("Ya existe una rewiew para esta fecha");
                }
                return false;
            }
        } catch (NullPointerException ex) {
            System.out.println("ERROR al crear la review.");
            return false;
        }
    }
    
    /**
     * Elimina una review del sistema, siempre y cuando exista y no tenga una 
     * contestacion asociada.
     * @param r Review a eliminar
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminaReview(Review r) {
        return almacenamiento.reviews.remove(r);
    }
    
    /**
     * Comprueba si la visita de un usuario a un local en una fecha dada ha sido 
     * comentada. En caso deque alguno de los datos sea incorrecto, o inexistente, el 
     * resultado sera false.
     * @param u el usuario
     * @param l el local visitadl
     * @param ld la fecha de visita
     * @return True si y solo si la review existe.
     */
    @Override
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld) {
       if (!almacenamiento.reviews.isEmpty()) {
           for(Review review : almacenamiento.reviews){
               if(u.nick.contentEquals(review.getNickUsuario()) &&
                    l.equals(review.local) &&
                    ld.toString().equals(review.fecha))
               {
                   //System.out.println("El usuario " + u.nick +" dejó una review en el local " + l.getNombre() + " con fecha " + ld);
                   //Este log resulta confuso a la hora de tratar reviews, por eso se deja comentado
                   return true;
               }
           }
       }
        return false;
    }

    //**CONTESTACIONES**//
    
    /**
     * Añade una contestacion a una review, en caso de que la review exista y no este 
     * ya comentada.
     * @param c Contestacion a añadir
     * @param r Review
     * @return True si y solo si la operacion fue completada y se pudo añadir la review.
     */
    
    
    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {
        try {
            Usuario user = obtenerUsuario(r.nickUsuario);
            //Comprobamos que exista la review y que  no tenga ya una contestacion
            if (existeRewiew(user, r.local, LocalDate.parse(r.fecha)) && !tieneContestacion(r)) {
                r.añadirContestacion(c);
                System.out.println("Contestacion creada.");
                System.out.println("El propietario " + c.p.nick + " ha contestado a una review de " + r.nickUsuario +" en el local " + r.local.getNombre() + " con fecha " + r.fecha);
                return true;
            } else {
                System.out.println("ERROR al crear respuesta.");
                if(!existeRewiew(user, r.local, LocalDate.parse(r.fecha) )){
                    System.out.println("La review a la que se quiere contestar no existe.");
                }
                if(tieneContestacion(r)){
                    System.out.println("La review ya tiene contestacion");
                }
                return false;
            }
        } catch (NullPointerException ex) {
            System.out.println("ERROR al crear respuesta.");
            return false;
        }
    }
    
    /**
     * Consulta la existencia de una contestacion para una review. Devolvera
     * falso si la contestacion no existe, o si la Review no esta registrada en
     * el sistema.
     * @param r Review a añadir
     * @return True si y solo si la Review existe y tiene contestacion
     */
    @Override
    public boolean tieneContestacion(Review r) {
        if (r.respuesta == null) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Recupera la contestacion para una review dada, si esta existe.
     * @param r Review a consultar
     * @return La contestacion, o null si esta, o la propia review, no existen
     */
    @Override
    public Contestacion obtenerContestacion(Review r) {
        try {
            return r.respuesta;
        } catch (NullPointerException ex) {
            return null;
        }
    }
    
    /**
     * Elimina la contestacion pasada como argumento
     * @param c Contestacion a eliminar
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminaContestacion(Contestacion c) {
        try {
            for (Review review : almacenamiento.reviews) {
                if (review.respuesta.equals(c)) {
                    review.respuesta = null;
                    System.out.println("Contestacion eliminada correctamente.");
                    return true;
                } else {
                    System.out.println("ERROR al eliminar respuesta.");
                    return false;
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("ERROR al eliminar respuesta.");
            return false;
        }
        return false;
    }
    
    /**
     * Elimina la contestacion asociada a una review
     * @param r La review cuya contestacion hay que elimnar
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminaContestacion(Review r) {
        try {
            r.respuesta = null;
            System.out.println("Contestacion eliminada correctamente.");
            return true;
        } catch (NullPointerException ex) {
            System.out.println("ERROR al eliminar respuesta.");
            return false;
        }
    }

    //**LOCALES**//
    
    /**
     * Añade un local al sistema, siempre que no exista otro en la misma direccion.
     * @param l El nuevo local
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean nuevoLocal(Local l) {
        for (Local local : almacenamiento.locales) {
            if (local.equals(l)) {
                System.out.println("En esta direccion ya hay un local.");
                return false;
            }
        }
        almacenamiento.locales.add(l);
        System.out.println("Local añadido correctamente.");
        return true;
    }
    
    /**
     * Elimina un local determinado, si este existe como tal en el sistema.
     * @param l EL local a eliminar
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminarLocal(Local l) {
        for (Local local : almacenamiento.locales) {
            if (local.equals(l) && local.nombre.contentEquals(l.nombre)) {
                almacenamiento.locales.remove(l);
                System.out.println("Local eliminado correctamente.");
                return true;
            }
        }
        System.out.println("Local no encontrado, no se ha podido eliminar.");
        return false;
    }

    
    /**
     * Importa una lista de bares desde un fichero externo.
     * @param f apunta a un fichero .ods existente, de una sola pagina,
     * con el nombre del bar al que se refiere en la primera columna
     * @return El numero de bares incorporados con exito al sistema
     */
    public int importaBares(File f){
        
        Sheet spreadsheet;
        int contadorOK = 0;
        
        try {
            //El fichero debe tener una unica hoja

            spreadsheet = SpreadSheet.createFromFile(f).getSheet(0);
            
            //Contador de fila y columna
            int nColCount = spreadsheet.getColumnCount();
            int nRowCount = spreadsheet.getRowCount();

            System.out.println("Rows :" + nRowCount);
            System.out.println("Cols :" + nColCount);
            //Iteramos cada fila del documento
            MutableCell cell = null; //Clase de JOpenDocument
            for (int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++) {  

                //Leemos nombre (1 celda)
                cell = spreadsheet.getCellAt(0, nRowIndex);
                String nombre = cell.getValue().toString();
                
                //Leemos direccion (3 celdas)
                cell = spreadsheet.getCellAt(1, nRowIndex);
                String aux = cell.getValue().toString();
                String[] partes = aux.split(" ");
                //Separamos el numero de la calle (ultima posicion) de la calle
                //Usando una coma (,)
                int k=0;
                String direccion = partes[0];
                k++;
                while(k<partes.length-1){
                    direccion = direccion.concat(" " + partes[k]);
                    k++;
                }
                direccion = direccion.concat(", " + partes[k]);
                
                cell = spreadsheet.getCellAt(2, nRowIndex);
                direccion = direccion.concat(", " + cell.getValue().toString());
                cell = spreadsheet.getCellAt(3, nRowIndex);
                direccion = direccion.concat(", " + cell.getValue().toString());
                
                //Leemos propietario (1 columna)
                cell = spreadsheet.getCellAt(4, nRowIndex);
                String propietario = cell.getValue().toString();
                /*** Puede haber mas de un propietario, vienen en formato
                 * 
                 * {Daniel Gomez, Francisco Perez, Pablo Asado}
                 * 
                 */
                
                //Creamos el bar
                Bar bar = new Bar(nombre, direccion, "Muy malo y bueno");
                List<String> tags = new ArrayList<String>();

                //Leemos tags (? columnas)
                int i = 5;
                cell = spreadsheet.getCellAt(i, nRowIndex);
                while(!cell.getValue().toString().isEmpty() && !cell.getValue().toString().equals(" ")){
                    cell = spreadsheet.getCellAt(i, nRowIndex);
                    
                    tags.add(cell.getValue().toString());
                    
                        i++;
                        
                        if(i==nColCount)
                        break;
                    cell = spreadsheet.getCellAt(i, nRowIndex);
                }
                
                bar.setTags(tags);

                System.out.println("El bar leido es: " + nombre + " " + direccion + " " + propietario + " " + bar.tags);
                System.out.println("");
                
                //Incorporamos el local al sistema
                if(nuevoLocal(bar))
                    contadorOK++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Devolvemos el numero de bares introducidos con exito
        return contadorOK;
    }
    
    
    /**
     * Obtiene los datos del local instalado en una determinada direccion fisica
     * @param d Direccion del local.
     * @return El local almacenado en el sistema, o null si no existe.
     */
    @Override
    public Local obtenerLocal(Direccion d) {
        for (Local local : almacenamiento.locales) {
            if (local.direccion.equals(d)) {
                System.out.println("Local encontrado.");
                return local;
            }
        }
        System.out.println("Local no encontrado.");
        return null;
    }
    
    /**
     * Asocia un local a un propietario, en caso de que ambos existan y no se haya llegado
     * al limite de Propietarios por local
     * @param l Local existente en en sistema
     * @param p Propietario existente en el sistema
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean asociarLocal(Local l, Propietario p) {
        try {
            if(l.addPropietario(p)){
                p.addLocal(l);
            }
            else{
                return false;
            }
            System.out.println(p.nick + " ahora es propietario del local " + l.nombre);
            return true;
        } catch (NullPointerException ex) {
            System.out.println("ERROR al añadir propietario al local.");
            return false;
        }
    }
    
    /**
     * Desliga un local de un propietario, en caso de que ambos existan y estén
     * ya relacionados
     * @param l Local existente en en sistema
     * @param p Propietario existente en el sistema
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        try {
            l.propietarios.remove(p);
            System.out.println("Propietario desasociado de su local.");
            return true;
        } catch (NullPointerException ex) {
            System.out.println("ERROR al quitar propietario.");
            return false;
        }
    }
    
    /**
     * 
     * @param viejoL viejo local
     * @param nuevoL nuevo local
     * @return true
     */
    @Override
    public boolean actualizarLocal(Local viejoL, Local nuevoL) {
        try {
            eliminarLocal(viejoL);
            nuevoLocal(nuevoL);
            System.out.println("Local actualizado.");
            return true;
        } catch (NullPointerException ex) {
            System.out.println("ERROR al actualizar el local.");
            return false;
        }
    }

    //**Locales**//
    
    /**
     * Ver las review asociadas a un local
     * @param l Local existente en en sistema
     * @return Lista de reviews del sistema. En caso de que el Local no exista, sera
     *  el valor null.
     */
    @Override
    public Review[] verReviews(Local l) {
        return (Review[]) l.reviews.toArray();
    }

    /**
     * Anota una nueva reserva para un cliente dado, en un local reservable
     * para una fecha y hora concreta. El cliente y el local deben existir,
     * y la fecha y hora debe ser futura. El cliente no debe tener otra reserva para el
     * mismo local en la misma fecha.
     * @param c Cliente que hace la reserva
     * @param r Local donde se efectua la reserva
     * @param ld Fecha de la reserva
     * @param lt Hora de la reserva
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt) {
        try {
            if (existeNick(c.nick)) {
                Reserva reserva = new Reserva(c, ld, lt, r);
                almacenamiento.reservas.add(reserva);
                System.out.println("Reserva creada correctamente.");
                System.out.println(reserva);
                return true;
            } else {
                System.out.println("ERROR al crear reserva.");
                return false;
            }
        } catch (NullPointerException ex) {
            return false;
        }
    }
    
    /**
     * Obtiene todas las reservas (futuras y pasadas) del cliente.
     * @param c El cliente a consultar
     * @return La lista de las reservas, o null si el cliente no existe.
     */
    @Override
    public Reserva[] obtenerReservas(Cliente c) {
        return (Reserva[]) c.reservas.toArray();
    }

    /**
     * Obtiene todas las reservas (futuras y pasadas) del local.
     * @param r El local a consultar
     * @return La lista de las reservas, o null si el local no existe.
     */
    @Override
    public Reserva[] obtenerReservas(Reservable r) {
        try {
            List<Reserva> aux = new ArrayList<>();
            for (Reserva reserva : almacenamiento.reservas) {
                if (reserva.getReservable().equals(r)) {
                    aux.add(reserva);
                }
            }
            return (Reserva[]) aux.toArray();
        } catch (NullPointerException ex) {
            return null;
        }
    }
    
    /**
     * Obtiene todas las reservas del dia usado como argumento
     * @param ld la fecha a consultar
     * @return La lista de las reservas.
     */
    @Override
    public Reserva[] obtenerReservas(LocalDate ld) {
        try {
            List<Reserva> aux = new ArrayList<>();
            for (Reserva reserva : almacenamiento.reservas) {
                if (reserva.getFecha().equals(ld)) {
                    aux.add(reserva);
                }
            }
            return (Reserva[]) aux.toArray();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    /**
     * Elimina una reserva del sistema, en caso de que esta exista
     * @param r La reserva a eliminar.
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminarReserva(Reserva r) {
        try {
            almacenamiento.reservas.remove(r);
            System.out.println("Reserva eliminada correctamente.");
            return true;
        } catch (NullPointerException ex) {
            System.out.println("ERROR al eliminar reserva.");
            return false;
        }
    }
    
    /**
     * Lista los bares en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de locales, potencialmente de longitud 0.
     */
    @Override
    public Local[] listarLocales(String ciudad, String provincia) {
        try {
            List aux = new ArrayList<>();
            for (Local l : almacenamiento.locales) {
                if (l.direccion.localidad.contentEquals(ciudad) && l.direccion.provincia.contentEquals(provincia)) {
                    aux.add(l);
                }
            }
            return (Local[]) aux.toArray();
        } catch (NullPointerException ex) {
            return null;
        }
    }
    
    /**
     * Lista los bares en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de bares, potencialmente de longitud 0.
     */
    @Override
    public Bar[] listarBares(String ciudad, String provincia) {
        try {
            List aux = new ArrayList<>();
            for (Local l : almacenamiento.locales) {
                if (l.getClass().getName().contentEquals("GSILabs.BModel.Bar")) {
                    aux.add(l);
                }
            }
            return (Bar[]) aux.toArray();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    /**
     * Lista los bares en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de bares, potencialmente de longitud 0.
     */
    @Override
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {
        try {
            List aux = new ArrayList<>();
            for (Local l : almacenamiento.locales) {
                if (l.getClass().getName().contentEquals("GSILabs.BModel.Restaurante")) {
                    aux.add(l);
                }
            }
            return (Restaurante[]) aux.toArray();
        } catch (NullPointerException ex) {
            return null;
        }
    }
    
    /**
     * Lista los bares en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de bares, potencialmente de longitud 0.
     */
    @Override
    public Pub[] listarPubs(String ciudad, String provincia) {
        try {
            List aux = new ArrayList<>();
            for (Local l : almacenamiento.locales) {
                if (l.getClass().getName().contentEquals("GSILabs.BModel.Pub")) {
                    aux.add(l);
                }
            }
            return (Pub[]) aux.toArray();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    @Override
    public String toXML() {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(BusinessSystem.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(BusinessSystem.class);

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
            JAXBContext jaxbContext = JAXBContext.newInstance(BusinessSystem.class);

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
    
    public static BusinessSystem parseXMLFile(File f) {
        try{
             JAXBContext jaxbContext = JAXBContext.newInstance(BusinessSystem.class);
             Unmarshaller mars = jaxbContext.createUnmarshaller();
             BusinessSystem almacenamiento = (BusinessSystem)mars.unmarshal(f);
             return almacenamiento;
        }catch(JAXBException e){
            System.out.println(e);
            return null;
        }
    }

}
