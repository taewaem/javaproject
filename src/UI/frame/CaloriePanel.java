package UI.frame;

import javax.swing.*;
import java.awt.*;

public class CaloriePanel extends JPanel {
    //MenuPanel에서 작게 보이는 패널
    //보이는 칼로리는 DietPanel에서 받아옴()
    private JLabel intakeLabel;
    private JLabel burnedLabel;
    private JLabel totalLabel;

    private int foodKcal = 0;
    private int exerKcal = 0;

    public CaloriePanel(){
        setLayout(new GridLayout(3, 1, 5, 5));
        setPreferredSize(new Dimension(150, 120));
        //setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBackground(new Color(0,0,0,0));

        intakeLabel = new JLabel("섭취: - kcal", SwingConstants.CENTER);
        intakeLabel.setFont(new Font("돋움체",Font.BOLD,20));
        burnedLabel = new JLabel("소모: - kcal", SwingConstants.CENTER);
        burnedLabel.setFont(new Font("돋움체",Font.BOLD,20));
        totalLabel = new JLabel("잔여: - kcal", SwingConstants.CENTER);
        totalLabel.setFont(new Font("돋움체",Font.BOLD,20));

        add(intakeLabel);
        add(burnedLabel);
        add(totalLabel);

    }

    public void setFoodKcal(int foodKcal){
        this.foodKcal = foodKcal;
        updateLabels();
    }

    public void setExerKcal(int exerKcal){
        this.exerKcal = exerKcal;
        updateLabels();
    }

    public void updateLabels() {
        intakeLabel.setText("섭취: "+foodKcal+ "kcal");
        burnedLabel.setText("소모: "+exerKcal+" kcal");
        totalLabel.setText("잔여: "+(foodKcal-exerKcal)+" kcal");
    }
}