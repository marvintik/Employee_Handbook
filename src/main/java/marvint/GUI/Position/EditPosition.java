package marvint.GUI.Position;

import marvint.GUI.MainForm;
import marvint.domain.Position;
import marvint.exceptions.EntityNotFoundException;
import marvint.сontroller.PositionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Component
public class EditPosition {

    private JInternalFrame frame;
    private JPanel panel1;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JButton button;
    private JTextField code;
    private JComboBox comboBox2;
    private JButton FindButton;

    @Autowired
    PositionController positionController;

    @Autowired
    PositionForm positionForm;

    @Autowired
    MainForm mainForm;

    public EditPosition() {
        FindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Position position = positionController.getPositionUI(Long.parseLong(code.getText()));
                    comboBox2.setSelectedItem(position.getTitle());
                } catch (EntityNotFoundException ex) {
                    // ex.printStackTrace();
                    JOptionPane.showConfirmDialog(frame, ex.getMessage(), "Не найдено", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }
              
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Position position = null;
                try {
                    position = positionController.getPositionUI(Long.parseLong(code.getText()));
                } catch (EntityNotFoundException ex) {
                    // ex.printStackTrace();
                    JOptionPane.showConfirmDialog(frame, ex.getMessage(), "Не найдено", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }
                position.setTitle(comboBox2.getSelectedItem().toString());
                positionController.savePosition(position);
                positionForm.updateTable();
                frame.setVisible(false);
            }
        });
    }

    public void initFrame() {
        frame = new JInternalFrame("Изменить должность", true, true, true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setComboBox2(comboBox2);
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

    public void setComboBox2(JComboBox comboBox2) {
        this.comboBox2 = comboBox2;
        List<Position> positionList = positionController.getPositionList();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < positionList.size(); i++) {
            Position position = positionList.get(i);
            titles.add(position.getTitle());
        }
        comboBox2.setModel(new DefaultComboBoxModel(titles.toArray()));
    }
}
