package check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * 선택한 건강 상태에 맞는 추천 영양제를 보여주는 패널
 */
public class RecommendationPanel extends JPanel {

    // 생성자: 전달받은 건강 상태와 뒤로가기 이벤트 처리
    public RecommendationPanel(Check check, ActionListener onBack) {
        setLayout(new BorderLayout());

        // 상단 패널: 뒤로가기 / 장바구니 버튼
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("뒤로가기");
        JButton cartButton = new JButton("장바구니");

        // 상단 버튼들 패널에 추가
        topPanel.add(backButton);
        topPanel.add(cartButton);

        // 중앙 텍스트: 선택된 건강 상태 이름 표시
        JLabel titleLabel = new JLabel("영양제 이름 (" + check.getName() + ")", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // 이미지 영역 라벨 생성
        JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // 이미지 로딩 시도
        URL imageUrl = getClass().getResource("check/check.image/" + check.getName() + ".png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        } else {
            imageLabel.setText("이미지를 찾을 수 없습니다.");
        }

        // 뒤로가기 버튼 클릭 시 전달받은 ActionListener 실행
        backButton.addActionListener(onBack);

        // 패널에 구성 요소 배치
        add(topPanel, BorderLayout.NORTH);
        add(titleLabel, BorderLayout.CENTER);
        add(imageLabel, BorderLayout.SOUTH);
    }
}