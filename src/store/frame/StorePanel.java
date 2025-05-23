
package store.frame;

import store.product.Product;
import store.product.ProductService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;


public class StorePanel extends JPanel {

    private ProductService productService = new ProductService();
    private List<Product> products = productService.getAllProduct();
    private JPanel productPanel;
    private BackgroundPanel backgroundPanel;
    private DecimalFormat df = new DecimalFormat("###,###");
    private UtilPanel utilPanel = new UtilPanel();

    public StorePanel() {



//        setBackground(new Color(255, 255, 255));
//        // 타이틀
//        JLabel title = new JLabel("영양제 상점", SwingConstants.CENTER);
//        title.setFont(new Font("NanumSquareRound", Font.BOLD, 28)); // 귀여운 둥근 폰트
//        title.setForeground(new Color(140, 80, 255));  // 밝은 보라색
//        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
//        add(title, BorderLayout.NORTH);

        backgroundPanel = new BackgroundPanel();

        setLayout(new BorderLayout());
        setOpaque(false);
        // 중앙 패널 (영양제 목록)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false); // 투명하게
        add(centerPanel, BorderLayout.CENTER);

        JLabel productLabel = new JLabel("🛒 영양제 목록 🛒");
        productLabel.setFont(new Font("NanumSquareRound", Font.BOLD, 18));
        productLabel.setForeground(new Color(110, 50, 220)); // 진한 보라
        productLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        centerPanel.add(productLabel);

        productPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        productPanel.setOpaque(false);
        addProductItems();
        centerPanel.add(productPanel);

        setVisible(true);
    }

    private void addProductItems() {
        productPanel.removeAll();

        for (Product product : products) {

            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setPreferredSize(new Dimension(140, 170));
            itemPanel.setOpaque(false);
            itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            // 이미지 버튼
            URL imageUrl = getClass().getResource("/store/image/" + product.getName() + ".png");
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

            btn.addActionListener(e -> showProductDetail(product.getName()));

            // 제품명 라벨
            JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("NanumSquareRound", Font.BOLD, 14));
            nameLabel.setForeground(new Color(90, 40, 180));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            nameLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

            // 가격 라벨
            int price = product.getPrice();
            String formattedPrice = df.format(price) + "원";
            JLabel priceLabel = new JLabel(formattedPrice, SwingConstants.CENTER);
            priceLabel.setFont(new Font("NanumSquareRound", Font.BOLD, 13));
            priceLabel.setForeground(new Color(140, 60, 255)); // 보라톤 유지
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            priceLabel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

            itemPanel.add(btn);
            itemPanel.add(nameLabel);
            itemPanel.add(priceLabel);

            productPanel.add(itemPanel);
        }

        productPanel.revalidate();
        productPanel.repaint();
    }

    private void showProductDetail(String productName) {
        System.out.println("선택된 영양제: " + productName);
        for (Product p : products) {
            if (productName.equals(p.getName())) {
                utilPanel.switchPanel(this, new ProductPanel(p));
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            backgroundPanel.paintBackground(g, this);
    }
}