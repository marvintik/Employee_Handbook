package marvint.GUI;

import marvint.Application;
import marvint.domain.Employee;
import marvint.domain.Mail;
import marvint.domain.Phone;
import marvint.сontroller.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class EmployeeForm {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private AddEmployee addEmployee;

    @Autowired
    private  EditEmployee editEmployee;

    @Autowired
    private DeleteEmployee deleteEmployee;

    private JPanel panel1;
    private JTable table;


    public class EmployeeTableModel implements TableModel {
        private List<Employee> employeeList;
        private Set<TableModelListener> listeners = new HashSet<>();


        public EmployeeTableModel(List<Employee> employeeList) {
            this.employeeList = employeeList;
        }

        @Override
        public int getRowCount() {
            return employeeList.size();
        }

        @Override
        public int getColumnCount() {
            return 9;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Логин";
                case 1:
                    return "Фамилия";
                case 2:
                    return "Имя";
                case 3:
                    return "Отчество";
                case 4:
                    return "Дата рождения";
                case 5:
                    return "Должность";
                case 6:
                    return "Фото";
                case 7:
                    return "Теленфон";
                case 8:
                    return "E-mail";
            }
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            var mockEmployee = new Employee();
            switch (columnIndex) {
                case 0:
                case 5:
                case 1:
                case 2:
                case 3:
                case 6:
                    return String.class;
                case 4:
                    return LocalDate.class;
                case 7:
                case 8:
                    return List.class;
            }
            throw new RuntimeException();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Employee employee = employeeList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return employee.getLogin();
                case 1:
                    return employee.getLastName();
                case 2:
                    return employee.getFirstName();
                case 3:
                    return employee.getSecondName();
                case 4:
                    return employee.getDate();
                case 5:
                    return employee.getPosition().getTitle();
                case 6:
                    return employee.getPhoto();
                case 7:
                    return joinPhone(employee.getPhone());
                case 8:
                    return joinMail(employee.getMail());
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }

        @Override
        public void addTableModelListener(TableModelListener l) {
            listeners.add(l);
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
            listeners.remove(l);
        }
    }

    public JTable displayTable() {
        List<Employee> employeeList = employeeController.getEmployeeList();
        TableModel model = new EmployeeTableModel(employeeList);
        return new JTable(model);
    }

    public JButton addButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Добавить сотрудника");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee.initFrame();
            }
        });
            return buttonAdd;
    }
    public JButton editButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Изменить сотрудника");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editEmployee.initFrame();
            }
        });
        return buttonAdd;
    }
    public JButton deleteButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Удалить сотрудника");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee.initFrame();
            }
        });
        return buttonAdd;
    }

    public void initFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocation(500, 100);
        panel1.setLayout(new BorderLayout());
        table = displayTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(sp, BorderLayout.CENTER);
        JButton buttonAdd = addButton();
        JButton editButton = editButton();
        JButton deleteButton = deleteButton();
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.add(buttonAdd);
        buttons.add(editButton);
        buttons.add(deleteButton);
        panel1.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);
    }

    public void updateEmployee() {
        List<Employee> employeeList = employeeController.getEmployeeList();
        TableModel model = new EmployeeTableModel(employeeList);
        table.setModel(model);
        table.revalidate();
    }

    private String joinPhone(List<Phone> phones) {
        StringBuilder s = new StringBuilder();
        if (phones.size() == 0)
            return s.toString();
        for (var phone : phones) {
            s.append(phone.getPhone());
            s.append(", ");
        }
        return s.substring(0, s.length() - 2);
    }

    private String joinMail(List<Mail> mails) {
        StringBuilder s = new StringBuilder();
        if (mails.size() == 0)
            return s.toString();
        for (var mail : mails) {
            s.append(mail.getMail());
            s.append(", ");
        }
        return s.substring(0, s.length() - 2);
    }

}

