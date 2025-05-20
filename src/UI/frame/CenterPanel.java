package UI.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class CenterPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel caloriePanel;

    public CenterPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Color themeColor = new Color(34, 139, 34);
        setBackground(themeColor);

        //칼로리 패널(섭취/소모/총)
        caloriePanel = new JPanel();
        caloriePanel.setLayout(new GridLayout(3, 1, 5, 5));
        caloriePanel.setPreferredSize(new Dimension(120, 120));
        caloriePanel.setBorder(new LineBorder(Color.WHITE, 2));
        caloriePanel.setBackground(Color.WHITE);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
}