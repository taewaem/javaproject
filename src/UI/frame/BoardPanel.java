package UI.frame;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    public BoardPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("게시판입니다.", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
