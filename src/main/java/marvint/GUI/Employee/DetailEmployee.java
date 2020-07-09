package marvint.GUI.Employee;

import lombok.SneakyThrows;
import marvint.GUI.Position.PositionForm;
import marvint.domain.Employee;
import marvint.domain.Mail;
import marvint.domain.Phone;
import marvint.utils.EmailUtil;
import marvint.utils.PhoneUtil;
import marvint.сontroller.EmployeeController;
import marvint.сontroller.PositionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

@Component
public class DetailEmployee {

    @Autowired
    EmployeeController employeeController;

    @Autowired
    PositionController positionController;

    @Autowired
    PositionForm positionForm;

    @Autowired
    EditEmployee editEmployee;

    //   JFrame frame;
    JInternalFrame frame;
    private JPanel panel1;
    private JTextField date;
    private JTextField position;
    private JTable table;
    JTextField login;
    JTextField fio;
    JLabel fioL;
    JTextField department;
    JTextField otdel;
    JTextField mail;
    JTextField phone;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel photo;
    private JLabel фотоСотрудникаLabel;


    public DetailEmployee() {
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = employeeController.getEmployeeByLogin(login.getText());
                editEmployee.initFrame();
                editEmployee.viewEmployee(employee);
                frame.setVisible(false);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                var userChoose = JOptionPane.showConfirmDialog(frame, "Вы действительно хотите удалить сотрудника?", "Удалить сотрудника?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                switch (userChoose) {
                    case JOptionPane.YES_OPTION:
                        employeeController.deleteEmployeeById(login.getText());
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        System.out.println("Закрыто");
                        break;
                    default:
                        throw new RuntimeException("как ты сюда попал? Дверь запили");
                }
            }
        });
    }


    public void detailEmployee(Employee employee) {
        String fioStr = employee.getLastName() + " " + employee.getFirstName() + " " + employee.getSecondName();
        fio.setText(fioStr);
        if (employee.getImage() != null) {
            Image img = new ImageIcon(employee.getImage()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            ImageIcon photos = new ImageIcon(img);
            photo.setIcon(photos);
        }
        position.setText(employee.getPosition().getTitle());
        login.setText(employee.getLogin());
        date.setText(employee.getDate().toString());
        department.setText(employee.getDepartment().getTitle());
        if (employee.getOtdel() != null) {
            otdel.setText(employee.getOtdel().getTitle());
        }
        mail.setText(EmailUtil.joinMail(employee.getMail()));
        phone.setText(PhoneUtil.joinPhone(employee.getPhone()));
    }

    public JInternalFrame initFrame(Employee employee) throws UnsupportedLookAndFeelException {
        frame = new JInternalFrame("Сотрудник", true, true, true, true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.getContentPane().add(panel1);
        detailEmployee(employee);
        frame.invalidate();
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

}
