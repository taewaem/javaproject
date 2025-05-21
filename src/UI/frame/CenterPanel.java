package UI.frame;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public CenterPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        CaloriePanel caloriePanel = new CaloriePanel();

        cardPanel.add(new MenuPanel(this), "menu");
        cardPanel.add(new DietPanel(caloriePanel), "diet");
        cardPanel.add(new ExercisePanel(caloriePanel), "exercise");
        cardPanel.add(new BoardPanel(), "board");
        cardPanel.add(new HealthPanel(), "health");
        cardPanel.add(new ShopPanel(), "shop");
        cardPanel.add(new CartPanel(), "cart");
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        //초기 화면 -> MenuPanel
        cardLayout.show(cardPanel, "menu");

    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
}