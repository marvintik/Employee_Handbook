package marvint.GUI.Department;

import marvint.GUI.Otdel.AddOtdel;
import marvint.GUI.Otdel.DeleteOtdel;
import marvint.GUI.Otdel.EditOtdel;
import marvint.domain.Department;
import marvint.domain.Otdel;
import marvint.сontroller.DepartmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeModelListener;
import javax.swing.table.TableModel;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DepartmentForm {
    @Autowired
    private DepartmentController departmentController;

    @Autowired
    private AddDepartment addDepartment;

    @Autowired
    private EditDepartmentt editDepartmentt;

    @Autowired
    private DeleteDepartment deleteDepartment;

    @Autowired
    private AddOtdel addOtdel;

    @Autowired
    private EditOtdel editOtdel;

    @Autowired
    private DeleteOtdel deleteOtdel;


    private JPanel panel1;
    private JTable table;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode departments;


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
        if (otdels.size() == 0){ return "";}
        else {
        String s = "";
        for (var otdel : otdels) {
            s += otdel.getTitle();
            s += ", ";
        }
        return s.substring(0, s.length() - 2);}
    }

    public JTable displayTable() {
        List<Department> departmentList = departmentController.getDepartmentList();
        TableModel model = new DepartmentForm.DepartmentTableModel(departmentList);
        return new JTable(model);
    }

    public void updateDepartment(){
        List<Department> departmentList = departmentController.getDepartmentList();
        TableModel model = new DepartmentForm.DepartmentTableModel(departmentList);
        table.setModel(model);
        table.revalidate();
    }

    public JButton addButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Добавить департамент");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDepartment.initFrame();
            }
        });
        return buttonAdd;
    }
    public JButton editButton(){
        JButton buttonEdit = new JButton();
        buttonEdit.setText("Изменить департамент");
        buttonEdit.setLocation(50,550);
        buttonEdit.setSize(100,50);
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDepartmentt.initFrame();
            }
        });
        return buttonEdit;
    }
    public JButton deleteButton(){
        JButton buttonDelete = new JButton();
        buttonDelete.setText("Удалить департамент");
        buttonDelete.setLocation(50,550);
        buttonDelete.setSize(100,50);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDepartment.initFrame();
            }
        });
        return buttonDelete;
    }
    public JButton addOtdelButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Добавить отдел");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               addOtdel.initFrame();
            }
        });
        return buttonAdd;
    }
    public JButton editOtdelButton(){
        JButton buttonEdit = new JButton();
        buttonEdit.setText("Изменить отдел");
        buttonEdit.setLocation(50,550);
        buttonEdit.setSize(100,50);
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    editOtdel.initFrame();
            }
        });
        return buttonEdit;
    }
    public JButton deleteOtdelButton(){
        JButton buttonDelete = new JButton();
        buttonDelete.setText("Удалить отдел");
        buttonDelete.setLocation(50,550);
        buttonDelete.setSize(100,50);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  deleteOtdel.initFrame();
            }
        });
        return buttonDelete;
    }

    public JTree createJTree(){
        String rootes = "Список департаментов и подразделений";
        root = new DefaultMutableTreeNode(rootes,true);
            List<Department> list = departmentController.getDepartmentList();
            for (int i=0; i<list.size();i++){
                Department department = list.get(i);
                departments = new DefaultMutableTreeNode(department.getTitle(),true);
               List<Otdel> otdelist = department.getOtdel();
                for (int j=0; j<otdelist.size();j++){
                    Otdel otdel = otdelist.get(j);
                   DefaultMutableTreeNode otd = new DefaultMutableTreeNode(otdel.getTitle(),false);
                    departments.add(otd); }
                root.add(departments);}
            treeModel = new DefaultTreeModel(root, true);
            tree = new JTree(treeModel);
            return tree;
    }

    public void updeteDepartmnentTree(String department) {
        departments = new DefaultMutableTreeNode(department, true);
        root.add(departments);
        tree.updateUI();
    }
    public void updeteOtdelstTree(String otdel, int i) {
        DefaultMutableTreeNode otdels = new DefaultMutableTreeNode(otdel, false);
        departments = (DefaultMutableTreeNode)(root.getChildAt(i));
        departments.add(otdels);
        treeModel = (DefaultTreeModel) tree.getModel();
        treeModel.reload();
        tree.setModel(treeModel);
        tree.updateUI();
    }



    public void initFrame() {
        JFrame frame = new JFrame("Department");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocation(500, 100);
        panel1.setLayout(new BorderLayout());
        tree = createJTree();
        JScrollPane sp = new JScrollPane(tree);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(sp, BorderLayout.CENTER);
        JButton buttonAdd = addButton();
        JButton editButton = editButton();
        JButton deleteButton = deleteButton();
        JButton editOtdelButton = editOtdelButton();
        JButton addOtdelButton = addOtdelButton();
        JButton deleteOtdelButton = deleteOtdelButton();
        JPanel buttons = new JPanel(new GridLayout(2,3));
        buttons.add(buttonAdd);
        buttons.add(editButton);
        buttons.add(deleteButton);
        buttons.add(addOtdelButton);
        buttons.add(editOtdelButton);
        buttons.add(deleteOtdelButton);
        panel1.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);
    }
}
