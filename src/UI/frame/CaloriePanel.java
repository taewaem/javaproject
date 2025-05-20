package UI.frame;

import javax.swing.*;
import java.awt.*;

public class CaloriePanel extends JPanel {
    //MenuPanel에서 작게 보이는 패널
    //보이는 칼로리는 DietPanel에서 받아옴()
    private JLabel intakeLabel;
    private JLabel burnedLabel;
    private JLabel totalLabel;

    public CaloriePanel(){
        setLayout(new GridLayout(3, 1, 5, 5));
        setPreferredSize(new Dimension(150, 120));
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setBackground(Color.WHITE);

        intakeLabel = new JLabel("섭취: - kcal", SwingConstants.CENTER);
        burnedLabel = new JLabel("소모: - kcal", SwingConstants.CENTER);
        totalLabel = new JLabel("잔여: - kcal", SwingConstants.CENTER);

        add(intakeLabel);
        add(burnedLabel);
        add(totalLabel);

    }

    public void updateText(int foodKcal, int exerKcal) {
        intakeLabel.setText("섭취: "+foodKcal+ "kcal");
        burnedLabel.setText("소모: "+exerKcal+" kcal");
        totalLabel.setText("잔여: "+(foodKcal-exerKcal)+" kcal");
    }
}