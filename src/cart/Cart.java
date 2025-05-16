package cart;

import product.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {


    public Cart() {
    }

    private static List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.print("current cartList: ");
                System.out.print(item.getProduct().getName());
                System.out.println(item.getQuantity());
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public void deleteProduct(Product product) {
        items.removeIf(item -> item.getProduct().getName().equals(product.getName()));
    }

    public void updateProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                System.out.println("수량 변경 전 " + item.getQuantity());
                item.setQuantity(quantity);
                System.out.println("수량 변경 후 " + item.getQuantity());
            }
        }
    }

    public void clear() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return items;

    }

    public int getTotalPrice() {
        return items.stream()
                .mapToInt(item -> (int) (item.getProduct().getPrice() * item.getQuantity()))
                .sum();
    }


    // 특정 상품 삭제
    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

}
