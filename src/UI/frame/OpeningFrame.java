package UI.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpeningFrame extends JFrame {//꾸미기
    //Main을 실행하면 처음 나오는 화면
    
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel openPanel;
    private JLabel titleLabel;
    private JButton startBtn;


	public OpeningFrame() {
		
		setTitle("NutriLog");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(800, 500);
	    setLocationRelativeTo(null);  // 가운데 정렬

	    cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        openPanel = new JPanel(null){
            
            @Override
            public void doLayout() {
                
                // 패널의 너비와 높이
                int panelWidth = getWidth();
                int y = 150;  // 시작 y 위치
                int fieldWidth = 200;
                int fieldHeight = 80;

                // 가운데 정렬된 x 좌표 계산
                int centerX = (panelWidth - fieldWidth) / 2;

                FontMetrics fm = titleLabel.getFontMetrics(titleLabel.getFont());
                int titleWidth = fm.stringWidth(titleLabel.getText());
                int titleHeight = fm.getHeight();
                int titleX = (panelWidth - titleWidth) / 2;
                int titleY = y - titleHeight + 170;

                titleLabel.setBounds(titleX, titleY, titleWidth, titleHeight);
                startBtn.setBounds(centerX, y + 150, fieldWidth, fieldHeight);
            }
            
        };
        openPanel.setBackground(new Color(34,139,34));

        //제목
        titleLabel = new JLabel("NutriLog");
        titleLabel.setFont(new Font("Bauhaus 93",Font.PLAIN,200));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 30, 500, 200);

        //로그인 버튼
        startBtn = new JButton("Start");
        startBtn.setBackground(Color.WHITE);
        startBtn.setForeground(Color.BLACK);
        startBtn.setFont(new Font("Bauhaus 93",Font.PLAIN,70));
        startBtn.setFocusPainted(false);
        startBtn.setBorderPainted(false);
        startBtn.setMargin(new Insets(20, 0, 0, 0));
        startBtn.setVerticalAlignment(SwingConstants.CENTER);
        startBtn.setHorizontalAlignment(SwingConstants.CENTER);
        startBtn.setVerticalTextPosition(SwingConstants.CENTER);
        startBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        startBtn.addActionListener(e -> showLoginPanel());

        openPanel.add(titleLabel);
        openPanel.add(startBtn);
        
        cardPanel.add(openPanel, "startButton");
        
        JPanel loginPanel = new LoginPanel();
        cardPanel.add(loginPanel, "loginPanel");

        add(cardPanel);

		setVisible(true);
	}
	
    private void showLoginPanel() {
        cardLayout.show(cardPanel, "loginPanel");
    }
}