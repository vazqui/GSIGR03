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
import org.jopendocument.sample.SpreadSheetCreation;

/**
 * @author GR03
 * @version 1.0
 */
public class SSTest01 {

    public static void main(String[] args) throws IOException {

        // Crea el array bidimensional de 4 x 6
        final Object[][] data = new Integer[4][6];
        String[] columns = new String[]{"", "", "", "", "", ""};

        //Asigna a cada posicion un numero entero aleatorio del 1 al 10
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                data[i][j] = (int) (Math.random() * 10 + 1);
            }
        }

        //Crea la tabla con los datos del array
        TableModel model = new DefaultTableModel(data, columns);

        //Guarda los datos en el fichero "test01.ods"
        final File file = new File("test01.ods");
        SpreadSheet sheet = SpreadSheet.create(1, 1, 1);
        sheet.getSheet(0).merge(model, 0, 0);

        //Abre el fichero en el programa de OpenOffice
        OOUtils.open(sheet.saveAs(file));
    }
}
