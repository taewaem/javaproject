package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPanel extends JPanel{		//JPanel 상속으로 바꾸기

	private JTextField idField;
	private JPasswordField pwField;

    public LoginPanel() {

        setLayout(new GridLayout(3, 2));

        add(new JLabel("아이디:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("비밀번호:"));
        pwField = new JPasswordField();
        add(pwField);

        JButton loginBtn = new JButton("로그인");
        loginBtn.addActionListener(e -> login());
        add(new JLabel()); // placeholder
        add(loginBtn);
    }

    private void login() {
        String id = idField.getText();
        String pw = new String(pwField.getPassword());

        // 간단한 예제 로그인 조건 (아이디: user / 비번: 1234)
        if (id.equals("user") && pw.equals("1234")) {
            JOptionPane.showMessageDialog(this, "로그인 성공!");
            SwingUtilities.getWindowAncestor(this).dispose();
            new MainPanel(); // 메인 패널 열기
        } else {
            JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }
	
}
