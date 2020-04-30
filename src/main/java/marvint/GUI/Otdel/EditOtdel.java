package marvint.GUI.Otdel;

import marvint.GUI.Department.DepartmentForm;
import marvint.domain.Department;
import marvint.domain.Otdel;
import marvint.сontroller.DepartmentController;
import marvint.сontroller.OtdelController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.criterion.Restrictions.or;

@Component
public class EditOtdel {

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
    private JComboBox comboBox2;
    private JCheckBox checkBox1;
    private int indexDep;

    @Autowired
    OtdelController otdelController;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    DepartmentForm departmentForm;

    public EditOtdel() {
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Department> list = departmentController.getDepartmentList();
                int i= comboBox1.getSelectedIndex();
                Department department = list.get(i);
                Otdel otdel = new Otdel();
                otdel.setId(Long.parseLong(idText.getText()));
               if (checkBox1.isSelected()== true) { otdel.setTitle(textField1.getText()); }
               else otdel.setTitle(comboBox2.getSelectedItem().toString());
                otdel.setAddress(textField2.getText());
                otdel.setDepartment(department);
                otdelController.saveOtdel(otdel);
                departmentForm.editTree();
                frame.setVisible(false);
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Otdel> list = otdelController.getOtdelList();
                int ibdex = comboBox2.getSelectedIndex();
                Otdel otdel = list.get(ibdex);
                idText.setText(otdel.getId().toString());
                textField1.setText(otdel.getTitle());
                textField2.setText(otdel.getAddress());
                comboBox1.setSelectedItem(otdel.getDepartment().getTitle());
                indexDep = comboBox1.getSelectedIndex();
            }
        });
    }
    public void setComboBox1(){
        List<Department> list = departmentController.getDepartmentList();
        List<String> titles = new ArrayList<>();
        for (int i=0; i<list.size();i++) {
            Department department = list.get(i);
            titles.add(department.getTitle());
        }
        comboBox1.setModel(new DefaultComboBoxModel(titles.toArray()));
    }

    public void setComboBox2(){
        List<Otdel> list = otdelController.getOtdelList();
        List<String> titles = new ArrayList<>();
        for (int i=0; i<list.size();i++) {
            Otdel otdel = list.get(i);
            titles.add(otdel.getTitle());
        }
        comboBox2.setModel(new DefaultComboBoxModel(titles.toArray()));
    }

    public void initFrame() {
        frame = new JFrame("AddEmployee");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setComboBox1();
        setComboBox2();
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocation(500, 100);
        frame.setVisible(true);
    }
}
