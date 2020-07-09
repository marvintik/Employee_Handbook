package marvint.GUI.Employee;

import lombok.SneakyThrows;
import marvint.GUI.MainForm;
import marvint.domain.Employee;
import marvint.domain.EmployeeFilter;
import marvint.сontroller.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Component
public class FindEmployee {
    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private DetailEmployee detailEmployee;

    @Autowired
    private MainForm mainForm;

    private JTextField login;
    private JTable table;
    JInternalFrame frame;
    private JTextField phone;
    private JTextField mail;
    private JTextField lastName;
    private JTextField firstName;
    private JTextField secondName;


    public JTable displayTable() {
        List<Employee> employeeList = employeeController.getEmployeeList();
        TableModel model = new EmployeeTableModel(employeeList);
        return new JTable(model);
    }

    public JButton findButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Поиск");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeFilter employeeFilter = new EmployeeFilter();
                employeeFilter.setLogin(login.getText());
                employeeFilter.setLastName(lastName.getText());
                employeeFilter.setFirstName(firstName.getText());
                employeeFilter.setSecondName(secondName.getText());
                employeeFilter.setMail(mail.getText());
                employeeFilter.setPhone(phone.getText());
                List<Employee> employeeList =  employeeController.listEmployeeByFilter(employeeFilter);
                TableModel model = new EmployeeTableModel(employeeList);
                table.setModel(model);
                table.revalidate();
            }
        });
        return buttonAdd;
    }

    public JPanel findPanel() {
        JPanel panelFind = new JPanel();
        panelFind.setLayout(new GridLayout(2,6));
        panelFind.add(new JLabel("login"));
        login = new JTextField();
        panelFind.add(login);
        panelFind.add(new JLabel("Фамилия"));
        lastName = new JTextField();
        panelFind.add(lastName);
        panelFind.add(new JLabel("Имя"));
        firstName = new JTextField();
        panelFind.add(firstName);
        panelFind.add(new JLabel("Отчество"));
        secondName = new JTextField();
        panelFind.add(secondName);
        panelFind.add(new JLabel("Телефон"));
        phone = new JTextField();
        panelFind.add(phone);
        panelFind.add(new JLabel("E-mail"));
        mail = new JTextField();
        panelFind.add(mail);
        return panelFind;
    }


    public void initFrame() {
        frame = new JInternalFrame("Все сотрудники", true, true, true, true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(findPanel(), BorderLayout.NORTH);
        table = displayTable();
        table.addMouseListener(setMouseAddaptTable());
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(table);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(sp, BorderLayout.CENTER);
      JButton buttonFind = findButton();
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
       buttons.add(buttonFind);
        panel1.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel1);
        mainForm.pane.add(frame);
        frame.pack();
        frame.setVisible(true);
    }

    public MouseAdapter setMouseAddaptTable() {
        MouseAdapter mouseAdapter= new MouseAdapter() {
            @SneakyThrows
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    var value = table.getValueAt(table.getSelectedRow(),0);
                    Employee employee = employeeController.getEmployee(value.toString());
                    mainForm.pane.add(detailEmployee.initFrame(employee));
                }}
        };
        return mouseAdapter;
    }
}
