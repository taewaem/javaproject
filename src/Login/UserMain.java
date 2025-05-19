package UserMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import User.User;

public class UserMain extends JFrame {

    public UserMain() {
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

        panel.add(new JLabel("\t아이디:"));
        panel.add(idField);
        panel.add(new JLabel("\t비밀번호:"));
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

    private void showSignUpPanel() {
        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));

        JTextField idField = new JTextField();
        JPasswordField pwField = new JPasswordField();
        JPasswordField pw2Field = new JPasswordField();
        JTextField nicknameField = new JTextField();
        JTextField birthdayField = new JTextField();
        JTextField phoneField = new JTextField();

        JButton submitBtn = new JButton("회원가입");
        JButton backBtn = new JButton("뒤로가기");

        panel.add(new JLabel("아이디:"));
        panel.add(idField);
        panel.add(new JLabel("비밀번호:"));
        panel.add(pwField);
        panel.add(new JLabel("비밀번호 확인:"));
        panel.add(pw2Field);
        panel.add(new JLabel("닉네임:"));
        panel.add(nicknameField);
        panel.add(new JLabel("생년월일 (예: 20010116):"));
        panel.add(birthdayField);
        panel.add(new JLabel("전화번호 (예: 01047757921):"));
        panel.add(phoneField);
        panel.add(submitBtn);
        panel.add(backBtn);

        add(panel);

        submitBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String pw1 = new String(pwField.getPassword());
            String pw2 = new String(pw2Field.getPassword());
            String nickname = nicknameField.getText().trim();
            String birthday = birthdayField.getText().trim();
            String phone = phoneField.getText().trim();

            if (id.isEmpty() || pw1.isEmpty() || pw2.isEmpty() || nickname.isEmpty() ||
                    birthday.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "모든 칸에 입력해주세요.");
                return;
            }
            if (!pw1.equals(pw2)) {
                JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
                return;
            }
            if (!UserID.checkid(id)) {
                JOptionPane.showMessageDialog(this, "이미 존재하는 아이디입니다.");
                return;
            }
            if (!UserID.checkname(nickname)) {
                JOptionPane.showMessageDialog(this, "이미 존재하는 닉네임입니다.");
                return;
            }
            if (birthday.length() != 8) {
                JOptionPane.showMessageDialog(this, "생년월일은 8자리 숫자여야 합니다.");
                return;
            }
            if (phone.length() != 11) {
                JOptionPane.showMessageDialog(this, "올바른 전화번호를 입력해주세요.(예시 01047757921)");
                return;
            }

            UserID.save(new User(id, pw1, nickname, birthday, phone));
            JOptionPane.showMessageDialog(this, nickname + "님 회원가입이 완료되었습니다.");
            showLoginPanel();
        });

        backBtn.addActionListener(e -> showLoginPanel());

        revalidate();
        repaint();
    }

    private void showFindPanel() {
        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));

        JButton findIdBtn = new JButton("아이디 찾기");
        JButton findPwBtn = new JButton("비밀번호 찾기");
        JButton backBtn = new JButton("뒤로가기");

        panel.add(findIdBtn);
        panel.add(findPwBtn);
        panel.add(backBtn);

        add(panel);

        findIdBtn.addActionListener(e -> showFindIdPanel());
        findPwBtn.addActionListener(e -> showFindPwPanel());
        backBtn.addActionListener(e -> showLoginPanel());

        revalidate();
        repaint();
    }

    private void showFindIdPanel() {
        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        JTextField nicknameField = new JTextField();
        JTextField phoneField = new JTextField();

        JButton submitBtn = new JButton("찾기");
        JButton backBtn = new JButton("뒤로가기");

        panel.add(new JLabel("닉네임:"));
        panel.add(nicknameField);
        panel.add(new JLabel("전화번호:"));
        panel.add(phoneField);
        panel.add(submitBtn);
        panel.add(backBtn);

        add(panel);

        submitBtn.addActionListener(e -> {
            String nickname = nicknameField.getText().trim();
            String phone = phoneField.getText().trim();

            String foundId = UserID.findId(nickname, phone);
            if (foundId != null) {
                JOptionPane.showMessageDialog(this, "회원님의 아이디는 [" + foundId + "] 입니다.");
                showLoginPanel();
            } else {
                JOptionPane.showMessageDialog(this, "일치하는 아이디가 없습니다.");
            }
        });

        backBtn.addActionListener(e -> showFindPanel());

        revalidate();
        repaint();
    }

    private void showFindPwPanel() {
        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        JTextField idField = new JTextField();
        JTextField phoneField = new JTextField();
        JPasswordField newPwField1 = new JPasswordField();
        JPasswordField newPwField2 = new JPasswordField();

        JButton submitBtn = new JButton("비밀번호 변경");
        JButton backBtn = new JButton("뒤로가기");

        panel.add(new JLabel("아이디:"));
        panel.add(idField);
        panel.add(new JLabel("전화번호:"));
        panel.add(phoneField);
        panel.add(new JLabel("새 비밀번호:"));
        panel.add(newPwField1);
        panel.add(new JLabel("새 비밀번호 확인:"));
        panel.add(newPwField2);
        panel.add(submitBtn);
        panel.add(backBtn);

        add(panel);

        submitBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String phone = phoneField.getText().trim();
            String newPw1 = new String(newPwField1.getPassword());
            String newPw2 = new String(newPwField2.getPassword());

            if (!newPw1.equals(newPw2)) {
                JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
                return;
            }
            boolean success = UserID.changePassword(id, phone, newPw1);
            if (success) {
                JOptionPane.showMessageDialog(this, "비밀번호가 성공적으로 변경되었습니다.");
                showLoginPanel();
            } else {
                JOptionPane.showMessageDialog(this, "일치하는 아이디가 없습니다.");
            }
        });

        backBtn.addActionListener(e -> showFindPanel());

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserMain().setVisible(true);
        });
    }
}
