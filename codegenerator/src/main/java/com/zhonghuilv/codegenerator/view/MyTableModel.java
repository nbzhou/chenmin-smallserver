package com.zhonghuilv.codegenerator.view;

import com.zhonghuilv.codegenerator.DbModelProvider;
import com.zhonghuilv.codegenerator.model.Table;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * Created by 郑靖 on 2017/5/17 10:20.
 */
public class MyTableModel implements TableModel {

    public static Table getLastModelTable(int cols) {
        return last.tables.get(cols);
    }

    private static MyTableModel last = null;
    private List<Table> tables;

    private static final int COLUMN_COUNT = 2;
    private int _rowCount;

    public MyTableModel() {
        try {
            this.tables = DbModelProvider.getInstance().getAllTables();
            _rowCount = this.tables.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        last = this;
    }

    public MyTableModel(String dbname) {
        try {
            this.tables = DbModelProvider.getInstance().getAllTables(dbname);
            _rowCount = this.tables.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        last = this;
    }

    //多少行:
    @Override
    public int getRowCount() {
        return _rowCount;
    }

    //多少列
    @Override
    public int getColumnCount() {

        return COLUMN_COUNT;

    }

    //取得列名
    @Override
    public String getColumnName(int columnIndex) {

        return columnIndex % 2 == 0 ? "表名" : "注释";
    }

    //每一列的数据类型:我们这里显示的都是String型
    @Override
    public Class<?> getColumnClass(int columnIndex) {

        return String.class;

    }

    //指定的单元格是否可从界面上编辑
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;

    }

    //取得指定单元格的值
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Table table = tables.get(rowIndex);
        return columnIndex % 2 == 0 ? table.getSqlName() : table.getTableRemark();

    }

    //从表格界面上改变了某个单元格的值后会调用这个方法
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (columnIndex == 1) {
            tables.get(rowIndex).setTableRemark((String) aValue);
        }

    }

    /**
     * Adds a listener to the list that is notified each time a change
     * to the data model occurs.
     *
     * @param l the TableModelListener
     */
    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    /**
     * Removes a listener from the list that is notified each time a
     * change to the data model occurs.
     *
     * @param l the TableModelListener
     */
    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

}
