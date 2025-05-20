package store.product;


public class Product {
    private String name;
    private int price;

    private ProductDescription description;

    public Product(String name, int price, ProductDescription description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductDescription getProductDescription() {
        return description;
    }

    public void setDescription(ProductDescription description) {
        this.description = description;
    }

}
