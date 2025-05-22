package UI.frame;

import store.cart.Cart;
import store.frame.CartPanel;
import cal.food.FoodPanel;
import cal.exercise.ExercisePanel;
import check.CheckPanel;
import store.frame.StorePanel;
import store.frame.CartPanel;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Cart cart = new Cart();

    public CenterPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        CaloriePanel caloriePanel = new CaloriePanel();

        // cardPanel.add(caloriePanel, "calories");    //칼로리패널을 메뉴페널에서 분리하여 배치해보기
        cardPanel.add(new MenuPanel(this), "menu");
        cardPanel.add(new FoodPanel(), "food");
        cardPanel.add(new ExercisePanel(), "exercise");
        cardPanel.add(new BoardPanel(), "board");
        cardPanel.add(new CheckPanel(), "check");
        cardPanel.add(new StorePanel(), "store");
        cardPanel.add(new CartPanel(cart), "cart");
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        //초기 화면 -> MenuPanel
        cardLayout.show(cardPanel, "menu");

    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
}