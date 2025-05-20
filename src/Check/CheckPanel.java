package src.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

public class CheckPanel extends JPanel {

    private JFrame parentFrame;
    private Image backgroundImage;

    public CheckPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        // 배경 이미지 로딩
        URL bgUrl = getClass().getResource("/src/image/Background.jpg");
        if (bgUrl != null) {
            backgroundImage = new ImageIcon(bgUrl).getImage();
        }

        // 레이아웃 설정
        setLayout(new GridLayout(2, 5, 10, 10));
        setOpaque(false); // 배경 투명하게 설정

        // 건강 항목 가져오기
        List<Check> checkList = Checklist.getAllCheck();

        for (Check check : checkList) {
            JButton button = createHealthButton(check);
            add(button);
        }
    }

    // 버튼 생성 메서드
    private JButton createHealthButton(Check check) {
        JButton button = new JButton(check.getName());
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setFont(new Font("SansSerif", Font.PLAIN,15));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setForeground(new Color(0, 0, 0)); // RGB로 지정

        // 아이콘 설정
        URL imageUrl = getClass().getResource("/src/image/" + check.getName() + ".png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        }

        button.addActionListener(e -> {// 버튼 클릭 시 다음화면
            RecommendationPanel panel = new RecommendationPanel(check, evt -> {
                parentFrame.setContentPane(new CheckPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            });
            parentFrame.setContentPane(panel);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        return button;
    }

    // 배경 이미지 그리기
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}