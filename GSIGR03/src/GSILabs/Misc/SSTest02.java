/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author alumno
 */
public class SSTest02 {
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException {
        
        final Object[][] m = new Integer [4][6];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                m[i][j] = (int) (Math.random() * 30);
            }
            
        }
        
        String [] columns = new String [] { " ", " " , " ", " ", " ", " "};                                              
        TableModel model = new DefaultTableModel(m, columns);

        final File file = new File("test02.ods");
        SpreadSheet sheet = SpreadSheet.create(1, 1, 1);
        
        sheet.getSheet(0).merge(model, 3, 5);
       
        Sheet sheet1 = sheet.getSheet(0);
        
        for (int i = 5; i < 9; i++) {
            for (int j = 3; j < 9; j++) {
                if(Integer.parseInt(sheet1.getCellAt(j, i).getValue().toString()) < 10){              
                    sheet1.getCellAt(j,i).setBackgroundColor(Color.RED);
                }else{
                    sheet1.getCellAt(j,i).setBackgroundColor(Color.blue);
                }
                
            }
        }

        OOUtils.open(sheet.saveAs(file));
        
    }
    
    
}
