import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class LoginFrameEx extends JFrame { 

    private JButton createFlatButton(String text, Color foreground, Color background) {
        JButton button = new JButton(text); // 버튼 텍스트 설정
        button.setForeground(foreground); // 텍스트색 설정
        button.setBackground(background); // 배경색 설정
        button.setOpaque(true); // 배경색 나타나게 설정
        button.setFocusable(false); // 포커스 제거
        button.setBorderPainted(false); // 테두리 제거
        return button;
    }

    private JLabel createFlatLabel(String text, Color foreground) {
        JLabel label = new JLabel(text); // 라벨 텍스트 설정
        label.setForeground(foreground); // 텍스트색 설정
        return label;
    }

    private JTextField createFlatTextField(Color background){
        JTextField textField = new JTextField();
        textField.setBackground(background); // 필드 배경색 설정
        textField.setBorder(null); // 테두리 제거
        return textField;
    }



    public LoginFrameEx () {
        setTitle("DayKeeper"); // Frame 타이틀 설정
        setSize(300, 450); // 폭 300, 높이 500 크기로 Frame 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Frame 윈도우를 닫으면 프로그램을 종료하도록 설정
        setLocationRelativeTo(null); // 화면 중앙에 배치
        setResizable(false); // Frame 크기 고정

        Container contentPane = getContentPane(); // 컨텐트 팬 알아냄
        contentPane.setBackground(Color.WHITE); // 컨텐트팬 색 화이트로 설정 
        contentPane.setLayout(null); // 컴포넌트 위치 직접 지정

        // daykeeper 라벨 생성
        JLabel daykeeperLabel = createFlatLabel("DayKeeper", new Color(131, 36, 255));
        daykeeperLabel.setBounds(50, 50, 200, 70);
        daykeeperLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
        daykeeperLabel.setHorizontalAlignment(JLabel.CENTER); // 텍스트 중앙 정렬
        contentPane.add(daykeeperLabel);

        // login 라벨 생성
        JLabel loginLabel = createFlatLabel("Login", new Color(131, 36, 255));
        loginLabel.setBounds(50, 150, 200, 40);
        loginLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        loginLabel.setHorizontalAlignment(JLabel.LEFT); // 텍스트 왼쪽 정렬
        contentPane.add(loginLabel);

        // userId 필드 생성
        JTextField userIdField = createFlatTextField(new Color(240, 240, 240));
        userIdField.setBounds(50, 200, 200, 40);
        userIdField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        contentPane.add(userIdField);

        // Password 필드 생성
        JTextField PasswordField = createFlatTextField(new Color(240, 240,240));
        PasswordField.setBounds(50, 250, 200, 40);;
        PasswordField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12)); 
        PasswordField.setBorder(null); // 테두리 제거
        contentPane.add(PasswordField);

        // login 버튼 생성
        JButton loginButton = createFlatButton("Login", Color.WHITE, new Color(131, 36, 255));
        loginButton.setBounds(50, 300, 200, 40);
        loginButton.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        loginButton.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(loginButton);


        // 아이디/비밀번호 찾기 라벨 생성
        JButton findInfoButton = createFlatButton("아이디 / 비밀번호 찾기", Color.LIGHT_GRAY, Color.WHITE);
        findInfoButton.setBounds(50, 350, 200, 10);
        findInfoButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
        contentPane.add(findInfoButton);
        
        // 회원가입 라벨 생성
        JButton signUpButton = createFlatButton("회원가입", Color.LIGHT_GRAY, Color.WHITE);
        signUpButton.setBounds(50, 370, 200, 10);
        signUpButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
        contentPane.add(signUpButton);

        // 클릭 이벤트: 회원가입 창 열기
        signUpButton.addActionListener(e -> {new Signup();});
        revalidate();
        repaint();

        setVisible(true); // Frame 출력
    }

    public static void main(String[] args) {
        LoginFrameEx frame = new LoginFrameEx();
    }


}
