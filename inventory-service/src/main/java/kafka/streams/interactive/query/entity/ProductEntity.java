package kafka.streams.interactive.query.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String brand;
    private Long price;
    private Long quantity;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductEntity(){

    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public ProductEntity(String name, String brand, Long price) {
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    public ProductEntity(String id, String name, String brand, Long price, Long quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getBrand() {return brand;}
    public void setBrand(String brand) {this.brand = brand;}
    public String getName() { return name;}
    public void setName(String name) {this.name = name; }


}
