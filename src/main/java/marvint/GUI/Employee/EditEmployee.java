package marvint.GUI.Employee;

import lombok.SneakyThrows;
import marvint.GUI.MainForm;
import marvint.domain.*;
import marvint.utils.EmailUtil;
import marvint.utils.PhoneUtil;
import marvint.сontroller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Component
public class EditEmployee {

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

    @Autowired
    DepartmentController departmentController;

    @Autowired
    MainForm mainForm;

    private JLabel loginLabel;
    private JPanel panel1;
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
    private JComboBox position;
    private JComboBox otdel;
    private JLabel otdelLabel;
    private JButton button1;
    private JComboBox department;
    private JCheckBox checkbox1;
    private JTextField phone;
    private JTextField mail;
    private JInternalFrame frame;
    private com.github.lgooddatepicker.components.DatePicker date;
    private JLabel image;
    private byte[] resultingBytes;

    public EditEmployee() {
        addEmployeeButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = employeeController.getEmployee(loginText.getText());
                employee.setLastName(textField1.getText());
                employee.setFirstName(textField2.getText());
                employee.setSecondName(textField3.getText());
                employee.setDate(date.getDate());
                employee.setPosition(((Position) (position.getSelectedItem())));
                if (checkbox1.isSelected()) {
                    Otdel otdelEntity = otdelController.getOtdelByTitle(Objects.requireNonNull(otdel.getSelectedItem()).toString());
                    employee.setOtdel(otdelEntity);
                }
                Department departmentEntity = departmentController.getDepartmentByTitle(department.getSelectedItem().toString());
                employee.setDepartment(departmentEntity);
                var error = false;
                if (resultingBytes != null) {
                    employee.setImage(resultingBytes);
                }
                if (!EmailUtil.joinMail(employee.getMail()).equals(mail.getText())) {
                    mailController.deleteByEmployee(employee);
                    List<Mail> mailList = EmailUtil.valAndSaveMails(employee, mail.getText());
                    if (mailList.isEmpty()) {
                        JOptionPane.showConfirmDialog(frame, "Введите корректный e-mail(ы)", "Mail", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
                    employee.setMail(mailList);
                }
                if (!PhoneUtil.joinPhone(employee.getPhone()).equals(phone.getText())) {
                    phoneController.deletePhoneByEmployee(employee);
                    List<Phone> phoneList = PhoneUtil.valAndSavePhones(employee, phone.getText());
                    if (phoneList.isEmpty()) {
                        JOptionPane.showConfirmDialog(frame, "Введите корректный телефон(ы)", "Телефон", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
                    employee.setPhone(phoneList);
                }

                if (!error) {
                    employeeController.saveEmployee(employee);
                    employeeForm.updateEmployee();
                    resultingBytes = null;
                    frame.setVisible(false);
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    var allByte = new FileInputStream(file).readAllBytes();
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                    image.setIcon(imageIcon);

                    BufferedImage bufferedImage = ImageIO.read(file);
                    ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, "png", byteArrayOut);
                    resultingBytes = byteArrayOut.toByteArray();
                }
            }
        });
    }

    public void viewEmployee(Employee employee) {
        loginText.setText(employee.getLogin());
        textField1.setText(employee.getLastName());
        textField2.setText(employee.getFirstName());
        textField3.setText(employee.getSecondName());
        date.setDate(employee.getDate());
        if (employee.getImage() != null) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(employee.getImage()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            image.setIcon(imageIcon);
        }
        phone.setText(PhoneUtil.joinPhone(employee.getPhone()));
        mail.setText(EmailUtil.joinMail(employee.getMail()));
        if (employee.getOtdel() == null) {
            otdel.setEnabled(false);
        } else otdel.setSelectedItem(employee.getOtdel().getTitle());
        position.setSelectedItem(employee.getPosition());
        department.setSelectedItem(employee.getDepartment().getTitle());

    }


    public void setPosition(JComboBox position) {
        this.position = position;
        List<Position> positions = positionController.getPositionList();
        position.setModel(new DefaultComboBoxModel(positions.toArray()));
    }

    public void setOtdel(JComboBox otdel) {
        this.otdel = otdel;
        List<Otdel> otdels = otdelController.getOtdelList();
        List<String> titleOtdels = new ArrayList<String>();
        for (int i = 0; i < otdels.size(); i++) {
            Otdel otdelEntity = otdels.get(i);
            titleOtdels.add(otdelEntity.getTitle());
        }
        otdel.setModel(new DefaultComboBoxModel(titleOtdels.toArray()));
    }

    public void setDepartment(JComboBox department) {
        this.department = department;
        List<Department> departments = departmentController.getDepartmentList();
        List<String> titleDepartments = new ArrayList<String>();
        for (int i = 0; i < departments.size(); i++) {
            Department departmentEntity = departments.get(i);
            titleDepartments.add(departmentEntity.getTitle());
        }
        department.setModel(new DefaultComboBoxModel(titleDepartments.toArray()));
    }

    public void initFrame() {
        frame = new JInternalFrame("Изменить сотрудника", true, true, true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setOtdel(otdel);
        setPosition(position);
        setDepartment(department);
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

}
