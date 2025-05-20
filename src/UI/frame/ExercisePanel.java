package frame;

import javax.swing.*;
import java.awt.*;

public class ExercisePanel extends JPanel {
    public ExercisePanel(CaloriePanel caloriePanel) {
        setLayout(new BorderLayout());
        add(new JLabel("운동량 화면입니다.", SwingConstants.CENTER), BorderLayout.CENTER);

        // 예시로 칼로리 업데이트
        caloriePanel.updateText("식단 기준: 1500kcal / 운동: 300kcal / 잔여: 1200kcal");
    }
}
