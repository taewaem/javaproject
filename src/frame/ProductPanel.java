package frame;

import cart.Cart;
import product.Product;

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


        JPanel panel = new JPanel(new BorderLayout());

        // 왼쪽: 이미지패널
        JLabel imageLabel = new JLabel();
        URL imageUrl = getClass().getResource("/image/" + product.getName() + ".png");
        if (imageUrl != null) {
            ImageIcon img = new ImageIcon(imageUrl);
            Image scaled = img.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        }
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        panel.add(imagePanel, BorderLayout.WEST);

        /**
         * 오른쪽: 정보 패널
         * 제품명, 설명, 가격, 버튼들
         */
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        // 제품명
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));

        // 설명
        JLabel mainDescriptionLabel = new JLabel("주요 기능");
        mainDescriptionLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        JLabel descriptionLabel = new JLabel(product.getDescription());
        descriptionLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));

        // 가격
        JLabel priceLabel = new JLabel(product.getPrice() + "원");
        priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        priceLabel.setForeground(new Color(108, 0, 255));


        // 수량 조절
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel quantityLabel = new JLabel("수량:");
        quantityLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 99, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setPreferredSize(new Dimension(50, 25));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantitySpinner);

        //장바구니 버튼
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cartButton = new JButton("장바구니");
        cartButton.setBackground(new Color(108, 0, 255));
        cartButton.setForeground(Color.WHITE);
        cartButton.setFocusPainted(false);
        cartButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        cartButton.setPreferredSize(new Dimension(100, 40));

        //이전 버튼
        backButton = new JButton("이전");
        backButton.setBackground(new Color(128, 128, 128));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(80, 40));


        //장바구니 버튼 클릭시
        cartButton.addActionListener(e -> {
            int quantity = (Integer) quantitySpinner.getValue();

            cart.addProduct(product, quantity);
            System.out.println(quantity + "개 " + product.getName() + " 장바구니에 추가");

            //장바구니 패널 이동( 삭제할 것 따로 장바구니 버튼 존재)
            utilPanel.goToPage(new CartPanel(cart));
        });

        //이전 버튼 클릭 시
        backButton.addActionListener(e -> {
            utilPanel.goBackPage();
            System.out.println("이전 페이지로 이동");
        });



        buttonPanel.add(cartButton);
        buttonPanel.add(backButton);


        // 구성 요소 추가
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(30));
        infoPanel.add(mainDescriptionLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(descriptionLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(quantityPanel);
        infoPanel.add(buttonPanel);

        panel.add(infoPanel, BorderLayout.EAST);
        add(panel);
    }
}
