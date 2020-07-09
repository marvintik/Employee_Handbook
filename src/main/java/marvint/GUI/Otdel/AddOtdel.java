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

@Component
public class AddOtdel {

    private JInternalFrame frame;
    private JPanel panel1;
    private JLabel titleLabel;
    private JLabel adressLabel;
    private JButton Button;
    private JTextField idText;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;

    @Autowired
    OtdelController otdelController;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    DepartmentForm departmentForm;

    @Autowired
    MainForm mainForm;

    public AddOtdel() {
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Department> list = departmentController.getDepartmentList();
                int i= comboBox1.getSelectedIndex();
                Department department = list.get(i);
              if (otdelController.getOtdelByTitleAndDepantment(textField1.getText(), department)!=null) {
                  JOptionPane.showConfirmDialog(frame, "Такой отдел уже сузествует в Департаменте", "Ошибка", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
              } else {
                Otdel otdel = new Otdel();
                otdel.setTitle(textField1.getText());
                otdel.setAddress(textField2.getText());
                otdel.setDepartment(department);
                otdelController.saveOtdel(otdel);
                departmentForm.editTree();
                frame.setVisible(false);}
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

    public void initFrame() {
        frame = new JInternalFrame("Добавить отдел", true,true,true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setComboBox1();
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }
}
