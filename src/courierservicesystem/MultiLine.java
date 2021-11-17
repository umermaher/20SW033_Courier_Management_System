
package courierservicesystem;

import java.awt.Component;
import java.awt.List;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;
import sun.awt.SunHints.Value;

//To make JTable's TableCellRenderer as JTextArea
public class MultiLine extends JTextArea implements TableCellRenderer{
    private final ArrayList<Integer> rcHieght=new ArrayList<Integer>();

    public MultiLine() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int i, int i1) {
        setText((value==null) ? "" : value.toString());
        setEditable(false);
        this.setBackground(java.awt.Color.white);
       // adjustRowHieght(table,i,i1);
        return this;
    }
    
}