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

    //Obtiene el nick del usuario
    public String getNick() {
        return nick;
    }
    
    //Establece el nick del usuario
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    //Obtiene la contraseña del usuario
    public String getPassword() {
        return password;
    }

    //Establece la contraseña del usuario
    public void setPassword(String password) {
        this.password = password;
    }

    //Obtiene la fecha de nacimiento del usuario
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    //Establece la fecha de nacimiento del usuario
    public void setFechaNacimiento(String fechaNacimiento) {
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
