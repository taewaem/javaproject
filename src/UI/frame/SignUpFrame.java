package UI.frame;

import Login.User;
import Login.UserID;
import Login.UserList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpFrame extends JFrame{
    
    private JLabel titleLabel;
    private JPanel signUpPanel;
    private JButton submitBtn;
    private UserList userList = new UserList();

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
        JTextField[] inputFields = new JTextField[fieldTxt.length];

        for(int i=0; i<fieldTxt.length; i++){
            String placeholder = fieldTxt[i];
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
            inputFields[i] = tf;

        }

        submitBtn = new JButton("회원 가입");
        submitBtn.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,13));
        submitBtn.setPreferredSize(new Dimension(100, 35));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setBackground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setBorderPainted(false);
        signUpPanel.add(submitBtn);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id =inputFields[0].getText();
                String pw1 = inputFields[1].getText();
                String pw2 = inputFields[2].getText();
                String nickname = inputFields[3].getText();
                String birthday = inputFields[4].getText();
                String phone = inputFields[5].getText();

                System.out.println(id);
                System.out.println(pw1);
                if (id.equals("아이디 입력") ||
                        pw1.equals("비밀번호 입력") ||
                        pw2.equals("비밀번호 확인") ||
                        nickname.equals("닉네임 입력") ||
                        birthday.equals("생년월일(ex20010116)") ||
                        phone.equals("전화번호 (예: 01047757921)"))
                {
                    JOptionPane.showMessageDialog(null, "모든 칸에 입력해주세요.");
                    return;
                }
                if (!pw1.equals(pw2)) {
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                    return;
                }
                if (!userList.checkNickname(nickname)) {
                    JOptionPane.showMessageDialog(null, "같은 닉네임이 존재합니다.");
                    return;
                }
                if (birthday.length() != 8) {
                    JOptionPane.showMessageDialog(null, "생년월일은 8자리 숫자여야 합니다.");
                    return;
                }
                if (phone.length() != 11) {
                    JOptionPane.showMessageDialog(null, "올바른 전화번호를 입력해주세요.(예시 01047757921)");
                    return;
                }

                boolean signup = userList.signup(id, pw1, pw2, nickname, birthday, phone);

                if (signup) {
                    JOptionPane.showMessageDialog(null, "회원가입 성공");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,"이미 존재하는 아이디 입니다");

                }
            }});

        //텍스트필드와 버튼 사이 간격
        signUpPanel.add(Box.createVerticalStrut(30));

        //하단 여백
        signUpPanel.add(Box.createVerticalGlue());

        add(signUpPanel);
        setVisible(true);
        SwingUtilities.invokeLater(() -> signUpPanel.requestFocusInWindow());
    }
}
