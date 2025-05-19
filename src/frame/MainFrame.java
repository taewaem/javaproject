package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {//꾸미기

    private JPanel contentPane;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel loginButtonPanel;
    private JLabel titleLabel;
    private JButton loginBtn;
    private JPanel btnCenterPanel;

	public MainFrame() {
		
		setTitle("NutriLog");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(500, 300);
	    setLocationRelativeTo(null);  // 가운데 정렬
		
        //배경색 커스텀
        // contentPane = new JPanel(new BorderLayout());
        // contentPane.setBackground(new Color(34,139,34));
        // setContentPane(contentPane);

	    cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        // cardPanel.setBackground(new Color(34,139,34));
        
        loginButtonPanel = new JPanel(null){

            @Override
            public void doLayout() {
                // 패널의 너비와 높이
                int panelWidth = getWidth();
                int y = 100;  // 시작 y 위치
                int fieldWidth = 200;
                int fieldHeight = 30;

                // 가운데 정렬된 x 좌표 계산
                int centerX = (panelWidth - fieldWidth) / 2;

                FontMetrics fm = titleLabel.getFontMetrics(titleLabel.getFont());
                int titleWidth = fm.stringWidth(titleLabel.getText());
                int titleHeight = fm.getHeight();
                int titleX = (panelWidth - titleWidth) / 2;
                int titleY = y - titleHeight + 50;

                titleLabel.setBounds(titleX, titleY, titleWidth, titleHeight);
                loginBtn.setBounds(centerX, y + 100, fieldWidth, fieldHeight);
            }
            
        };
        loginButtonPanel.setBackground(new Color(34,139,34));

        //제목
        titleLabel = new JLabel("NutriLog");
        titleLabel.setFont(new Font("Bauhaus 93",Font.PLAIN,80));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 30, 500, 200);

        //로그인 버튼
        loginBtn = new JButton("Start");
        loginBtn.setPreferredSize(new Dimension(120,40));
        loginBtn.setBackground(Color.WHITE);
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setFont(new Font("Bauhous 93",Font.PLAIN,14));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.addActionListener(e -> showLoginPanel());

        // btnCenterPanel = new JPanel(new GridBagLayout());
        // btnCenterPanel.setBackground(new Color(34,139,34));
        // btnCenterPanel.add(loginBtn);
        
        loginButtonPanel.add(titleLabel);
        loginButtonPanel.add(loginBtn);
        
        cardPanel.add(loginButtonPanel, "LoginButton");
        
        JPanel loginPanel = new LoginPanel();
        cardPanel.add(loginPanel, "loginPanel");

        add(cardPanel);

		setVisible(true);
	}
	
    private void showLoginPanel() {
        cardLayout.show(cardPanel, "loginPanel");
    }
}