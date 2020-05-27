package marvint.GUI.Position;

import marvint.domain.Position;
import marvint.сontroller.PositionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AddPosition {

    private JFrame frame;
    private JPanel panel1;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JButton Button;
    private JTextField idText;
    private JTextField titleText;

    @Autowired
    PositionController positionController;

    @Autowired
    PositionForm positionForm;

    public AddPosition() {
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Position position = new Position();
                position.setCode(Long.parseLong(idText.getText()));
                position.setTitle(titleText.getText());
                positionController.savePosition(position);
                positionForm.updateTable();
                frame.setVisible(false);
            }
        });
    }

    public void initFrame() {
        frame = new JFrame("AddPosition");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocation(500, 100);
        frame.setVisible(true);
    }

}
