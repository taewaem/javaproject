package check;

import store.frame.BackgroundPanel;
import store.frame.ProductPanel;
import store.frame.UtilPanel;
import store.product.Product;
import store.product.ProductService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class CheckPanel extends JPanel {

    private List<Check> checks = Checklist.getAllCheck();
    private ProductService productService = new ProductService();
    private JPanel checkPanel;
    private BackgroundPanel backgroundPanel;
    private UtilPanel utilPanel = new UtilPanel();

    public CheckPanel() {

        backgroundPanel = new BackgroundPanel();
        setLayout(new BorderLayout());
        setOpaque(false);

        // 중앙 패널 (영양제 목록)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        add(centerPanel, BorderLayout.CENTER);

        JLabel checkLabel = new JLabel("어떤 부분을 개선하고 싶으세요?");
        checkLabel.setFont(new Font("NanumSquareRound", Font.BOLD, 18));
        checkLabel.setForeground(new Color(110, 50, 220));
        checkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        centerPanel.add(checkLabel);

        checkPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        checkPanel.setOpaque(false);
        addChecklist();
        centerPanel.add(checkPanel);

        setVisible(true);
    }

    private void addChecklist() {
        for (Check check : checks) {

            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setPreferredSize(new Dimension(140, 170));
            itemPanel.setOpaque(false);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            // 이미지 버튼
            URL imageUrl = getClass().getResource("/check/image/" + check.getName() + ".png");
            JButton btn;
            if (imageUrl != null) {
                ImageIcon img = new ImageIcon(imageUrl);
                Image scaled = img.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                btn = new JButton(new ImageIcon(scaled));
            } else {
                btn = new JButton("이미지 없음");
            }
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // 마우스 오버 효과 (입체감)
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBorder(BorderFactory.createLineBorder(new Color(180, 120, 255), 3, true));
                    btn.setLocation(btn.getX(), btn.getY() - 3);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBorder(null);
                    btn.setLocation(btn.getX(), btn.getY() + 3);
                }
            });

            // 버튼 클릭 시 영양제 추천 페이지로 이동
            btn.addActionListener(e -> showRecommendedSupplements(check));

            // 건강 상태명 라벨
            JLabel nameLabel = new JLabel(check.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("NanumSquareRound", Font.BOLD, 14));
            nameLabel.setForeground(new Color(90, 40, 180));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            nameLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

            itemPanel.add(btn);
            itemPanel.add(nameLabel);

            checkPanel.add(itemPanel);
        }

        checkPanel.revalidate();
        checkPanel.repaint();
    }

    private void showRecommendedSupplements(Check check) {
        System.out.println("선택된 건강 상태: " + check.getName());

        // 해당 건강 상태에 맞는 추천 영양제 패널로 이동
        Product recommendedProducts = productService.getProduct(check.getDescription());
        if (recommendedProducts != null) {
            utilPanel.switchPanel(this, new ProductPanel(recommendedProducts));
        } else {
            JOptionPane.showMessageDialog(this, "추천 영양제가 없습니다.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 기본적인 페인팅 작업
        backgroundPanel.paintBackground(g, this); // 배경 그리기
    }
}
