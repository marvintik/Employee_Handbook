package marvint.GUI.Department;

import marvint.domain.Department;
import marvint.сontroller.DepartmentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Component
public class EditDepartmentt {
    private JFrame frame;
    private JPanel panel1;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JLabel adressLabel;
    private JButton Button;
    private JTextField idText;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    DepartmentForm departmentForm;

    public EditDepartmentt() {
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Department department = new Department();
                department.setId(Long.parseLong(textField1.getText()));
                department.setTitle(comboBox1.getSelectedItem().toString());
                department.setAddress(textField2.getText());
                departmentController.saveDepartment(department);
                departmentForm.updateDepartment();
                frame.setVisible(false);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Department> list = departmentController.getDepartmentList();
                int ibdex = comboBox1.getSelectedIndex();
                Department department = list.get(ibdex);
                textField1.setText(department.getId().toString());
                textField2.setText(department.getAddress());
            }
        });
    }

    public void initFrame() {
        frame = new JFrame("AddEmployee");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        List<Department> list = departmentController.getDepartmentList();
        List<String> titles = new ArrayList<>();
        for (int i=0; i<list.size();i++) {
            Department department = list.get(i);
            titles.add(department.getTitle());
        }
        comboBox1.setModel(new DefaultComboBoxModel(titles.toArray()));
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocation(500, 100);
        frame.setVisible(true);
    }

}
