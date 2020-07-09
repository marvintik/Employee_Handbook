package marvint.GUI;

import marvint.GUI.Department.DepartmentForm;
import marvint.GUI.Department.FindDepartment;
import marvint.GUI.Employee.EmployeeForm;
import marvint.GUI.Employee.FindEmployee;
import marvint.GUI.Otdel.FindOtdel;
import marvint.GUI.Position.PositionForm;
import marvint.сontroller.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainForm {
    private JLabel label2;
    private JPanel panel1;
    public JDesktopPane pane;

    @Autowired
    private EmployeeController controller;

    @Autowired
    private EmployeeForm employeeForm;

    @Autowired
    private DepartmentForm departmentForm;

    @Autowired
    private PositionForm positionForm;

    @Autowired
    FindEmployee findEmployee;

    @Autowired
    FindDepartment findDepartment;

    @Autowired
    FindOtdel findOtdel;

    @Autowired
    StatisticForm statisticForm;

    public JFrame frame;

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Главная");
        JMenuItem exit = new JMenuItem(new ExitAction());
        JMenuItem main = new JMenuItem("Общая информация");
        main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                statisticForm.initFrame();
            }
        });
        mainMenu.add(main);
        mainMenu.addSeparator();
        mainMenu.add(exit);
        JMenu departmentMenu = new JMenu("Позразделения");
        JMenuItem allDep = new JMenuItem("Все подразделения");

        allDep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pane.add(departmentForm.initFrame());
            }
        });

        JMenuItem findDep = new JMenuItem("Поиск департамента");
        findDep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                findDepartment.initFrame();
            }
        });
        JMenuItem findOtd = new JMenuItem("Поиск отдела");
        findOtd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                findOtdel.initFrame();
            }
        });
        departmentMenu.add(allDep);
        departmentMenu.addSeparator();
        departmentMenu.add(findDep);
        departmentMenu.add(findOtd);
        JMenu employeeMenu = new JMenu("Сотрудники");
        JMenuItem allEmp = new JMenuItem("Все Сотрудники");

        allEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.add(employeeForm.initFrame());
            }
        });

        JMenuItem findEmp = new JMenuItem("Поиск Сотрудника");
        findEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findEmployee.initFrame();
            }
        });
        employeeMenu.add(allEmp);
        employeeMenu.addSeparator();
        employeeMenu.add(findEmp);

        JMenu positMenu = new JMenu("Должности");
        JMenuItem allPosit = new JMenuItem("Все должности");
        allPosit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                positionForm.initFrame();
            }
        });
        positMenu.add(allPosit);

        menuBar.add(mainMenu);
        menuBar.add(departmentMenu);
        menuBar.add(employeeMenu);
        menuBar.add(positMenu);
        // TODO: 09.07.2020 FONT 
        menuBar.setFont(new Font("Arial", Font.BOLD,20));
        return menuBar;
    }

    class ExitAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        ExitAction() {
            putValue(NAME, "Выход");
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public void init() {
        frame = new JFrame("Hello!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane = new JDesktopPane();
        frame.add(pane);
        frame.setJMenuBar(createMenu());
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}
