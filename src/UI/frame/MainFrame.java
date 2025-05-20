package frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    //로그인 성공하면 열리는 frame
    //상단에 이름과 장바구니
    //중앙에 메뉴화면

    public MainFrame() {

        Color themeColor = new Color(34, 139, 34);

        setTitle("NutriLog");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());


        // 칼로리 영역
        CaloriePanel caloriePanel = new CaloriePanel();
        caloriePanel.setBackground(themeColor);

        // 내용 화면 영역
        CenterPanel centerPanel = new CenterPanel(caloriePanel);
        centerPanel.setBackground(themeColor);

        // 상단 고정 영역
        NorthPanel northPanel = new NorthPanel(centerPanel);
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

