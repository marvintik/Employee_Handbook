package marvint.GUI.Employee;

import lombok.SneakyThrows;
import marvint.GUI.MainForm;
import marvint.domain.Employee;
import marvint.сontroller.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Component
public class EmployeeForm {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private AddEmployee addEmployee;

    @Autowired
    private EditEmployee editEmployee;

    @Autowired
    private DetailEmployee detailEmployee;

    @Autowired
    private MainForm mainForm;

    private JTable table;
//    private JFrame frame;
    JInternalFrame frame;

    public JTable displayTable() {
        List<Employee> employeeList = employeeController.getEmployeeList();
        TableModel model = new EmployeeTableModel(employeeList);
        return new JTable(model);
    }

    public JButton addButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Добавить сотрудника");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee.initFrame();
            }
        });
            return buttonAdd;
    }
    public JButton editButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Изменить сотрудника");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = table.getSelectedRow();
                if (sel<0){JOptionPane.showConfirmDialog(frame, "Выберите сотрудника для изменения", "Изменить", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);}
                else {
                    var value = table.getValueAt(sel,0);
                    System.out.println(value);
                   Employee employee = employeeController.getEmployeeByLogin(value.toString());
                    editEmployee.initFrame();
                    editEmployee.viewEmployee(employee);

            }
        }});
        return buttonAdd;
    }

    public JButton deleteButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Удалить сотрудника");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                var sel = table.getSelectedRow();
                if (sel<0){JOptionPane.showConfirmDialog(frame, "Выберите сотрудника для удаления", "Удалить", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);}
                else {
                var value = table.getValueAt(sel,0);
                System.out.println(value);
                var userChoose = JOptionPane.showConfirmDialog(frame, "Вы действительно хотите удалить сотрудника?", "Удалить сотрудника?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                switch (userChoose) {
                    case JOptionPane.YES_OPTION:
                        employeeController.deleteEmployeeById(value.toString());
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
        return buttonAdd;
    }

    public JInternalFrame initFrame() {
        frame = new JInternalFrame("Все сотрудники", true, true, true, true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   //     frame.setSize(800, 800);
    //    frame.setLocation(500, 100);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        table = displayTable();
        table.addMouseListener(setMouseAddaptTable());
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(table);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(sp, BorderLayout.CENTER);
        JButton buttonAdd = addButton();
        JButton editButton = editButton();
        JButton deleteButton = deleteButton();
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.add(buttonAdd);
        buttons.add(editButton);
        buttons.add(deleteButton);
        panel1.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    public void updateEmployee() {
        List<Employee> employeeList = employeeController.getEmployeeList();
        TableModel model = new EmployeeTableModel(employeeList);
        table.setModel(model);
        table.revalidate();
    }

    public MouseAdapter setMouseAddaptTable() {
        MouseAdapter mouseAdapter= new MouseAdapter() {
            @SneakyThrows
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    var value = table.getValueAt(table.getSelectedRow(),0);
                   Employee employee = employeeController.getEmployee(value.toString());

                   mainForm.pane.add(detailEmployee.initFrame(employee));
                }}
        };
        return mouseAdapter;
    }
}

