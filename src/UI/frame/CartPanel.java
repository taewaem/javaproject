package frame;

import javax.swing.*;
import java.awt.*;

public class CartPanel extends JPanel {
    public CartPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("장바구니입니다.", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}