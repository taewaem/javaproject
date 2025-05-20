//package store.frame;
//
//import javax.swing.*;
//import java.awt.*;
//import java.net.URL;
//
//public class BackgroundPanel extends JPanel {
//    private Image background;
//
//    public BackgroundPanel() {
//        try {
//            URL url = getClass().getResource("/store/check.image/background.jpg"); // 이미지 경로
//            background = new ImageIcon(url).getImage();
//        } catch (Exception e) {
//            System.out.println("배경 이미지 불러오기 실패: " + e.getMessage());
//        }
//        setOpaque(false); // 배경 패널도 투명 지정
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (background != null) {
//            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
//        }
//    }
//}
