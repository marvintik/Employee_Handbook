package marvint.GUI.Otdel;

import marvint.GUI.Department.DepartmentForm;
import marvint.GUI.MainForm;
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

    private JInternalFrame frame;
    private JPanel panel1;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JLabel adressLabel;
    private JButton editButton;
    private JTextField idText;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JCheckBox checkBox1;
    private JButton findButton;
    private int indexDep;

    @Autowired
    OtdelController otdelController;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    DepartmentForm departmentForm;

    @Autowired
    MainForm mainForm;

    public EditOtdel() {
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Department> list = departmentController.getDepartmentList();
                int i= comboBox1.getSelectedIndex();
                Department department = list.get(i);
                Otdel otdel = otdelController.getOtdelById(Long.parseLong(idText.getText()));
                otdel.setTitle(textField1.getText());
                otdel.setAddress(textField2.getText());
                otdel.setDepartment(department);
                otdelController.saveOtdel(otdel);
                departmentForm.editTree();
                frame.setVisible(false);
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Otdel otdel = otdelController.getOtdelById(Long.parseLong(idText.getText()));
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

    public void setLabels(Otdel otdel){
        idText.setText(otdel.getId().toString());
        textField1.setText(otdel.getTitle());
        textField2.setText(otdel.getAddress());
        setComboBox1();
        comboBox1.setSelectedItem(otdel.getDepartment().getTitle());
        indexDep = comboBox1.getSelectedIndex();
        idText.setEnabled(false);
        findButton.setVisible(false);
    }

    public void defaultEdit(){
        idText.setText("");
        textField1.setText("");
        textField2.setText("");
        setComboBox1();
        idText.setEnabled(true);
        findButton.setVisible(true);
    }

    public void initFrame() {
        frame = new JInternalFrame("Изменить отдел", true,true,true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }
}
