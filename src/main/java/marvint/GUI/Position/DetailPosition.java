package marvint.GUI.Position;

import marvint.GUI.Employee.EmployeeTableModel;
import marvint.domain.Department;
import marvint.domain.Employee;
import marvint.domain.Position;
import marvint.сontroller.EmployeeController;
import marvint.сontroller.PositionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

@Component
public class DetailPosition {
    @Autowired
    EmployeeController employeeController;

    @Autowired
    PositionController positionController;

    @Autowired
            PositionForm positionForm;

    JFrame frame;
    private JPanel panel1;
    private JTable table;
    JLabel code;
    JLabel title;

    public JTable displayTable(Position position) {
      //  Position position = positionController.getPosition(Long.parseLong(code.getText()));
        List<Employee> employeeList = employeeController.listEmployeeByPosition(position);
        TableModel model = new EmployeeTableModel(employeeList);
        return new JTable(model);
    }

    public void updatePosition(Position position) {
        List<Employee> employeeList = employeeController.listEmployeeByPosition(position);
        TableModel model = new EmployeeTableModel(employeeList);
        table.setModel(model);
        table.revalidate();
    }

    public JButton deleteButton(){
        JButton buttonDelete = new JButton();
        buttonDelete.setText("Удалить позицию");
        buttonDelete.setLocation(50,550);
        buttonDelete.setSize(100,50);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    var userChoose = JOptionPane.showConfirmDialog(frame, "Вы действительно хотите удалить должность?", "Удалить должность?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    switch (userChoose) {
                        case JOptionPane.YES_OPTION:
                            positionController.deletePosition(Long.parseLong(code.getText()));
                            break;
                        case JOptionPane.NO_OPTION:
                            break;
                        case JOptionPane.CLOSED_OPTION:
                            System.out.println("Закрыто");
                            break;
                        default:
                            throw new RuntimeException("как ты сюда попал? Дверь запили"); }
                            positionForm.updateTable();
                    frame.dispose();
                     }
        });
        return buttonDelete;
    }

    public void detailPosition(Position position) {
        code.setText(position.getCode().toString());
        title.setText(position.getTitle());
    }

    public void initFrame(Position position) {
        frame = new JFrame("Должность");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocation(500, 100);
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        JPanel labels = new JPanel(new GridLayout(2,2));
        code = new JLabel();
        title = new JLabel();
        detailPosition(position);
        code.setHorizontalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        labels.add(code);
        labels.add(title);
        panel1.add(labels, BorderLayout.NORTH);
        table = displayTable(position);
        JScrollPane sp = new JScrollPane(table);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(sp, BorderLayout.CENTER);
        //JButton editButton = editButton();
        JButton deleteButton = deleteButton();
        JPanel buttons = new JPanel(new GridLayout(2,3));
       // buttons.add(editButton);
        buttons.add(deleteButton);
        panel1.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);
        frame.invalidate();
        panel1.invalidate();
    }
}
