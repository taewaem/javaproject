package frame;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    private CardLayout layout;
    private JPanel cardPanel;

    public CenterPanel(CaloriePanel caloriePanel) {
        layout = new CardLayout();
        cardPanel = new JPanel(layout);

        cardPanel.add(new DietPanel(caloriePanel), "diet");
        cardPanel.add(new ExercisePanel(caloriePanel), "exercise");
        cardPanel.add(new BoardPanel(), "board");
        cardPanel.add(new HealthPanel(), "health");
        cardPanel.add(new ShopPanel(), "shop");
        cardPanel.add(new CartPanel(), "cart");
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);
    }

    public void showPanel(String name) {
        layout.show(cardPanel, name);
    }
}