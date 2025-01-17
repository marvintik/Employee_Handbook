package marvint.GUI.Position;

import lombok.SneakyThrows;
import marvint.GUI.Department.DepartmentForm;
import marvint.GUI.MainForm;
import marvint.domain.Department;
import marvint.domain.Position;
import marvint.сontroller.PositionController;
import org.hibernate.engine.spi.SelfDirtinessTracker;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    MainForm mainForm;

    @Autowired
    EditPosition editPosition;

    @Autowired
    DetailPosition detailPosition;

    private JPanel panel1;
    private JTable table;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode departments;
    private JInternalFrame frame;

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
        table.addMouseListener(setMouseAddaptTable());
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
                var sel = table.getSelectedRow();
                if (sel<0){JOptionPane.showConfirmDialog(frame, "Выберите должность для удаления", "Удалить", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);}
                else {
               var value = table.getValueAt(sel,0);
                System.out.println(value);
                var userChoose = JOptionPane.showConfirmDialog(frame, "Вы действительно хотите удалить должность?", "Удалить должность?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                switch (userChoose) {
                    case JOptionPane.YES_OPTION:
                      positionController.deletePosition(Long.parseLong(value.toString()));
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        System.out.println("Закрыто");
                        break;
                    default:
                        throw new RuntimeException("как ты сюда попал? Дверь запили");
                }
                displayTable();
            }}
        });
        return buttonDelete;
    }

        public MouseAdapter setMouseAddaptTable() {
            MouseAdapter mouseAdapter= new MouseAdapter() {
                @SneakyThrows
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        var value = table.getValueAt(table.getSelectedRow(),0);
                        Position position = positionController.getPositionUI(Long.parseLong(value.toString()));
                        detailPosition.initFrame(position);
                    }}
            };
                return mouseAdapter;
        }

    public void initFrame() {
        frame = new JInternalFrame("Должности", true,true,true,true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocation(500, 100);
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        table = displayTable();
        table.addMouseListener(setMouseAddaptTable());
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
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }
}
