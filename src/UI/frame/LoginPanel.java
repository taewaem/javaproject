package UI.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Login.UserID;
import Login.UserList;
import store.frame.Main;

public class LoginPanel extends JPanel{

    private JLabel titleLabel;
    private JTextField idField;
    private JPasswordField pwField;
    private JButton signInBtn;
    private JButton signUpBtn;
    private UserList userList = new UserList();
    public LoginPanel() {

        setBackground(new Color(34,139,34)); //배경색
        setLayout(null);    //위치 수동 지정

        // 배경 클릭 시 입력 필드 포커스 해제
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow(); // 이 JPanel로 포커스를 이동시켜서 기존 필드들 포커스 해제
            }
        });

        titleLabel = new JLabel("NutriLog");
        titleLabel.setFont(new Font("Bauhaus 93",Font.PLAIN,100));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        idField = new JTextField("ID");
        idField.setFont(new Font("맑은고딕",Font.BOLD,17));
        String id = idField.getText();
        idField.setForeground(Color.GRAY);
        idField.setBackground(Color.WHITE);
        idField.setBorder(null);
        idField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (idField.getText().equals("ID")) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (idField.getText().isEmpty()) {
                    idField.setText("ID");
                    idField.setForeground(Color.GRAY);
                }
            }
        });
        add(idField);

        pwField = new JPasswordField();
        String pw = new String(pwField.getPassword());
        pwField.setFont(new Font("맑은고딕",Font.PLAIN,17));
        pwField.setForeground(Color.GRAY);
        pwField.setBackground(Color.WHITE);
        pwField.setBorder(null);
        pwField.setEchoChar((char) 0);
        pwField.setText("PW");
        pwField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(pwField.getPassword()).equals("PW")) {
                    pwField.setText("");
                    pwField.setForeground(Color.BLACK);
                    pwField.setEchoChar('•'); // 입력 시 비밀번호 표시
                }
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(pwField.getPassword()).isEmpty()) {
                    pwField.setText("PW");
                    pwField.setForeground(Color.GRAY);
                    pwField.setEchoChar((char) 0); // 다시 평문으로
                }
            }
        });
        add(pwField);

        // 로그인(Sign In) 버튼
        signInBtn = new JButton("Sign In");
        signInBtn.setForeground(Color.BLACK);
        signInBtn.setBackground(Color.WHITE);
        signInBtn.setFont(new Font("Bauhaus 93",Font.BOLD, 20));
        signInBtn.setFocusPainted(false);
        signInBtn.setBorderPainted(false);

        signInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String pw = new String(pwField.getPassword());

                if (id.equals("ID") || pw.equals("PW")) {
                    JOptionPane.showMessageDialog(null, "ID와 비밀번호를 입력하세요.");
                    return;
                }

                boolean loginSuccess = userList.login(id, pw);
                if (loginSuccess) {
                    SwingUtilities.getWindowAncestor(LoginPanel.this).dispose();
                    JOptionPane.showMessageDialog(null, id+"님, 환영합니다!");
                    new MainFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "ID 또는 비밀번호가 올바르지 않습니다.");
                }
            }
        });
        add(signInBtn);

        //회원가입(Sign Up) 버튼
        signUpBtn = new JButton("Sign Up");
        signUpBtn.setForeground(Color.BLACK);
        signUpBtn.setBackground(Color.WHITE);
        signUpBtn.setFont(new Font("Bauhaus 93",Font.BOLD, 20));
        signUpBtn.setFocusPainted(false);
        signUpBtn.setBorderPainted(false);
        signUpBtn.addActionListener(e -> showSignUpFrame());
        add(signUpBtn);
    }



    @Override
    public void doLayout() {
        // 패널의 너비와 높이
        int panelWidth = getWidth();
        int y = 150;  // 시작 y 위치
        int fieldWidth = 250;
        int fieldHeight = 50;

        // 가운데 정렬된 x 좌표 계산
        int centerX = (panelWidth - fieldWidth) / 2;

        FontMetrics fm = titleLabel.getFontMetrics(titleLabel.getFont());
        int titleWidth = fm.stringWidth(titleLabel.getText());
        int titleHeight = fm.getHeight();
        int titleX = (panelWidth - titleWidth) / 2;
        int titleY = y - titleHeight - 70;

        titleLabel.setBounds(titleX, titleY+100, titleWidth, titleHeight);
        idField.setBounds(centerX, y + 30, fieldWidth, fieldHeight);
        pwField.setBounds(centerX, y + 100, fieldWidth, fieldHeight);

        //버튼 배치(signInBtn과 signUpBtn 나란히)
        int btnWidth = 115;
        int btnHeight = 50;
        int gap = 20;   //나란히 배치된 버튼 간격
        int totalBtnWidth = btnWidth * 2 + gap;
        int btnStartX = (panelWidth - totalBtnWidth) / 2;
        int btnY = y + 170;

        signInBtn.setBounds(btnStartX, btnY, btnWidth, btnHeight);
        signUpBtn.setBounds(btnStartX+btnWidth+gap, btnY, btnWidth, btnHeight);
    }

    private void showSignUpFrame(){
        new SignUpFrame();
    }

}