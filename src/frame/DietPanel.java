package frame;

import javax.swing.*;
import java.awt.*;

public class DietPanel extends JPanel {
    public DietPanel(CaloriePanel caloriePanel) {
        setLayout(new BorderLayout());
        add(new JLabel("식단 화면입니다.", SwingConstants.CENTER), BorderLayout.CENTER);

        // 예시로 칼로리 업데이트
        caloriePanel.updateText("식단 기준: 1500kcal / 운동: 0kcal / 잔여: 1500kcal");
    }
}
