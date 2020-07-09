package marvint.GUI.Department;

import lombok.SneakyThrows;
import marvint.GUI.MainForm;
import marvint.domain.Department;
import marvint.domain.DepartmentFilter;
import marvint.domain.Otdel;
import marvint.сontroller.DepartmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FindDepartment {

    @Autowired
    private DepartmentController departmentController;
    @Autowired
    private DetailDepartment detailDepartment;
    @Autowired
    private MainForm mainForm;
    private JInternalFrame frame;
    private JPanel panel1;
    private JTable table;
    JTextField id;
    JTextField title;
    JTextField adress;


    public class DepartmentTableModel implements TableModel {
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
            switch (columnIndex) {
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
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        }

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
        if (otdels.size() == 0) {
            return "";
        } else {
            String s = "";
            for (var otdel : otdels) {
                s += otdel.getTitle();
                s += ", ";
            }
            return s.substring(0, s.length() - 2);
        }
    }


    public JTable displayTable() {
        List<Department> departmentList = departmentController.getDepartmentList();
        TableModel model = new DepartmentTableModel(departmentList);
        return new JTable(model);
    }

    public void updateDepartment() {
        List<Department> departmentList = departmentController.getDepartmentList();
        TableModel model = new DepartmentTableModel(departmentList);
        table.setModel(model);
        table.revalidate();
    }

    public JButton findButton() {
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Поиск");
        buttonAdd.setLocation(50, 550);
        buttonAdd.setSize(100, 50);
        buttonAdd.addActionListener(e -> {
            DepartmentFilter departmentFilter = new DepartmentFilter();
            if (!id.getText().equals("")) {
                departmentFilter.setId(Long.parseLong(id.getText()));
            }
            departmentFilter.setTitle(title.getText());
            departmentFilter.setAddress(adress.getText());
            List<Department> departmentList = departmentController.listDepartmentByFilter(departmentFilter);
            TableModel model = new DepartmentTableModel(departmentList);
            table.setModel(model);
            table.revalidate();
        });
        return buttonAdd;
    }

    public JPanel findPanel() {
        JPanel panelFind = new JPanel();
        panelFind.setLayout(new GridLayout(1, 6));
        panelFind.add(new JLabel("id"));
        id = new JTextField();
        panelFind.add(id);
        panelFind.add(new JLabel("Название"));
        title = new JTextField();
        panelFind.add(title);
        panelFind.add(new JLabel("Адрес"));
        adress = new JTextField();
        panelFind.add(adress);
        return panelFind;
    }

    public void initFrame() {
        frame = new JInternalFrame("Поиск подрахделения", true, true, true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(findPanel(), BorderLayout.NORTH);
        table = displayTable();
        table.addMouseListener(setMouseAddaptTable());
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane sp = new JScrollPane(table);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(sp, BorderLayout.CENTER);
        JButton buttonFind = findButton();
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.add(buttonFind);
        panel1.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel1);
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

    public MouseAdapter setMouseAddaptTable() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @SneakyThrows
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    var value = table.getValueAt(table.getSelectedRow(), 0);
                    Department department = departmentController.getDepartmentById(Long.parseLong(value.toString()));
                    detailDepartment.detailDepartment(department);
                    detailDepartment.init();
                }
            }
        };
        return mouseAdapter;
    }
}
