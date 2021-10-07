/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.jopendocument.dom.spreadsheet.Cell;
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author alumno
 */
public class SSTest03 {
    public static void main (String args[]) throws IOException{
        File file = new File("test02.ods");
        LeerFicheroTest02(file);
    }
    public static void LeerFicheroTest02(File file) throws IOException {
        Sheet sheet;
        sheet = SpreadSheet.createFromFile(file).getSheet(0); //Accedo a la hoja de cálcula mediante Spreadsheet
            
        Cell cell;
            
        System.out.println("Los datos almacenados en el test02 son los siguientes: ");
            
        for (int fila = 5; fila < sheet.getRowCount(); fila++) { //Recorro la hoja de cálculo desde la fila 5 que es donde empiezan los datos hasta el último dato que estará en la fila sheet.getRowCount()
            for (int columna = 3; columna < sheet.getColumnCount(); columna++) { //Recorro la hoja de cálculo desde la columna 3 que es donde empiezan los datos hasta el último dato que estará en la columna sheet.getColumnCount()
                cell = sheet.getCellAt(columna, fila); //Accedo a la celda [fila][columna]
                System.out.print(cell.getValue() + " ");//Accedo al valor de la celda
            }
            System.out.println();
        }
    }
}
