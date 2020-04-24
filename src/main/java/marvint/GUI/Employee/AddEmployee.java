package marvint.GUI.Employee;

import marvint.domain.*;
import marvint.сontroller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AddEmployee {

    @Autowired
    EmployeeForm employeeForm;

    @Autowired
    EmployeeController employeeController;

    @Autowired
    PositionController positionController;

    @Autowired
    OtdelController otdelController;

    @Autowired
    MailController mailController;

    @Autowired
    PhoneController phoneController;

    private JPanel panel1;
    private JLabel loginLabel;
    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private JLabel secondNameLabel;
    private JLabel dateLabel;
    private JTextField loginText;
    private JLabel photoLabel;
    private JLabel phoneLabel;
    private JLabel eMailLabel;
    private JButton addEmployeeButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel otdelLabel;
    private JFrame frame;

    public AddEmployee() {
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee newEmployee = new Employee();
                Mail newMail = new Mail();
                Phone newPhone = new Phone();
                Position position = new Position();
                newEmployee.setLogin(loginText.getText());
                newEmployee.setLastName(textField1.getText());
                newEmployee.setFirstName(textField2.getText());
                newEmployee.setSecondName(textField3.getText());
                newEmployee.setDate(LocalDate.parse(formattedTextField1.getText()));
                newEmployee.setPhoto(formattedTextField2.getText());
                newMail.setMail(textField5.getText());
                newPhone.setPhone(textField4.getText());
                newEmployee.setPosition(((Position)(comboBox1.getSelectedItem())));
                List<Otdel> otdels = otdelController.getOtdelList();
                Otdel otdel = otdels.get(comboBox2.getSelectedIndex());
                newEmployee.setOtdel(otdel);
                Random random = new Random();
                newMail.setId(random.nextLong());
                newPhone.setId(random.nextLong());
                newMail.setEmployee(newEmployee);
                newPhone.setEmployee(newEmployee);
                employeeController.saveEmployee(newEmployee);
                mailController.saveMail(newMail);
                phoneController.savePhone(newPhone);
                employeeForm.updateEmployee();
                frame.setVisible(false);
            }
        });
    }

    public void initFrame() {
        frame = new JFrame("AddEmployee");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        List<Position> positions = positionController.getPositionList();
        comboBox1.setModel(new DefaultComboBoxModel(positions.toArray()));
        List<Otdel> otdels = otdelController.getOtdelList();
        List<String> titleOtdels = new ArrayList<String>();
        for (int i=0; i<otdels.size();i++){
            Otdel otdel = otdels.get(i);
            titleOtdels.add(otdel.getTitle());
        }
        comboBox2.setModel(new DefaultComboBoxModel(titleOtdels.toArray()));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter dft = new DateFormatter(df);
        DefaultFormatterFactory dff = new DefaultFormatterFactory(dft);
        formattedTextField1.setFormatterFactory(dff);
        frame.setSize(500, 500);
        frame.setLocation(500, 100);
        frame.setVisible(true);
    }

}
