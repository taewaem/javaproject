package frame;

import javax.swing.*;
import java.awt.*;

public class MainPanelFrame extends JFrame {
    //로그인 성공하면 열리는 frame

    public MainPanelFrame() {

        Color themeColor = new Color(34, 139, 34);

        setTitle("NutriLog");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        // setBackground(new Color(34,139,34));
        setLayout(new BorderLayout());


        // 칼로리 영역
        CaloriePanel caloriePanel = new CaloriePanel();
        caloriePanel.setBackground(themeColor);

        // 내용 화면 영역
        CenterPanel centerPanel = new CenterPanel(caloriePanel);
        centerPanel.setBackground(themeColor);

        // 상단 고정 영역
        NorthPanel northPanel = new NorthPanel(centerPanel);
        northPanel.setBackground(themeColor);
        add(northPanel, BorderLayout.NORTH);
        

        // CENTER에 칼로리 + centerPanel 내용 합쳐서 넣기
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(themeColor);
        mainContent.add(caloriePanel, BorderLayout.NORTH);
        mainContent.add(centerPanel, BorderLayout.CENTER);
        add(mainContent, BorderLayout.CENTER);

        getContentPane().setBackground(themeColor);

        setVisible(true);
    }
}

