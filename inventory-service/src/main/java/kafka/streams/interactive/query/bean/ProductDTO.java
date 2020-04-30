package kafka.streams.interactive.query.bean;

public class ProductDTO {
    String name;
    String brand;
    Long price;
    Long quantity;

    public Long getQuantity() {
        return quantity;
    }

    public ProductDTO(String name, String brand, Long price, Long quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
}
