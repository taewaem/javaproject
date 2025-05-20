package UI.frame;

import javax.swing.*;
import java.awt.*;

public class DietPanel extends JPanel {
    public DietPanel(CaloriePanel caloriePanel) {
        setLayout(new BorderLayout());
        add(new JLabel("식단 화면입니다.", SwingConstants.CENTER), BorderLayout.CENTER);

        // 예시로 칼로리 업데이트
        caloriePanel.updateText(500, 200);
    }
}
