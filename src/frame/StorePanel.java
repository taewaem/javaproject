package frame;

import product.Product;
import product.ProductService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;


public class StorePanel extends JPanel{

    private ProductService productService = new ProductService();

    private List<Product> products = productService.getAllProduct();

    private UtilPanel utilPanel = new UtilPanel();
    private JTextField searchField;
    private JPanel cartPanel;
    private JPanel productPanel;

    public StorePanel() {

        setLayout(new BorderLayout());

        /**
         * 상단 컨테이너
         */
        JLabel title = new JLabel("Store", SwingConstants.CENTER);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        add(title, BorderLayout.NORTH);


        /**
         * 중앙 컨테이너
         * 영양제 목록
         */
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        add(centerPanel, BorderLayout.CENTER);

        //영양제 구현
        JLabel productLabel = new JLabel("영양제");
        productLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        productLabel.setAlignmentX(Component.CENTER_ALIGNMENT);               //가운데로 정렬
        centerPanel.add(productLabel);
        productPanel = new JPanel(new GridLayout(2, 5, 5, 5));
        addProductItems();  // 영양제 항목 추가
        centerPanel.add(productPanel);

        setVisible(true);
    }


    //영양제 목록 추가
    private void addProductItems() {

        for (Product product : products) {

            JPanel sp = new JPanel();
            sp.setSize(new Dimension(170, 170));

            //이미지 불러오기
            URL imageUrl = getClass().getResource("/image/" + product.getName() + ".png");
            if (imageUrl != null) {
                ImageIcon img = new ImageIcon(imageUrl);
                Image scaled = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

                // 버튼 생성
                JButton btn = new JButton(new ImageIcon(scaled));
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setPreferredSize(new Dimension(100, 100));
                btn.addActionListener(e -> showProductDetail(product.getName()));

                // 텍스트 라벨 생성
                JLabel label = new JLabel(product.getName(), SwingConstants.LEFT);
                label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));

                //가격 라벨 생성
                JLabel priceLabel = new JLabel(String.valueOf(product.getPrice()) + "원", SwingConstants.LEFT);
                priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
                priceLabel.setForeground(new Color(108, 0, 255));


                // 버튼 + 라벨 + 가격을 담을 panel 생성
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS)); // 세로 정렬
                itemPanel.setPreferredSize(new Dimension(120, 100));
                itemPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                itemPanel.setOpaque(false); // 배경 투명

                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                itemPanel.add(btn);
                itemPanel.add(label);
                itemPanel.add(priceLabel);

                productPanel.add(itemPanel);

            }
            else {
                System.out.println("image doesn't exist");
            }
        }
    }


    private void searchProduct(String query) {
        if (query.equalsIgnoreCase("비타민C")) {
            showProductDetail("비타민C");
        } else {

            //상세페이지로 화면 전환 코드

            JOptionPane.showMessageDialog(this, query + " 해당 상품이 없습니다.");
        }
    }

    private void showProductDetail(String productName) {
//        JOptionPane.showMessageDialog(this, productName + " 상세 페이지로 이동합니다.");
        System.out.println("선택된 영양제: " + productName);
        for (Product p : products) {
            if (!productName.equals(p.getName())) {
            }
            else {
                System.out.println(p.getName() + " page");

                utilPanel.goToPage(new ProductPanel(p));    //ProductPanel로 이동
            }
        }
    }

}