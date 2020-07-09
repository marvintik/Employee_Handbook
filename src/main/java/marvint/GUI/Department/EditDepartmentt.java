package marvint.GUI.Department;

import lombok.SneakyThrows;
import marvint.GUI.MainForm;
import marvint.domain.Department;
import marvint.domain.Position;
import marvint.сontroller.DepartmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Component
public class EditDepartmentt {
    private JInternalFrame frame;
    private JPanel panel1;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JLabel adressLabel;
    private JButton Button;
    private JTextField idText;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton findButton;
    private JComboBox comboBox1;

    @Autowired
    private MainForm mainForm;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    DepartmentForm departmentForm;

    public EditDepartmentt() {
        findButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                Department department = departmentController.getDepartmentById(Long.parseLong(idText.getText()));
                textField2.setText(department.getAddress());
                textField3.setText(department.getTitle());
            }
        });
        Button.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                Department department = departmentController.getDepartmentById(Long.parseLong(idText.getText()));
                department.setAddress(textField2.getText());
                department.setTitle(textField3.getText());
                departmentController.saveDepartment(department);
                departmentForm.editTree();
                frame.setVisible(false);
            }
        });
    }

    public void setLabels(Department department) {
        idText.setText(department.getId().toString());
        idText.setEnabled(false);
        findButton.setVisible(false);
        textField2.setText(department.getAddress());
        textField3.setText(department.getTitle());
    }

    public void defaultEdit() {
        // TODO: 08.07.2020 delete
        idText.setText("");
        textField2.setText("");
        textField3.setText("");
        idText.setEnabled(true);
        findButton.setVisible(true);
    }

    public void initFrame() {
        frame = new JInternalFrame("Изменить департамент", true, true, true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

}
