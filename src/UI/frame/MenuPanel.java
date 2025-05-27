package UI.frame;

import cal.exercise.ExercisePanel;
import cal.food.FoodPanel;
import check.CheckPanel;
import store.frame.StorePanel;
import store.frame.UtilPanel;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class MenuPanel extends JPanel {

    private Image background;
    private Color themeColor = new Color(34, 139, 34);
    private Font themeFont = new Font("휴먼둥근헤드라인",Font.PLAIN,40);
    private Dimension btnSize = new Dimension(120, 80);
    private UtilPanel utilPanel = new UtilPanel();

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

        //CaloriePanel 추가(왼쪽) -- 메뉴페널 외부로 수정 중
        CaloriePanel caloriePanel = new CaloriePanel();
        caloriePanel.setPreferredSize(new Dimension(250, 250));
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 3;
        c.insets = new Insets(0, 0, 0, 40); //오른쪽 여백
        c.anchor = GridBagConstraints.CENTER;
        add(caloriePanel, c);

        //버튼들(오른쪽)

        JButton dietBtn = new JButton("식단");
        dietBtn.setFont(themeFont);
        dietBtn.setBorder(BorderFactory.createLineBorder(themeColor, 3));
        dietBtn.setPreferredSize(btnSize);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 10, 10);
        add(dietBtn, c);

        JButton exerciseBtn = new JButton("운동");
        exerciseBtn.setFont(themeFont);
        exerciseBtn.setPreferredSize(btnSize);
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 10, 0);
        add(exerciseBtn, c);

        JButton healthBtn = new JButton("상태");
        healthBtn.setFont(themeFont);
        healthBtn.setPreferredSize(btnSize);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 10, 10);
        add(healthBtn, c);

        JButton boardBtn = new JButton("기록");
        boardBtn.setFont(themeFont);
        boardBtn.setPreferredSize(btnSize);
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 10, 0);
        add(boardBtn, c);

        JButton shopBtn = new JButton("상점");
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
            btn.setBorder(BorderFactory.createLineBorder(themeColor, 3));
        }

//        //버튼 클릭 시 패널 전환
//        dietBtn.addActionListener(e -> centerPanel.showPanel("food"));
//        exerciseBtn.addActionListener(e -> centerPanel.showPanel("exercise"));
//        boardBtn.addActionListener(e -> centerPanel.showPanel("board"));
//        healthBtn.addActionListener(e -> centerPanel.showPanel("check"));
//        shopBtn.addActionListener(e -> centerPanel.showPanel("store"));


        dietBtn.addActionListener(e -> utilPanel.switchPanel(centerPanel, new FoodPanel()));
        exerciseBtn.addActionListener(e -> utilPanel.switchPanel(centerPanel, new ExercisePanel()));
        boardBtn.addActionListener(e -> utilPanel.switchPanel(centerPanel, new BoardPanel()));
        healthBtn.addActionListener(e -> utilPanel.switchPanel(centerPanel, new CheckPanel()));
        shopBtn.addActionListener(e -> utilPanel.switchPanel(centerPanel, new StorePanel()));

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
    