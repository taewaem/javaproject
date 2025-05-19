package frame;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public CenterPanel(CaloriePanel caloriePanel) {
        setLayout(null);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

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
        cardLayout.show(cardPanel, name);
    }
}