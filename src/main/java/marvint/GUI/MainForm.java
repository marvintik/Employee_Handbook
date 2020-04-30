package marvint.GUI;

import marvint.GUI.Department.DepartmentForm;
import marvint.GUI.Employee.EmployeeForm;
import marvint.GUI.Position.PositionForm;
import marvint.сontroller.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JPanel panel1;
    private JButton button2;
    private JButton button3;
    private JButton positionButton;

    @Autowired
    private EmployeeController controller;

    @Autowired
    private EmployeeForm employeeForm;

    @Autowired
    private DepartmentForm departmentForm;

    @Autowired
    private PositionForm positionForm;

    private JFrame frame;

    public MainForm(EmployeeController controller) {
        this();
        this.controller = controller;
    }

    public MainForm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setText(label1.getText() + " Have a good day!");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeForm.initFrame();
             //   frame.setVisible(false);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                departmentForm.initFrame();
              //  frame.setVisible(false);
            }
        });
        positionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                positionForm.initFrame();
            }
        });
    }

    public void init(){
        frame = new JFrame("Hello!");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setLocation(500,100);
    }

}
