package marvint.GUI.Department;

import marvint.domain.Department;
import marvint.domain.Otdel;
import marvint.domain.Position;
import marvint.сontroller.DepartmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddDepartment {

    private JFrame frame;
    private JPanel panel1;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JLabel adressLabel;
    private JButton Button;
    private JTextField idText;
    private JTextField textField1;
    private JTextField textField2;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    DepartmentForm departmentForm;

    public AddDepartment() {
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Department department = new Department();
                department.setId(Long.parseLong(idText.getText()));
                department.setTitle(textField1.getText());
                department.setAddress(textField2.getText());
                departmentController.saveDepartment(department);
                departmentForm.updeteTree(department.getTitle());
                departmentForm.updateDepartment();
                frame.setVisible(false);
            }
        });
    }

    public void initFrame() {
        frame = new JFrame("AddEmployee");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocation(500, 100);
        frame.setVisible(true);
    }

}
