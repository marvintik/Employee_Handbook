package marvint.GUI.Otdel;

import lombok.SneakyThrows;
import marvint.GUI.Department.DepartmentForm;
import marvint.GUI.Employee.EmployeeTableModel;
import marvint.GUI.MainForm;
import marvint.domain.Department;
import marvint.domain.Employee;
import marvint.domain.Otdel;
import marvint.сontroller.OtdelController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

@Component
public class DetailOtdel {

    private JInternalFrame frame;
    private JPanel panel1;
    private JLabel title;
    private JLabel id;
    private JLabel adress;
    private JLabel department;
    private JButton button1;
    private JButton button2;
    private JTabbedPane tabbedPane1;

    @Autowired
    private OtdelController otdelController;

    @Autowired
    private DepartmentForm departmentForm;

    @Autowired
    private EditOtdel editOtdel;

    @Autowired
    MainForm mainForm;


    public DetailOtdel() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editOtdel.setLabels(otdelController.getOtdelById(Long.parseLong(id.getText())));
                editOtdel.initFrame();
                tabbedPane1.remove(1);
                frame.setVisible(false);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    var userChoose = JOptionPane.showConfirmDialog(frame, "Вы действительно хотите удалить отдел?", "Удалить отдел?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    switch (userChoose) {
                        case JOptionPane.YES_OPTION:
                            otdelController.deleteOtdel(Long.parseLong(id.getText()));
                            break;
                        case JOptionPane.NO_OPTION:
                            break;
                        case JOptionPane.CLOSED_OPTION:
                            System.out.println("Закрыто");
                            break;
                        default:
                            throw new RuntimeException("как ты сюда попал? Дверь запили");
                    }
                    tabbedPane1.remove(1);
                    departmentForm.editTree();
                    frame.setVisible(false);
            }
        });
    }

    public void detailOtdel(Otdel otdel){
        id.setText(otdel.getId().toString());
        title.setText(otdel.getTitle());
        adress.setText(otdel.getAddress());
        department.setText(otdel.getDepartment().getTitle());
    }
    @SneakyThrows
    public JTabbedPane createTabbedPane() {
        JPanel panelEmployee = new JPanel();
        Otdel otdel = otdelController.getOtdelById(Long.parseLong(id.getText()));
        List<Employee> employees = otdel.getEmployees();
        TableModel model = new EmployeeTableModel(employees);
        JTable employeeTable = new JTable(model);
        JScrollPane sp = new JScrollPane(employeeTable);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelEmployee.add(sp, BorderLayout.CENTER);
        tabbedPane1.addTab("Сотрудники", panelEmployee);
        return tabbedPane1;
    }


    public void init(){
        frame = new JInternalFrame("Отдел", true,true,true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createTabbedPane();
        list();
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

    public void list(){
        frame.addInternalFrameListener(new InternalFrameListener() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                System.out.println(tabbedPane1.getTabCount());
                tabbedPane1.remove(1);
                System.out.println("Success");
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
