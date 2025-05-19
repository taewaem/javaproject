package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserMainEx extends JFrame{

     public UserMainEx() {
        setTitle("로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        showLoginPanel();
    }

    private void showLoginPanel() {
        getContentPane().removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2,10, 10));

        JTextField idField = new JTextField();
        JPasswordField pwField = new JPasswordField();

        JButton loginBtn = new JButton("로그인");
        Font font = new Font("맑은 고딕",Font.BOLD,15);
        loginBtn.setFont(font);
        JButton signupBtn = new JButton("회원가입");
        JButton findBtn = new JButton("ID/PW 찾기");

        panel.add(new JLabel("아이디:"));
        panel.add(idField);
        panel.add(new JLabel("비밀번호:"));
        panel.add(pwField);

        panel.add(loginBtn);
        panel.add(signupBtn);
        panel.add(findBtn);

        add(panel);

        loginBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String pw = new String(pwField.getPassword());

            if (UserID.loginCheck(id, pw)) {
                JOptionPane.showMessageDialog(this, "로그인에 성공하셨습니다.");
            } else {
                JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 맞지 않습니다.");
            }
        });

        signupBtn.addActionListener(e -> showSignUpPanel());
        findBtn.addActionListener(e -> showFindPanel());

        revalidate();
        repaint();
    }


}
