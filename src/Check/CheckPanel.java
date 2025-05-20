package src.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

/**
 * 건강 상태 목록을 아이콘 + 텍스트로 3열로 보여주는 패널
 */
public class CheckPanel extends JPanel {

    private JFrame parentFrame; // 패널을 감싸고 있는 JFrame 참조

    // 생성자: 프레임을 전달받아 저장
    public CheckPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        // 그리드 레이아웃: 3열, 요소 간 10px 간격
        setLayout(new GridLayout(0, 3, 10, 10));

        // 건강 상태 리스트 가져오기
        List<Check> checkList = Checklist.getAllCheck();

        // 각 건강 항목에 대해 버튼 생성
        for (Check check : checkList) {
            JButton button = new JButton(); // 버튼 생성
            button.setPreferredSize(new Dimension(800, 600)); // 버튼 크기

            // 버튼 스타일 설정 (배경·테두리 제거)
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setOpaque(false);

            // 텍스트 설정
            button.setText(check.getName());
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setFont(new Font("Bauhaus93", Font.PLAIN, 11));

            // 이미지 로딩 시도
            URL imageUrl = getClass().getResource("/image/" + check.getName() + ".png");
            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage)); // 아이콘 적용
            }

            // 버튼 클릭 시 추천 영양제 패널로 전환
            button.addActionListener(e -> {
                RecommendationPanel recommendationPanel = new RecommendationPanel(check, evt -> {
                    // 뒤로가기 버튼 클릭 시 다시 CheckPanel 로딩
                    parentFrame.setContentPane(new CheckPanel(parentFrame));
                    parentFrame.revalidate();
                    parentFrame.repaint();
                });

                // 현재 패널을 RecommendationPanel로 교체
                parentFrame.setContentPane(recommendationPanel);
                parentFrame.revalidate();
                parentFrame.repaint();
            });

            // 버튼을 패널에 추가
            add(button);
        }
    }
}