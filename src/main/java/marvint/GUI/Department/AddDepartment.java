package marvint.GUI.Department;

import lombok.SneakyThrows;
import marvint.GUI.MainForm;
import marvint.domain.Department;
import marvint.сontroller.DepartmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AddDepartment {

    private JInternalFrame frame;
    private JPanel panel1;
    private JLabel titleLabel;
    private JLabel adressLabel;
    private JButton Button;
    private JTextField idText;
    private JTextField title;
    private JTextField adress;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    DepartmentForm departmentForm;

    @Autowired
    MainForm mainForm;

    public AddDepartment() {
        Button.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                Department department = new Department();
                department.setTitle(title.getText());
                department.setAddress(adress.getText());
                departmentController.createDepartment(department);
                departmentForm.editTree();
                frame.setVisible(false);
            }
        });
    }

    public void initFrame() {
        frame = new JInternalFrame("Добавление департамента", true, true, true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

}
