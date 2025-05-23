package store.product;

import java.util.List;

public class ProductService {

    public List<Product> getAllProduct() {

        return ProductList.getAllProduct();
    }

    public Product getProduct(String name) {

        List<Product> allProduct = getAllProduct();
        for (Product product : allProduct) {
            if (name.equals(product.getName())) {
                return product;
            }
        }
        return null;
    }

}