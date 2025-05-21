package UI.frame;

import javax.swing.*;
import java.awt.*;

public class HealthPanel extends JPanel {
    public HealthPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("건강 상태입니다.", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}