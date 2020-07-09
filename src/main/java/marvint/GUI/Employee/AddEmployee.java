package marvint.GUI.Employee;

import lombok.SneakyThrows;
import marvint.GUI.MainForm;
import marvint.domain.*;
import marvint.exceptions.EntityAlreadyExistException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    DepartmentController departmentController;

    @Autowired
    MainForm mainForm;

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
    private com.github.lgooddatepicker.components.DatePicker date;
    private JLabel image;
    private JTextField phone;
    private JTextField mail;
    private JComboBox position;
    private JComboBox otdel;
    private JLabel otdelLabel;
    private JComboBox department;
    private JButton button1;
    private JCheckBox checkbox1;
    private JInternalFrame frame;
    private byte[] resultingBytes;

    public AddEmployee() {
        addEmployeeButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee newEmployee = new Employee();
                newEmployee.setLogin(loginText.getText());
                newEmployee.setLastName(textField1.getText());
                newEmployee.setFirstName(textField2.getText());
                newEmployee.setSecondName(textField3.getText());
                newEmployee.setDate(date.getDate());
                newEmployee.setPhoto(image.getText());
                newEmployee.setPosition(((Position) (position.getSelectedItem())));
                if (checkbox1.isSelected()) {
                    Otdel otdelEntity = otdelController.getOtdelByTitle(Objects.requireNonNull(otdel.getSelectedItem()).toString());
                    newEmployee.setOtdel(otdelEntity);
                }
                Department departmentEntity = departmentController.getDepartmentByTitle(department.getSelectedItem().toString());
                newEmployee.setDepartment(departmentEntity);
                newEmployee.setImage(resultingBytes);
                List<Mail> mailList = EmailUtil.valAndSaveMails(newEmployee, mail.getText());
                List<Phone> phoneList = PhoneUtil.valAndSavePhones(newEmployee, phone.getText());
                var error = false;
                if (mailList.isEmpty()) {
                    JOptionPane.showConfirmDialog(frame, "Введите корректный e-mail(ы)", "Mail", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
                if (phoneList.isEmpty()) {
                    JOptionPane.showConfirmDialog(frame, "Введите корректный телефон(ы)", "Телефон", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
                newEmployee.setPhone(phoneList);
                newEmployee.setMail(mailList);
                if (!error) {
                    try {
                        employeeController.createEmployee(newEmployee);
                    } catch (EntityAlreadyExistException ex) {
                        JOptionPane.showConfirmDialog(frame, ex.getMessage(), "Ошибка", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    }
                    employeeForm.updateEmployee();
                    resultingBytes = null;
                    frame.setVisible(false);
                }
            }
        });
        department.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOtdel(otdel);
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
        checkbox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otdel.setEnabled(checkbox1.isSelected());
            }
        });
    }

    public void setPosition(JComboBox position) {
        this.position = position;
        List<Position> positions = positionController.getPositionList();
        position.setModel(new DefaultComboBoxModel(positions.toArray()));
    }

    public void setOtdel(JComboBox otdel) {
        this.otdel = otdel;
        Department departmentEntity = departmentController.getDepartmentByTitle(department.getSelectedItem().toString());
        List<Otdel> otdels = departmentEntity.getOtdel();
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
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            Department departmentEntity = departments.get(i);
            titles.add(departmentEntity.getTitle());
        }
        department.setModel(new DefaultComboBoxModel(titles.toArray()));
    }

    public void initFrame() {
        frame = new JInternalFrame("AddEmployee", true,true, true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPosition(position);
        setDepartment(department);
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

}
