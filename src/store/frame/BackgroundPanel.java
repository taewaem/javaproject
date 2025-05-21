package store.frame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BackgroundPanel {

    private Image backgroundImage;

    // 배경 이미지를 설정하는 생성자
    public BackgroundPanel() {
        URL bgUrl = getClass().getResource("/store/image/Background.jpg");
        if (bgUrl != null) {
            backgroundImage = new ImageIcon(bgUrl).getImage();
        } else {
            System.out.println("배경 이미지 로딩 실패");
        }
    }

    // 배경 이미지를 그리는 메서드
    public void paintBackground(Graphics g, JPanel panel) {
        if (backgroundImage != null) {
            // 배경 이미지를 패널 크기에 맞게 그리기
            g.drawImage(backgroundImage, 0, 0, panel.getWidth(), panel.getHeight(), panel);
        }
    }
}
