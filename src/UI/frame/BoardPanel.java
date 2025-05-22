package UI.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class BoardPanel extends JPanel {
    
    private Image background;

    private JPanel listPanel;
    private JPanel btnPanel;
    private JButton writeBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private Color themeColor = new Color(34, 139, 34);
    private Dimension btnSize = new Dimension(200, 30);
    
    public BoardPanel() {

        //배경이미지 불러오기
        URL imageURL = getClass().getResource("/img/Background.jpg");
        try{
            background = ImageIO.read(imageURL);
        }catch(IOException e){
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(); 

        //listPanel(Board 첫 화면 가장 크게 보이는 패널)
        listPanel = new JPanel();
        listPanel.setBorder(BorderFactory.createLineBorder(themeColor, 3));
        listPanel.setForeground(Color.BLACK);
        listPanel.setBackground(Color.WHITE);
        listPanel.setPreferredSize(new Dimension(300, 300));
        c.gridx = 0;    // 첫 번째 열
        c.gridy = 0;    // 첫 번째 행
        c.gridheight = 3;   // 3개의 행 차지지
        c.insets = new Insets(10, 70, 10, 10);  //여백
        c.anchor = GridBagConstraints.CENTER;   // 중앙 정렬
        c.fill = GridBagConstraints.NONE;   //크기 조절 안함
        add(listPanel, c);

        //btnPanel
        btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
        btnPanel.setOpaque(false);  //투명배경

        writeBtn = createBtn("게시물 작성");
        editBtn = createBtn("게시물 수정");
        deleteBtn = createBtn("게시물 삭제");

        btnPanel.add(Box.createVerticalGlue()); //위 여백
        btnPanel.add(writeBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(0, 30)));    //간격
        btnPanel.add(editBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        btnPanel.add(deleteBtn);
        btnPanel.add(Box.createVerticalGlue()); //아래 여백

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        add(btnPanel, c);


        // //writeBtn
        // writeBtn = new JButton("게시물 작성");
        // writeBtn.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,17));
        // writeBtn.setPreferredSize(btnSize);
        // writeBtn.setBackground(themeColor);
        // writeBtn.setForeground(Color.WHITE);
        // writeBtn.setFocusPainted(false);
        // writeBtn.setBorderPainted(false);
        // c.gridx = 1;
        // c.gridy = 0;
        // c.gridheight = 1;
        // c.insets = new Insets(5, 10, 5, 10);
        // c.anchor = GridBagConstraints.CENTER;

        // //editBtn
        // editBtn = new JButton("게시물 수정");
        // editBtn.setFont(new Font("휴먼둥근헤드라인",Font.BOLD,17));
        // editBtn.setPreferredSize(btnSize);
        // editBtn.setBackground(themeColor);
        // editBtn.setForeground(Color.WHITE);
        // editBtn.setFocusPainted(false);
        // editBtn.setBorderPainted(false);
        // c.gridy = 1;

        // //deleteBtn
        // deleteBtn = new JButton("게시물 삭제");
        // deleteBtn.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 17));
        // deleteBtn.setPreferredSize(btnSize);
        // deleteBtn.setBackground(themeColor);
        // deleteBtn.setForeground(Color.WHITE);
        // deleteBtn.setFocusPainted(false);
        // deleteBtn.setBorderPainted(false);
        // c.gridy = 3;

    }

    private JButton createBtn(String text){
        JButton btn = new JButton(text);
        btn.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 17));
        btn.setPreferredSize(btnSize);
        btn.setBackground(themeColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);

        return btn;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(background != null){
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    
}