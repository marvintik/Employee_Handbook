package marvint.GUI.Position;

import marvint.GUI.Department.DepartmentForm;
import marvint.domain.Department;
import marvint.domain.Position;
import marvint.сontroller.PositionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PositionForm {

    @Autowired
    PositionController positionController;

    @Autowired
    AddPosition addPosition;

    @Autowired
    EditPosition editPosition;

    private JPanel panel1;
    private JTable table;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode departments;
    private JFrame frame;

    public class PositionTableModel implements TableModel{
        private List<Position> positionList;
        private Set<TableModelListener> listeners = new HashSet<>();

        public PositionTableModel(List<Position> positionList) {this.positionList = positionList;}

        @Override
        public int getRowCount() {
            return positionList.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "ID";
                case 1:
                    return "Название";
            }
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            var mockDepartment = new Position();
            switch (columnIndex) {
                case 0:
                    return Long.class;
                case 1:
                    return String.class;
            }
            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Position position = positionList.get(rowIndex);
            switch (columnIndex)
            {
                case 0:
                    return position.getCode();
                case 1:
                    return position.getTitle();
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }

        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }
    }

    public JTable displayTable() {
        List<Position> positionList = positionController.getPositionList();
        TableModel model = new PositionTableModel(positionList);
        return new JTable(model);
    }

    public void updateTable() {
        List<Position> positionList = positionController.getPositionList();
        TableModel model = new PositionTableModel(positionList);
        table.setModel(model);
        table.revalidate();
    }

    public JButton addButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Добавить позицию");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPosition.initFrame();
            }
        });
        return buttonAdd;
    }
    public JButton editButton(){
        JButton buttonEdit = new JButton();
        buttonEdit.setText("Изменить позицию");
        buttonEdit.setLocation(50,550);
        buttonEdit.setSize(100,50);
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPosition.initFrame();
            }
        });
        return buttonEdit;
    }
    public JButton deleteButton(){
        JButton buttonDelete = new JButton();
        buttonDelete.setText("Удалить позицию");
        buttonDelete.setLocation(50,550);
        buttonDelete.setSize(100,50);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  deleteDepartment.initFrame();
            }
        });
        return buttonDelete;
    }

    public void initFrame() {
        frame = new JFrame("Department");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocation(500, 100);
        panel1.setLayout(new BorderLayout());
        table = displayTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(sp, BorderLayout.CENTER);
        JButton buttonAdd = addButton();
        JButton editButton = editButton();
        JButton deleteButton = deleteButton();
        JPanel buttons = new JPanel(new GridLayout(2,3));
        buttons.add(buttonAdd);
        buttons.add(editButton);
        buttons.add(deleteButton);
        panel1.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);
    }
}
