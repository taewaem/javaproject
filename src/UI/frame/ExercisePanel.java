package UI.frame;

import javax.swing.*;
import java.awt.*;

public class ExercisePanel extends JPanel {

    private CaloriePanel caloriePanel;
    public ExercisePanel(CaloriePanel caloriePanel) {
        setLayout(new BorderLayout());
        add(new JLabel("운동량 화면입니다.", SwingConstants.CENTER), BorderLayout.CENTER);
    }
    public void setExerKcal(int exerKcal) {
        caloriePanel.setExerKcal(exerKcal);
    }
}
