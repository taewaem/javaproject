package UI.frame;

import Login.User;
import cal.food.Food;

import javax.swing.*;
import java.awt.*;


public class CaloriePanel extends JPanel {
    //MenuPanel에서 작게 보이는 패널
    //보이는 칼로리는 DietPanel에서 받아옴()
    private JLabel intakeLabel;
    private JLabel burnedLabel;
    private JLabel totalLabel;
    public static User user = new User();

    public CaloriePanel(){
        setLayout(new GridLayout(3, 1, 5, 5));
        setPreferredSize(new Dimension(250, 120));
        //setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBackground(new Color(0,0,0,0));

        intakeLabel = new JLabel("섭취: " + String.format("%.1f",user.getTotalFoodKcal()) + " kcal", SwingConstants.CENTER);
        intakeLabel.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,17));
        burnedLabel = new JLabel("소모: " + String.format("%.1f", user.getTotalExerKcal()) + " kcal", SwingConstants.CENTER);
        burnedLabel.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,17));
        totalLabel = new JLabel("잔여: " + String.format("%.1f", (user.getTotalFoodKcal() - user.getTotalExerKcal())) + " kcal", SwingConstants.CENTER);
        totalLabel.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,17));

        System.out.println("총 섭취량: " + String.format("%.1f",user.getTotalFoodKcal()));
        System.out.println("총 소모량: " + String.format("%.1f", user.getTotalExerKcal()));
        System.out.println("총 잔여량: " + String.format("%.1f", (user.getTotalFoodKcal() - user.getTotalExerKcal())));

        add(intakeLabel);
        add(burnedLabel);
        add(totalLabel);

    }
//
//    public void updateLabels() {
//        intakeLabel.setText("섭취: "+foodKcal+ "kcal");
//        burnedLabel.setText("소모: "+exerKcal+" kcal");
//        totalLabel.setText("잔여: "+(foodKcal-exerKcal)+" kcal");
//    }
}