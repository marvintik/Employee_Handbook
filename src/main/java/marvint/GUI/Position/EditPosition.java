package marvint.GUI.Position;

import marvint.domain.Position;
import marvint.сontroller.PositionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EditPosition {

    private JFrame frame;
    private JPanel panel1;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JButton Button;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    @Autowired
    PositionController positionController;

    @Autowired
    PositionForm positionForm;

    public void initFrame() {
        frame = new JFrame("AddPosition");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setComboBox1(comboBox1);
        setComboBox2(comboBox2);
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocation(500, 100);
        frame.setVisible(true);
    }

    public void setComboBox2(JComboBox comboBox2) {
        this.comboBox2 = comboBox2;
        List<Position> positionList = positionController.getPositionList();
        List<String> titles = new ArrayList<>();
        for (int i=0; i<positionList.size(); i++) {
            Position position = positionList.get(i);
            titles.add(position.getTitle());
        }
        comboBox2.setModel(new DefaultComboBoxModel(titles.toArray()));
    }

    public void setComboBox1(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
        List<Position> positionList = positionController.getPositionList();
        List<String> titles = new ArrayList<>();
        for (int i=0; i<positionList.size(); i++) {
            Position position = positionList.get(i);
            titles.add(position.getCode().toString());
        }
        comboBox1.setModel(new DefaultComboBoxModel(titles.toArray()));
    }
}
