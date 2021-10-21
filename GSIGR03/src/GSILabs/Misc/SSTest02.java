/*
 * Proyecto de Practicas
 * Gestion de Sistemas de Informacion
 * Curso Academico 21/22
 * Grupo GR03
 */
package GSILabs.Misc;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * @author GR03
 * @version 1.0
 */
public class SSTest02 {
    
    
    public static void main(String[] args) throws IOException {
        
        // Crea el array bidimensional de 4 x 6 con numeros aleatorios
        final Object[][] m = new Integer [4][6];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                m[i][j] = (int) (Math.random() * 30);
            }
            
        }
        
        //Crea la tabla con los datos del array
        String [] columns = new String [] { " ", " " , " ", " ", " ", " "};                                              
        TableModel model = new DefaultTableModel(m, columns);
        
        //Guarda los datos en el fichero "test01.ods" y creamos el sheet
        final File file = new File("test02.ods");
        SpreadSheet sheet = SpreadSheet.create(1, 1, 1);
        
        //Posicionamos la matriz en la posicion que se nos indica en el enunciado
        sheet.getSheet(0).merge(model, 3, 5);
       
        Sheet sheet1 = sheet.getSheet(0);
        
        //Recorremos la matriz, comprobamos si su valor es mayor o menor que 10,
        //y le asignamos el color correspondiente a cada celda.
        for (int i = 5; i < 9; i++) {
            for (int j = 3; j < 9; j++) {
                if(Integer.parseInt(sheet1.getCellAt(j, i).getValue().toString()) < 10){              
                    sheet1.getCellAt(j,i).setBackgroundColor(Color.RED);
                }else{
                    sheet1.getCellAt(j,i).setBackgroundColor(Color.blue);
                }
                
            }
        }
        
        //Abre el fichero en el programa de OpenOffice
        OOUtils.open(sheet.saveAs(file));
        
    }
    
    
}
