package frame;

import cart.Cart;
import cart.CartItem;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CartPanel extends JPanel {

    private JPanel itemPanel;
    private JLabel totalLabel;
    private JButton backButton;
    private JButton clearButton;

    private Cart cart;
    private UtilPanel utilPanel = new UtilPanel();

    public CartPanel(Cart cart) {
        this.cart = cart;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 총 금액 영역
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        totalLabel = new JLabel();
        totalLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(totalLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // 상품 목록 패널
        itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(itemPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // 하단 버튼
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        clearButton = new JButton("전체 삭제");
        backButton = new JButton("이전");

        clearButton.setBackground(new Color(240, 70, 70));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        clearButton.setPreferredSize(new Dimension(100, 40));

        backButton = new JButton("이전");
        backButton.setBackground(new Color(128, 128, 128));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        backButton.setPreferredSize(new Dimension(80, 40));

        backButton.addActionListener(e -> utilPanel.goBackPage());
        clearButton.addActionListener(e -> {
            cart.clear(); // 전체 삭제
            refreshCartView();
        });

        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshCartView();
    }

    private void refreshCartView() {
        itemPanel.removeAll();

        for (CartItem item : cart.getItems()) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

            // 왼쪽: 이미지
            JLabel imageLabel = new JLabel();
            URL imageUrl = getClass().getResource("/image/" + item.getProduct().getName() + ".png");
            if (imageUrl != null) {
                imageLabel.setPreferredSize(new Dimension(100, 100));
                ImageIcon img = new ImageIcon(imageUrl);
                Image scaled = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaled));
            }
            card.add(imageLabel, BorderLayout.WEST);

            // 가운데: 정보 및 수량 조절
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

            JLabel nameLabel = new JLabel(item.getProduct().getName());
            nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

            JLabel priceLabel = new JLabel((item.getProduct().getPrice()) + "원" );
            priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            priceLabel.setForeground(new Color(108, 0, 255));

            JLabel totalPriceLabel = new JLabel("총 금액: " + (item.getProduct().getPrice() * item.getQuantity()) + "원");
            totalPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            totalPriceLabel.setForeground(new Color(108, 0, 255));


            // 수량 스피너
            SpinnerNumberModel spinnerModel = new SpinnerNumberModel(item.getQuantity(), 1, 99, 1);
            JSpinner quantitySpinner = new JSpinner(spinnerModel);
            quantitySpinner.setPreferredSize(new Dimension(60, 25));
            quantitySpinner.addChangeListener(e -> {
                int newQuantity = (int) quantitySpinner.getValue();
                item.setQuantity(newQuantity);
                refreshCartView();
            });

            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            quantityPanel.setBackground(Color.WHITE);
            quantityPanel.add(new JLabel("수량: "));
            quantityPanel.add(quantitySpinner);

            infoPanel.add(nameLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(priceLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(totalPriceLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(quantityPanel);

            // 오른쪽: 삭제 버튼
            JButton deleteBtn = new JButton("삭제");
            deleteBtn.setBackground(new Color(240, 70, 70));
            deleteBtn.setForeground(Color.WHITE);
            deleteBtn.setFocusPainted(false);
            deleteBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            deleteBtn.setPreferredSize(new Dimension(70, 30));
            deleteBtn.addActionListener(e -> {
                cart.removeItem(item.getProduct());
                refreshCartView();
            });

            JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightPanel.setBackground(Color.WHITE);
            rightPanel.add(deleteBtn);

            card.add(infoPanel, BorderLayout.CENTER);
            card.add(rightPanel, BorderLayout.EAST);

            itemPanel.add(card);
            itemPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        totalLabel.setText("총 금액: ₩" + cart.getTotalPrice());

        revalidate();
        repaint();
    }
}
