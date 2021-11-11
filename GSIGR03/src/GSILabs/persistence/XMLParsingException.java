/*
 * Excepcion personalizada a lanzar en aquellos metodos
 * que analicen un XML
 */
package GSILabs.persistence;

/**
 *
 * @author GR03
 */
public class XMLParsingException extends Exception{
    
    public XMLParsingException(String msg){
        
        super(msg);
        
    }
    
}
