package marvint.GUI.Department;

import lombok.SneakyThrows;
import marvint.GUI.Employee.EmployeeTableModel;
import marvint.GUI.MainForm;
import marvint.domain.Department;
import marvint.domain.Employee;
import marvint.domain.Otdel;
import marvint.сontroller.DepartmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class DetailDepartment {

    private JInternalFrame frame;
    private JPanel panel1;
    private JLabel title;
    private JLabel id;
    private JLabel adress;
    private JLabel department;
    private JButton button1;
    private JButton button2;
    private JTabbedPane tabbedPane1;
    private JPanel panelMain;

    @Autowired
    private DepartmentController departmentController;

    @Autowired
    private DepartmentForm departmentForm;

    @Autowired
    private EditDepartmentt editDepartmentt;
    @Autowired
    private MainForm mainForm;


    public DetailDepartment() {
        button2.addActionListener(e -> {
            var userChoose = JOptionPane.showConfirmDialog(frame, "Вы действительно хотите удалить департамент?", "Удалить департамент?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            switch (userChoose) {
                case JOptionPane.YES_OPTION:
                    departmentController.deleteDepartmentById(Long.parseLong(id.getText()));
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
                default:
                    throw new RuntimeException("как ты сюда попал? Дверь запили");
            }
            tabbedPane1.remove(2);
            tabbedPane1.remove(1);
            departmentForm.editTree();
            frame.setVisible(false);
        });
        button1.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                editDepartmentt.setLabels(departmentController.getDepartmentById(Long.parseLong(id.getText())));
                editDepartmentt.initFrame();
                tabbedPane1.remove(2);
                tabbedPane1.remove(1);
                frame.setVisible(false);
            }
        });
    }

    @SneakyThrows
    public JTabbedPane createTabbedPane() {
        JPanel panelOtdel = new JPanel();
        JPanel panelEmployee = new JPanel();
        Department department = departmentController.getDepartmentById(Long.parseLong(id.getText()));
        List<Otdel> otdels = department.getOtdel();
        List<Employee> employees = department.getEmployees();
        TableModel model = new EmployeeTableModel(employees);
        JTable employeeTable = new JTable(model);
        String[] titleOtdels = otdels.stream().map(Otdel::getTitle).toArray(String[]::new);
        JList<String> list1 = new JList<String>(titleOtdels);
        JScrollPane sp = new JScrollPane(employeeTable);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelEmployee.add(sp, BorderLayout.CENTER);
        panelOtdel.add(list1);
        tabbedPane1.addTab("Отделы", panelOtdel);
        tabbedPane1.addTab("Сотрудники", panelEmployee);
        return tabbedPane1;
    }

    public void detailDepartment(Department department) {
        id.setText(department.getId().toString());
        title.setText(department.getTitle());
        adress.setText(department.getAddress());
    }


    @SneakyThrows
    public void init() {
        frame = new JInternalFrame("Департамент", true, true, true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        id.setHorizontalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        adress.setHorizontalAlignment(JLabel.CENTER);
        createTabbedPane();
        list();
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

    public void list() {
        frame.addInternalFrameListener(new InternalFrameListener() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                tabbedPane1.remove(2);
                tabbedPane1.remove(1);
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {

            }
        });
    }
}