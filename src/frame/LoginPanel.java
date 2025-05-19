package frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPanel extends JPanel{

    private JLabel titleLabel;
	private JTextField idField;
	private JPasswordField pwField;
    private JButton loginBtn;

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
        titleLabel.setFont(new Font("Bauhaus 93",Font.PLAIN,50));
        titleLabel.setForeground(Color.WHITE);
        //titleLabel.setBounds(200, 30, 500, 200);
        add(titleLabel);

        idField = new JTextField("ID");
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
        pwField.addActionListener(e -> login());
        add(pwField);

        loginBtn = new JButton("Login");
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setBackground(Color.WHITE);
        loginBtn.setFont(new Font("Bauhaus 93",Font.BOLD, 14));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        // loginBtn.addMouseListener(new MouseAdapter() {
        //     public void mousePressed(MouseEvent e){
        //         loginBtn.setBackground(Color.GRAY);
        //     }
        //     public void mouseReleased(MouseEvent e){
        //         loginBtn.setBackground(Color.WHITE);
        //     }
        // });
        loginBtn.addActionListener(e -> login());
        add(loginBtn);
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
        idField.setBounds(centerX, y, fieldWidth, fieldHeight);
        pwField.setBounds(centerX, y + 50, fieldWidth, fieldHeight);
        loginBtn.setBounds(centerX, y + 100, fieldWidth, fieldHeight);
    }



    private void login() {
        String id = idField.getText();
        String pw = new String(pwField.getPassword());

        // 간단한 예제 로그인 조건 (아이디: user / 비번: 1234)
        if (id.equals("user") && pw.equals("1234")) {
            JOptionPane.showMessageDialog(this, id+"님, 환영합니다!");
            SwingUtilities.getWindowAncestor(this).dispose();
            new MainPanelFrame(); // 메인 패널 열기
        } else {
            JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }
	
}
