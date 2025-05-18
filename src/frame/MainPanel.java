package frame;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JFrame {
    public MainPanel() {
        setTitle("NutriLog");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());


        // 칼로리 영역
        CaloriePanel caloriePanel = new CaloriePanel();

        // 내용 화면 영역
        CenterPanel centerPanel = new CenterPanel(caloriePanel);

        // 상단 고정 영역
        NorthPanel northPanel = new NorthPanel(centerPanel);
        add(northPanel, BorderLayout.NORTH);

        // 왼쪽 메뉴
        SideMenuPanel sideMenu = new SideMenuPanel(centerPanel);
        add(sideMenu, BorderLayout.WEST);
        
        

        // CENTER에 칼로리 + 내용 합쳐서 넣기
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.add(caloriePanel, BorderLayout.NORTH);
        mainContent.add(centerPanel, BorderLayout.CENTER);
        add(mainContent, BorderLayout.CENTER);

        setVisible(true);
    }
}

