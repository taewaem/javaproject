package UI.frame;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    public ShopPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("상점입니다.", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
