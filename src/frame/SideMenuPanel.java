package frame;

import javax.swing.*;
import java.awt.*;

public class SideMenuPanel extends JPanel {
    public SideMenuPanel(CenterPanel centerPanel) {
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton dietBtn = new JButton("식단");
        JButton exerciseBtn = new JButton("운동량");
        JButton boardBtn = new JButton("게시판");
        JButton healthBtn = new JButton("건강 상태");
        JButton shopBtn = new JButton("상점 (영양제 추천)");

        dietBtn.addActionListener(e -> centerPanel.showPanel("diet"));
        exerciseBtn.addActionListener(e -> centerPanel.showPanel("exercise"));
        boardBtn.addActionListener(e -> centerPanel.showPanel("board"));
        healthBtn.addActionListener(e -> centerPanel.showPanel("health"));
        shopBtn.addActionListener(e -> centerPanel.showPanel("shop"));

        add(dietBtn);
        add(exerciseBtn);
        add(boardBtn);
        add(healthBtn);
        add(shopBtn);
    }
}
