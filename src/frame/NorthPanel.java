package frame;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {
    public NorthPanel(CenterPanel centerPanel) {
        setLayout(new FlowLayout());

        JLabel title = new JLabel("NutriLog", SwingConstants.CENTER);
        JButton cartButton = new JButton("장바구니");
        
        cartButton.addActionListener(e -> centerPanel.showPanel("cart"));

        add(title);
        add(cartButton);
    }
}
