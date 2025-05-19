package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//실행했을 때 열리는 창(제목+로그인+회원가입)
public class MainFrame extends JFrame {//꾸미기

    private JLabel titleLabel;
    private JPanel mainPanel;
    private JButton loginBtn;

	public MainFrame() {
		
		setTitle("NutriLog");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(500, 300);
	    setLocationRelativeTo(null);  // 가운데 정렬

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(34, 139, 34));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));    //수직 박스 레이아웃
		
        titleLabel = new JLabel("NutriLog", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Bauhaus 93",Font.PLAIN,50));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);   //중앙 정렬렬


        JPanel loginButtonPanel = new JPanel(new GridBagLayout());
        loginButtonPanel.setBackground(new Color(34,139,34));

        loginBtn = new JButton("LOGIN");
        loginBtn.setPreferredSize(new Dimension(120,40));
        loginBtn.addActionListener(e -> showLoginPanel());
        loginButtonPanel.add(loginBtn);
		
        JPanel loginPanel = new LoginPanel();
        cardPanel.add(loginButtonPanel, "LoginButton");
        cardPanel.add(loginPanel, "loginPanel");

		add(cardPanel);
		setVisible(true);
	}
	
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
        int titleY = y - titleHeight - 10;

        titleLabel.setBounds(titleX, titleY, titleWidth, titleHeight);
        loginBtn.setBounds(centerX, y + 100, fieldWidth, fieldHeight);
    }

    private void showLoginPanel() {
        cardLayout.show(cardPanel, "loginPanel");
    }
}