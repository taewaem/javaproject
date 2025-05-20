package store.frame;

import store.cart.Cart;
import store.frame.CartPanel;
import store.frame.UtilPanel;
import store.product.Product;
import store.product.ProductDescription;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ProductPanel extends JPanel {

    private UtilPanel utilPanel = new UtilPanel();
    private JButton cartButton;
    private JButton backButton;
    private Cart cart;


    public ProductPanel(Product product) {
        cart = new Cart();

        // 전체 패널 배경: 연한 아이보리
        setBackground(new Color(250, 250, 245));
        setLayout(new BorderLayout(30, 30));

        // 왼쪽 이미지 패널
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        URL imageUrl = getClass().getResource("/store/image/" + product.getName() + ".png");
        if (imageUrl != null) {
            ImageIcon img = new ImageIcon(imageUrl);
            Image scaled = img.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        }
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(245, 245, 240)); // 이미지 영역 배경: 아주 연한 회색
        imagePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setPreferredSize(new Dimension(300, 320));
        add(imagePanel, BorderLayout.WEST);

        // 오른쪽 정보 패널
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
        infoPanel.setBackground(new Color(250, 250, 245)); // 전체와 맞춤

        // 제품명
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        nameLabel.setForeground(new Color(40, 40, 40));  // 검정보단 조금 연한 다크그레이
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(25));

        // 제품 가격
        JLabel priceLabel = new JLabel(MainFrame.df.format(product.getPrice()) + " 원");
        priceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        priceLabel.setForeground(new Color(98, 0, 220)); // 조금 더 부드러운 보라톤
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(25));

        // 상세 설명 섹션 제목과 내용
        ProductDescription desc = product.getProductDescription();

        infoPanel.add(createSectionLabel("주요 기능"));
        infoPanel.add(createContentLabel(desc.getSummary()));

        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(createSectionLabel("설명"));
        infoPanel.add(createContentLabel(desc.getDetails()));

        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(createSectionLabel("복용법"));
        infoPanel.add(createContentLabel(desc.getUsage()));

        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(createSectionLabel("주의사항"));
        infoPanel.add(createContentLabel(desc.getCaution()));

        infoPanel.add(Box.createVerticalStrut(30));

        // 수량 조절 패널
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        quantityPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        quantityPanel.setBackground(new Color(250, 250, 245));
        JLabel quantityLabel = new JLabel("수량:");
        quantityLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        quantityLabel.setForeground(new Color(60, 60, 60));

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 99, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setPreferredSize(new Dimension(60, 28));

        // 스피너 폰트 및 색상 조정 (내부 에디터)
        JFormattedTextField tf = ((JSpinner.DefaultEditor) quantitySpinner.getEditor()).getTextField();
        tf.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        tf.setForeground(new Color(60, 60, 60));

        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantitySpinner);

        infoPanel.add(quantityPanel);
        infoPanel.add(Box.createVerticalStrut(25));

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setBackground(new Color(250, 250, 245));

        cartButton = new JButton("장바구니 담기");
        cartButton.setBackground(new Color(98, 0, 220));
        cartButton.setForeground(Color.WHITE);
        cartButton.setFocusPainted(false);
        cartButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        cartButton.setPreferredSize(new Dimension(140, 45));
        cartButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        cartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // 호버 효과 (간단히)
        cartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cartButton.setBackground(new Color(120, 30, 250));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cartButton.setBackground(new Color(98, 0, 220));
            }
        });

        backButton = new JButton("이전");
        backButton.setBackground(new Color(150, 150, 150));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(90, 45));
        backButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(180, 180, 180));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(150, 150, 150));
            }
        });

        buttonPanel.add(cartButton);
        buttonPanel.add(backButton);

        infoPanel.add(buttonPanel);

        add(infoPanel, BorderLayout.CENTER);

        // 버튼 기능
        cartButton.addActionListener(e -> {
            int quantity = (Integer) quantitySpinner.getValue();
            cart.addProduct(product, quantity);
            System.out.println(quantity + "개 " + product.getName() + " 장바구니에 추가");

            int result = JOptionPane.showConfirmDialog(null,
                    "장바구니로 이동하시겠습니까?",
                    "장바구니 이동",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                System.out.println("장바구니 페이지로 이동");
                utilPanel.goToPage(new CartPanel(cart));
            }
        });

        backButton.addActionListener(e -> {
            utilPanel.goBackPage();
            System.out.println("이전 페이지로 이동");
        });

        setPreferredSize(new Dimension(800, 420));
    }



    private JLabel createSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        label.setForeground(new Color(80, 80, 80));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel createContentLabel(String text) {
        JLabel label = new JLabel("<html><body style='width:420px;'>" + text + "</body></html>");
        label.setFont(new Font("Bauhaus 93", Font.PLAIN, 17));
        label.setForeground(new Color(70, 70, 70));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
}
