/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.Misc;

import GSILabs.BSystem.*;
import java.io.File;

/**
 * @author GR03
 * @version 1.0
 */
public class SSTest05 {
    
    public static void main(String[] args) {
        
        BusinessSystem system = new BusinessSystem();
        
        File f = new File("ejemplo.ods");
        
        System.out.println(system.importaBares(f));
        
    }
    
    
    
}
