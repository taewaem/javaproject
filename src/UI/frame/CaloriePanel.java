package frame;

import javax.swing.*;
import java.awt.*;

public class CaloriePanel extends JPanel {
    //보이는 칼로리는 DietPanel에서 받아옴
    private JLabel calLabel;

    public CaloriePanel(){
        setLayout(new FlowLayout());
        calLabel = new JLabel("먹은 칼로리 / 운동 칼로리 / 잔여 칼로리");
        add(calLabel);
    }

    public void updateText(String text) {
        calLabel.setText(text);
    }
}