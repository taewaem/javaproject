package UI.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpFrame extends JFrame{
    
    private JLabel titleLabel;
    private JPanel signUpPanel;
    private JButton submitBtn;

    public SignUpFrame(){
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 500);
        setLocationRelativeTo(null);  // 가운데 정렬

        signUpPanel = new JPanel(){
            // 배경 클릭 시 입력 필드 포커스 해제
            {
                // 배경 클릭 시 입력 필드 포커스 해제
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        requestFocusInWindow(); // 이 JPanel로 포커스를 이동시켜서 기존 필드들 포커스 해제
                    }
                });
            }
        };
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
        signUpPanel.setBackground(new Color(34, 139, 34));

        //위아래 여백 균형 맞추기 위해 Glue 추가
        signUpPanel.add(Box.createVerticalGlue());

        //타이틀 라벨
        titleLabel = new JLabel("회원가입 정보 입력");
        titleLabel.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpPanel.add(titleLabel);

        //타이틀과 텍스트필드 사이 간격
        signUpPanel.add(Box.createVerticalStrut(30));

        //idField,pwField,pw2Field,nicknameField,birthdayField,phoneField
        String[] fieldTxt = {"아이디 입력", "비밀번호 입력", "비밀번화 확인", "닉네임 입력", 
                                "생년월일(ex20010116)", "전화번호 (예: 01047757921)"};

        for(String placeholder : fieldTxt){
            JTextField tf = new JTextField(placeholder);
            tf.setMaximumSize(new Dimension(200, 100));
            tf.setPreferredSize(new Dimension(200, 35));
            tf.setBorder(null);
            tf.setForeground(Color.GRAY);
            tf.setBackground(Color.WHITE);
            tf.setAlignmentX(Component.CENTER_ALIGNMENT);

            //포커스 리스너 추가
            tf.addFocusListener(new FocusAdapter(){

                @Override
                public void focusGained(FocusEvent e) {
                    if(tf.getText().equals(placeholder)){
                        tf.setText("");
                        tf.setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if(tf.getText().isEmpty()){
                        tf.setText(placeholder);
                        tf.setForeground(Color.GRAY);
                    }
                }
            });

            signUpPanel.add(tf);

            //텍스트필드 사이 간격
            signUpPanel.add(Box.createVerticalStrut(20));
        }

        submitBtn = new JButton("회원 가입");
        submitBtn.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,13));
        submitBtn.setPreferredSize(new Dimension(100, 35));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setBackground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setBorderPainted(false);
        signUpPanel.add(submitBtn);
        
        //텍스트필드와 버튼 사이 간격
        signUpPanel.add(Box.createVerticalStrut(30));

        //하단 여백
        signUpPanel.add(Box.createVerticalGlue());

        add(signUpPanel);
        setVisible(true);
        SwingUtilities.invokeLater(() -> signUpPanel.requestFocusInWindow());
    }

}
