/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Misc;

import GSILabs.BModel.*;
import GSILabs.BSystem.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author alumno
 */
public class SSTest05 {
    
    public static void main(String[] args) {
        
        BussinessSystem system = new BussinessSystem();
        
        File f = new File("ejemplo.ods");
        
        System.out.println(system.importaBares(f));
        
    }
    
    
    
}
