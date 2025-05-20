package UserMain;

import javax.swing.*;
import java.awt.*;
import User.User;

public class UserMain extends JPanel {

    private JFrame frame; // 프레임 

    public UserMain(JFrame frame) { 
        this.frame = frame;
        setLayout(new BorderLayout());
        showLoginPanel();
    }

    private void showLoginPanel() {//로그인 패널
        removeAll();

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField idField = new JTextField(); //아이디 입력
        JPasswordField pwField = new JPasswordField(); //비번 입력 

        JButton loginBtn = new JButton("Login"); //버튼 
        Font font = new Font("Bauhaus 93",Font.PLAIN,10);
        loginBtn.setFont(font);
        JButton signupBtn = new JButton("SignUp");
        signupBtn.setFont(font);
        JButton findBtn = new JButton("ID/PW Found");
        findBtn.setFont(font);

        panel.add(new JLabel("아이디:"));
        panel.add(new JLabel("비밀번호:"));
        panel.add(pwField);
        panel.add(loginBtn);
        panel.add(signupBtn);
        panel.add(findBtn);

        add(panel, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> { //기능
            String id = idField.getText().trim();
            String pw = new String(pwField.getPassword());

            if (UserID.loginCheck(id, pw)) {
                JOptionPane.showMessageDialog(frame, "로그인에 성공하셨습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "아이디 또는 비밀번호가 맞지 않습니다.");
            }
        });

        signupBtn.addActionListener(e -> showSignUpPanel());
        findBtn.addActionListener(e -> showFindPanel());

        revalidate();
        repaint();
    }

    private void showSignUpPanel() { //회원가입 창
        removeAll();

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

        add(panel, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> { 
            String id = idField.getText().trim();
            String pw1 = new String(pwField.getPassword());
            String pw2 = new String(pw2Field.getPassword());
            String nickname = nicknameField.getText().trim();
            String birthday = birthdayField.getText().trim();
            String phone = phoneField.getText().trim();

            if (id.isEmpty() || pw1.isEmpty() || pw2.isEmpty() || nickname.isEmpty() ||
                    birthday.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "모든 칸에 입력해주세요.");
                return;
            }
            if (!pw1.equals(pw2)) {
                JOptionPane.showMessageDialog(frame, "비밀번호가 일치하지 않습니다.");
                return;
            }
            if (!UserID.checkid(id)) {
                JOptionPane.showMessageDialog(frame, "이미 존재하는 아이디입니다.");
                return;
            }
            if (!UserID.checkname(nickname)) {
                JOptionPane.showMessageDialog(frame, "이미 존재하는 닉네임입니다.");
                return;
            }
            if (birthday.length() != 8) {
                JOptionPane.showMessageDialog(frame, "생년월일은 8자리 숫자여야 합니다.");
                return;
            }
            if (phone.length() != 11) {
                JOptionPane.showMessageDialog(frame, "올바른 전화번호를 입력해주세요.(예시 01047757921)");
                return;
            }

            UserID.save(new User(id, pw1, nickname, birthday, phone));
            JOptionPane.showMessageDialog(frame, nickname + "님 회원가입이 완료되었습니다.");
            showLoginPanel();
        });

        backBtn.addActionListener(e -> showLoginPanel());

        revalidate();
        repaint();
    }

    private void showFindPanel() { //ID ,PW 찾기창 
        removeAll();

        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));

        JButton findIdBtn = new JButton("아이디 찾기");
        JButton findPwBtn = new JButton("비밀번호 찾기");
        JButton backBtn = new JButton("뒤로가기");

        panel.add(findIdBtn);
        panel.add(findPwBtn);
        panel.add(backBtn);

        add(panel, BorderLayout.CENTER);

        findIdBtn.addActionListener(e -> showFindIdPanel());
        findPwBtn.addActionListener(e -> showFindPwPanel());
        backBtn.addActionListener(e -> showLoginPanel());

        revalidate();
        repaint();
    }

    private void showFindIdPanel() { //ID 찾기
        removeAll();

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

        add(panel, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> { //찾기성공 
            String nickname = nicknameField.getText().trim();
            String phone = phoneField.getText().trim();

            String foundId = UserID.findId(nickname, phone);
            if (foundId != null) {
                JOptionPane.showMessageDialog(frame, "회원님의 아이디는 [" + foundId + "] 입니다.");
                showLoginPanel();
            } else {
                JOptionPane.showMessageDialog(frame, "일치하는 아이디가 없습니다.");
            }
        });

        backBtn.addActionListener(e -> showFindPanel());

        revalidate();
        repaint();
    }

    private void showFindPwPanel() {//비번찾기
        removeAll();

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

        add(panel, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> { 
            String id = idField.getText().trim();
            String phone = phoneField.getText().trim();
            String newPw1 = new String(newPwField1.getPassword());
            String newPw2 = new String(newPwField2.getPassword());

            if (!newPw1.equals(newPw2)) {
                JOptionPane.showMessageDialog(frame, "비밀번호가 일치하지 않습니다.");
                return;
            }
            boolean success = UserID.changePassword(id, phone, newPw1);
            if (success) {
                JOptionPane.showMessageDialog(frame, "비밀번호가 성공적으로 변경되었습니다.");
                showLoginPanel();
            } else {
                JOptionPane.showMessageDialog(frame, "일치하는 아이디가 없습니다.");
            }
        });

        backBtn.addActionListener(e -> showFindPanel());

        revalidate();
        repaint();
    }
    //메인 테스트
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("UserMain 테스트");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 400);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new UserMain(frame));
            frame.setVisible(true);
        });
    }
}
