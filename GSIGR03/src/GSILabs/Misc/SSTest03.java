/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.Misc;

import java.io.File;
import java.io.IOException;
import org.jopendocument.dom.spreadsheet.Cell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * @author GR03
 * @version 1.0
 */
public class SSTest03 {
    public static void main (String args[]) throws IOException{
        //Guarda los datos en el fichero "test01.ods"
        File file = new File("test02.ods");
        //Leemos el fichero
        LeerFicheroTest02(file);
    }
    public static void LeerFicheroTest02(File file) throws IOException {
        Sheet sheet;
        //Accedo a la hoja de cálcula mediante Spreadsheet
        sheet = SpreadSheet.createFromFile(file).getSheet(0); 
            
        Cell cell;
            
        System.out.println("Los datos almacenados en el test02 son los siguientes: ");
        
        //Recorro la hoja de cálculo desde la fila 5 que es donde empiezan los datos hasta el último dato que estará en la fila sheet.getRowCount()
        for (int fila = 5; fila < sheet.getRowCount(); fila++) {
            //Recorro la hoja de cálculo desde la columna 3 que es donde empiezan los datos hasta el último dato que estará en la columna sheet.getColumnCount()
            for (int columna = 3; columna < sheet.getColumnCount(); columna++) { 
                //Accedo a la celda [fila][columna]
                cell = sheet.getCellAt(columna, fila); 
                //Accedo al valor de la celda
                System.out.print(cell.getValue() + " ");                            
            }
            System.out.println();
        }
    }
}
