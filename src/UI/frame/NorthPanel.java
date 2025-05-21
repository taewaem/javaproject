package UI.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NorthPanel extends JPanel {
    //상단에 고정(이름, 장바구니)

    private JLabel title;
    private JButton cartBtn;
    private JPanel titlePanel;
    private JPanel btnPanel;

    public NorthPanel(CenterPanel centerPanel) {
        setBackground(new Color(34,139,34));
        setLayout(null);
        
        //제목 라벨 세팅
        title = new JLabel("NutriLog");
        title.setFont(new Font("Bauhaus 93",Font.PLAIN,24));
        title.setForeground(Color.WHITE);
        //제목 클릭 시 메인메뉴로
        title.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                centerPanel.showPanel("menu");
            }
            
        });
        //제목 패널
        titlePanel = new JPanel(null);
        titlePanel.setOpaque(false);
        titlePanel.add(title);
        add(titlePanel);
        
        // 장바구니 버튼 세팅
        cartBtn = new JButton("Cart");
        cartBtn.setPreferredSize(new Dimension(120,40));
        cartBtn.setFont(new Font("Bauhaus 93",Font.PLAIN,16));
        cartBtn.setBackground(Color.WHITE);
        cartBtn.setForeground(Color.BLACK);
        cartBtn.setFocusPainted(false);
        cartBtn.setBorderPainted(false);
        cartBtn.addActionListener(e -> centerPanel.showPanel("cart"));
        //버튼 패널
        btnPanel = new JPanel(null);
        btnPanel.setOpaque(false);
        btnPanel.add(cartBtn);
        add(btnPanel);

        setPreferredSize(new Dimension(800, 60));
    }

    @Override
    public void doLayout() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        //title 크기 계산
        FontMetrics fmTitle = title.getFontMetrics(title.getFont());
        int titleWidth = fmTitle.stringWidth(title.getText());
        int titleHeight = fmTitle.getHeight();
        title.setBounds(0, 0, titleWidth, titleHeight);
        //titlePanel 위치 중앙에 정렬
        int titlePanelX = (panelWidth-titleWidth)/2;
        int titlePanelY = (panelHeight-titleHeight)/2;
        titlePanel.setBounds(titlePanelX, titlePanelY, titleWidth, titleHeight);

        //cartBtn 크기 계산
        FontMetrics fmBtn = cartBtn.getFontMetrics(cartBtn.getFont());
        int btnTextWidth = fmBtn.stringWidth(cartBtn.getText());
        int btnTextHeight = fmBtn.getHeight();
        int paddingX = 20;
        int paddingY = 10;
        int btnWidth = btnTextWidth + paddingX * 2;
        int btnHeight = btnTextHeight + paddingY;
        cartBtn.setBounds(0, 0, btnWidth, btnHeight);
        //btnPanel 위치 titlePanel 오른쪽에 Npx 띄우고 수직 중앙 정렬
        int btnPanelX = titlePanelX + titleWidth + 10;  //titlePanelX = titleWidth + N;
        int btnPanelY = (panelHeight - btnHeight) / 2;
        btnPanel.setBounds(btnPanelX, btnPanelY, btnWidth, btnHeight);
    }

    
}
