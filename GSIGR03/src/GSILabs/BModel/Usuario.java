/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author alumno
 */
public class Usuario {
    public String nick;
    public String password;
    public String fechaNacimiento;

    //Constructor
    public Usuario(String nick, String password, String fechaNacimiento) {
        this.nick = nick;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }

    
    //Comprueba que la longitud del nick sea menor que 3
    public void comprobarLongitudNick (){
        if (nick.length() < 3){
            System.out.println("ERROR el nombre debe tener minimo 3 caracteres");
        }
    }
    
    //comprueba que los usuarios sean mayores de 14 años
    public static void comprobarEdadUsuarios (String fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try{
            int edad = (int) ((TimeUnit.DAYS.convert(((new Date()).getTime() - sdf.parse(fecha).getTime()),TimeUnit.MILLISECONDS))/365);
            if (edad < 14){
                System.out.println("ERROR el usuario debe tener mínimo 14 años");
            }
        }catch(ParseException e){
            System.out.println("ERROR la fecha introducida no es correcta");
        }
    }    
}
