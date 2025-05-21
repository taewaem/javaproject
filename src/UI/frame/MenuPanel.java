package UI.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class MenuPanel extends JPanel {

    private Image background;
    private Color themeColor = new Color(34, 139, 34);
    private Font themeFont = new Font("Bauhaus 93",Font.PLAIN,30);
    private Dimension btnSize = new Dimension(100, 60);

    public MenuPanel(CenterPanel centerPanel) {

        //배경이미지 불러오기
        URL imageURL = getClass().getResource("/img/Background.jpg");
        try{
            background = ImageIO.read(imageURL);
        }catch(IOException e){
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        setBackground(themeColor);

        GridBagConstraints c = new GridBagConstraints();

        // CaloriePanel 추가(왼쪽)
        CaloriePanel caloriePanel = new CaloriePanel();
        caloriePanel.setPreferredSize(new Dimension(180, 180));
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 3;
        c.insets = new Insets(0, 0, 0, 40); //오른쪽 여백
        c.anchor = GridBagConstraints.CENTER;
        add(caloriePanel, c);

        //버튼들(오른쪽)
        
        JButton dietBtn = new JButton("D");
        dietBtn.setFont(themeFont);
        dietBtn.setPreferredSize(btnSize);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 10, 10);
        add(dietBtn, c);

        JButton exerciseBtn = new JButton("E");
        exerciseBtn.setFont(themeFont);
        exerciseBtn.setPreferredSize(btnSize);
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 10, 0);
        add(exerciseBtn, c);

        JButton healthBtn = new JButton("H");
        healthBtn.setFont(themeFont);
        healthBtn.setPreferredSize(btnSize);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 10, 10);
        add(healthBtn, c);

        JButton boardBtn = new JButton("B");
        boardBtn.setFont(themeFont);
        boardBtn.setPreferredSize(btnSize);
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 10, 0);
        add(boardBtn, c);

        JButton shopBtn = new JButton("S");
        shopBtn.setFont(themeFont);
        shopBtn.setPreferredSize(btnSize);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        add(shopBtn, c);

        JButton[] buttons = {dietBtn, exerciseBtn, healthBtn, boardBtn, shopBtn};
        for(JButton btn : buttons){
            btn.setBackground(Color.WHITE);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
        }

        //버튼 클릭 시 패널 전환
        dietBtn.addActionListener(e -> centerPanel.showPanel("diet"));
        exerciseBtn.addActionListener(e -> centerPanel.showPanel("exercise"));
        boardBtn.addActionListener(e -> centerPanel.showPanel("board"));
        healthBtn.addActionListener(e -> centerPanel.showPanel("health"));
        shopBtn.addActionListener(e -> centerPanel.showPanel("shop"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(background != null){
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }else{
            //이미지가 없을 경우 배경색으로 대체
            g.setColor(themeColor);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    
}
    