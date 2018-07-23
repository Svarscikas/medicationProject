import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * Created by Arnas on 7/21/2018.
 */
public class MyTableModel extends AbstractTableModel{

    private Object[][] data;
    private Object[] columnNames;

    public void setTableData(Object[] customNames, Object[][] customData){
        this.columnNames = customNames;
        this.data = customData;
    }
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col].toString();
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
}
