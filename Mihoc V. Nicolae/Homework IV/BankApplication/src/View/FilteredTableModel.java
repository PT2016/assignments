package View;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class FilteredTableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int nrRows = 1;
    private String[] columnNames = {"Acc ID", "Acc Type", "Client ID", "Name", "Address", "Balance"};
    private Object[][] data = new Object[][]{{"","","","","",""}};

   
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public int getRowCount() {
        return data.length;
    }
    
    public Object[] getRowAt(int row) {
        return data[row];
    }
    
    
    public Object getValueAt(int row, int col) {
        if ((data.length > row) && (row >= 0)) {
            return data[row][col];
        } else {
            return null;
        }
    }
    
    
    public void setData(Object[][] p) {
        data = new Object[p.length + 1][6];
       // Object p1;
        for (int i = 0; i < p.length; i++) {
           // p1 = p[i];
            if(p[i][0]!=null){
                data[i] = p[i];    
            } else {
                data[i]=new Object[]{"", "", "", "","",""};
            }
            }
        data[data.length - 1] = new Object[]{"", "", "", "","",""};
        nrRows = data.length;
        fireTableStructureChanged();
    }
  
    public void setRow(Object[] o, int row) {
        if ((row == data.length) && (o != null)) {
            if (o[0].equals("")) {
                return;
            }
            Object[][] data1 = new Object[data.length + 1][data[0].length];
            for (int i = 0; i < data.length; i++) {
                data1[i] = data[i];
            }
            data1[row - 1] = o;
            data1[row] = new Object[]{"","", "", "", "","", ""};
            data = data1;

        } else if (o == null) {
            if (data.length == 1) {
                data[0] = new Object[]{"", "", "", "","",""};
            } else {
                Object[][] data1 = new Object[data.length - 1][7];
                System.arraycopy(data, 0, data1, 0, row);
                for (int i = row + 1; i < data.length; i++) {
                    data1[i - 1] = data[i];
                }

                data = data1;
            }
        } else {
            data[row] = o;

        }
    }
    
    public void setValueAt(Object value, int row, int col) {
        if (row == data.length) {
            Object[][] data1 = new Object[data.length + 1][data[0].length];
            for (int i = data.length - 1; i >= 0; i--) {
                data1[i] = data[i];
            }
            data1[row] = data[row];
            data = data1;
        }
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    public static int getRow(TableModel model, Object o) {
		int row = 0;
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			for (int j = model.getColumnCount() - 1; j >= 0; j--) {
				if (model.getValueAt(i, j).equals(o)) {
					row = i;
				}
			}
		}
		return row;
	}
    
}
