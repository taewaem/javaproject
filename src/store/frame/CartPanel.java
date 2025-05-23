package store.frame;

import store.cart.Cart;
import store.cart.CartItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class CartPanel extends JPanel {

    private JPanel itemPanel;
    private JLabel totalLabel;
    private JButton clearButton;
    private JButton buyButton;
    private JButton storeButton;
    private Cart cart;
    private BackgroundPanel backgroundPanel = new BackgroundPanel();
    private DecimalFormat df = new DecimalFormat("###,###");
    private UtilPanel utilPanel = new UtilPanel();

    private Map<CartItem, JCheckBox> itemCheckBoxes = new HashMap<>();

    public CartPanel(Cart cart) {
        this.cart = cart;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 상단 총 금액
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setOpaque(false);

        totalLabel = new JLabel();
        totalLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(totalLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // 아이템 목록
        itemPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                backgroundPanel.paintBackground(g, this);
            }
        };
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(itemPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // 하단 버튼 영역
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setOpaque(false);

        clearButton = new JButton("전체 삭제");
        buyButton = new JButton("결제하기");

        buyButton.setBackground(new Color(144, 238, 144));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFocusPainted(false);
        buyButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        buyButton.setPreferredSize(new Dimension(100, 40));

        clearButton.setBackground(new Color(240, 70, 70));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        clearButton.setPreferredSize(new Dimension(100, 40));


        storeButton = new JButton("상점");
        storeButton.setBackground(new Color(128, 128, 128));
        storeButton.setForeground(Color.WHITE);
        storeButton.setFocusPainted(false);
        storeButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        storeButton.setPreferredSize(new Dimension(100, 40));

        storeButton.addActionListener(e -> {
            utilPanel.switchPanel(this, new StorePanel());
        });

        buyButton.addActionListener(e -> {
            List<CartItem> selectedItems = new ArrayList<>();
            for (Map.Entry<CartItem, JCheckBox> entry : itemCheckBoxes.entrySet()) {
                if (entry.getValue().isSelected()) {
                    selectedItems.add(entry.getKey());
                }
            }

            if (selectedItems.isEmpty()) {
                JOptionPane.showMessageDialog(this, "선택된 항목이 없습니다.");
                return;
            }

            JOptionPane.showMessageDialog(this, " 구매 완료!");
            for (CartItem item : selectedItems) {
                cart.removeItem(item.getProduct());
            }

            refreshCartView();
        });

        clearButton.addActionListener(e -> {
            cart.clear();
            refreshCartView();
        });

        buttonPanel.add(storeButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshCartView();
    }

    private void refreshCartView() {
        // 기존 체크 상태 저장
        Map<CartItem, Boolean> checkedStates = new HashMap<>();
        for (Map.Entry<CartItem, JCheckBox> entry : itemCheckBoxes.entrySet()) {
            checkedStates.put(entry.getKey(), entry.getValue().isSelected());
        }

        itemPanel.removeAll();
        itemPanel.setOpaque(false);
        itemCheckBoxes.clear();

        for (CartItem item : cart.getItems()) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

            // 왼쪽: 체크박스 + 이미지
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
            leftPanel.setBackground(Color.WHITE);

            JCheckBox selectCheckBox = new JCheckBox();
            selectCheckBox.setBackground(Color.WHITE);

            // 이전 체크 상태 유지
            Boolean wasSelected = checkedStates.get(item);
            selectCheckBox.setSelected(wasSelected != null && wasSelected);

            selectCheckBox.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED || e.getStateChange() == ItemEvent.DESELECTED) {
                    updateSelectedTotal();
                }
            });

            JLabel imageLabel = new JLabel();
            URL imageUrl = getClass().getResource("/store/image/" + item.getProduct().getName() + ".png");
            if (imageUrl != null) {
                imageLabel.setPreferredSize(new Dimension(100, 100));
                ImageIcon img = new ImageIcon(imageUrl);
                Image scaled = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaled));
            }

            leftPanel.add(selectCheckBox);
            leftPanel.add(Box.createHorizontalStrut(10));
            leftPanel.add(imageLabel);
            card.add(leftPanel, BorderLayout.WEST);

            itemCheckBoxes.put(item, selectCheckBox);

            // 가운데: 상품 정보 + 수량 조절
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

            JLabel nameLabel = new JLabel(item.getProduct().getName());
            nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

            int price = item.getProduct().getPrice();
            String formatPrice = df.format(price);
            JLabel priceLabel = new JLabel(formatPrice + "원");
            priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            priceLabel.setForeground(new Color(108, 0, 255));

            int totalPrice = price * item.getQuantity();
            String formatTotalPrice = df.format(totalPrice);
            JLabel totalPriceLabel = new JLabel("총 금액: " + formatTotalPrice + "원");
            totalPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            totalPriceLabel.setForeground(new Color(108, 0, 255));

            SpinnerNumberModel spinnerModel = new SpinnerNumberModel(item.getQuantity(), 1, 99, 1);
            JSpinner quantitySpinner = new JSpinner(spinnerModel);
            quantitySpinner.setPreferredSize(new Dimension(60, 25));
            quantitySpinner.addChangeListener(e -> {
                item.setQuantity((int) quantitySpinner.getValue());
                refreshCartView(); // 체크 상태가 유지되도록 위에서 저장하고 복원
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
            rightPanel.add(Box.createHorizontalStrut(5));

            card.add(infoPanel, BorderLayout.CENTER);
            card.add(rightPanel, BorderLayout.EAST);

            itemPanel.add(card);
            itemPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        updateSelectedTotal();

        revalidate();
        repaint();
    }


    private void updateSelectedTotal() {
        int selectedTotal = 0;
        for (Map.Entry<CartItem, JCheckBox> entry : itemCheckBoxes.entrySet()) {
            if (entry.getValue().isSelected()) {
                CartItem item = entry.getKey();
                selectedTotal += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        String formatSelectedTotal = df.format(selectedTotal);
        totalLabel.setText("선택 항목 총 금액: " + formatSelectedTotal + "원");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        backgroundPanel.paintBackground(g, this);
    }
}
