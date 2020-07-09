package marvint.GUI.Employee;

import marvint.domain.Employee;
import marvint.domain.Mail;
import marvint.domain.Phone;
import marvint.utils.EmailUtil;
import marvint.utils.PhoneUtil;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return 8;
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
                return "Телефон";
            case 7:
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
                return String.class;
            case 4:
                return LocalDate.class;
            case 6:
            case 7:
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
      //  ImageIcon imageIcon=  new ImageIcon(employee.getImage());
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
                return PhoneUtil.joinPhone(employee.getPhone());
            case 7:
                return EmailUtil.joinMail(employee.getMail());
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


