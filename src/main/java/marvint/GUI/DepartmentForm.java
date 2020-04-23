package marvint.GUI;

import marvint.domain.Department;
import marvint.domain.Department;
import marvint.domain.Mail;
import marvint.domain.Otdel;
import marvint.сontroller.DepartmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DepartmentForm {
    @Autowired
    private DepartmentController departmentController;


    public class DepartmentTableModel implements TableModel{
        private List<Department> departmentList;
        private Set<TableModelListener> listeners = new HashSet<>();

        public DepartmentTableModel(List<Department> departmentList) {
            this.departmentList = departmentList;
        }

        @Override
        public int getRowCount() {
            return departmentList.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "ID";
                case 1:
                    return "Название";
                case 2:
                    return "Адрес";
                case 3:
                    return "Отделы";
            }
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            var mockDepartment = new Department();
            switch (columnIndex) {
                case 0:
                    return Long.class;
                case 1:
                case 2:
                    return String.class;
                case 3:
                    return List.class;
            }
            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Department department = departmentList.get(rowIndex);
            switch (columnIndex)
            {
                case 0:
                    return department.getId();
                case 1:
                    return department.getTitle();
                case 2:
                    return department.getAddress();
                case 3:
                    return joinOtdels(department.getOtdel());
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

        @Override
        public void addTableModelListener(TableModelListener l) {
            listeners.add(l);
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
            listeners.remove(l);
        }
    }

    private String joinOtdels(List<Otdel> otdels) {
        String s = "";
        for (var otdel : otdels) {
            s += otdel.getTitle();
            s += ", ";
        }
        return s.substring(0, s.length() - 2);
    }

    public JTable displayTable() {
        List<Department> departmentList = departmentController.getDepartmentList();
        TableModel model = new DepartmentForm.DepartmentTableModel(departmentList);
        return new JTable(model);
    }

    public void initFrame() {
        JFrame frame = new JFrame("Department");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocation(500, 100);
        JTable table = displayTable();
        table.setBounds(30, 40, 1000, 500);
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        frame.pack();
        frame.setVisible(true);
    }
}
