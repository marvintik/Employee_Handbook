package marvint.GUI;

import marvint.сontroller.DepartmentController;
import marvint.сontroller.EmployeeController;
import marvint.сontroller.OtdelController;
import marvint.сontroller.PositionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class StatisticForm {
    JInternalFrame frame;


    @Autowired
    MainForm mainForm;
    @Autowired
    DepartmentController departmentController;
    @Autowired
    OtdelController otdelController;
    @Autowired
    EmployeeController employeeController;
    @Autowired
    PositionController positionController;

    public void initFrame() {
        frame = new JInternalFrame("Общая информация", true, true, true, true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5,1));
        panel1.add(new JLabel("ВСЕГО:"));
        panel1.add(new JLabel(String.format("Департаменты: всего %s департаментов", departmentController.count().toString())));
        panel1.add(new JLabel(String.format("Отделы: всего %s отделов", otdelController.count().toString())));
        panel1.add(new JLabel(String.format("Должности: всего %s должностей", positionController.count().toString())));
        panel1.add(new JLabel(String.format("Сотрудники: всего %s сотрудников", employeeController.count().toString())));
        frame.getContentPane().add(panel1);
        mainForm.pane.add(frame);
        frame.pack();
        frame.setVisible(true);
    }
}
