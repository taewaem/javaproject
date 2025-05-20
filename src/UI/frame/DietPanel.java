package UI.frame;

import javax.swing.*;
import java.awt.*;

public class DietPanel extends JPanel {

    private CaloriePanel caloriePanel;
    
    public DietPanel(CaloriePanel caloriePanel) {
        setLayout(new BorderLayout());
        add(new JLabel("식단 화면입니다.", SwingConstants.CENTER), BorderLayout.CENTER);
    }
    
    public void setFoodKcal(int foodKcal) {
        caloriePanel.setFoodKcal(foodKcal);
    }
}
