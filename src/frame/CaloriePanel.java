package frame;

import javax.swing.*;
import java.awt.*;

public class CaloriePanel extends JPanel {
    private JLabel label;

    public CaloriePanel() {
        setLayout(new FlowLayout());
        label = new JLabel("먹은 칼로리 / 운동 칼로리 / 잔여 칼로리");
        add(label);
    }

    public void updateText(String text) {
        label.setText(text);
    }
}