package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {//꾸미기

    private JPanel cardPanel;
    private CardLayout cardLayout;

	public MainFrame() {
		
		setTitle("NutriLog");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(500, 300);
	    setLocationRelativeTo(null);  // 가운데 정렬
		
	    cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel loginButtonPanel = new JPanel(new GridBagLayout());
        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setPreferredSize(new Dimension(120,40));
        loginBtn.addActionListener(e -> showLoginPanel());
        loginButtonPanel.add(loginBtn);
		
        JPanel loginPanel = new LoginPanel();
        cardPanel.add(loginButtonPanel, "LoginButton");
        cardPanel.add(loginPanel, "loginPanel");

		add(cardPanel);
		setVisible(true);
	}
	
    private void showLoginPanel() {
        cardLayout.show(cardPanel, "loginPanel");
    }
}