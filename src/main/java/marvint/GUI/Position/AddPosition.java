package marvint.GUI.Position;

import marvint.GUI.MainForm;
import marvint.domain.Position;
import marvint.сontroller.PositionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AddPosition {

    private JInternalFrame frame;
    private JPanel panel1;
    private JLabel titleLabel;
    private JButton Button;
    private JTextField titleText;

    @Autowired
    PositionController positionController;

    @Autowired
    PositionForm positionForm;
    @Autowired
    MainForm mainForm;

    public AddPosition() {
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Position position = new Position();
                position.setTitle(titleText.getText());
                positionController.savePosition(position);
                positionForm.updateTable();
                frame.setVisible(false);
            }
        });
    }

    public void initFrame() {
        frame = new JInternalFrame("AddPosition", true, true, true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

}
