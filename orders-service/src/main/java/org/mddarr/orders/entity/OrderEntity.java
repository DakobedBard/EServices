package org.mddarr.orders.entity;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class OrderEntity {

    @Id
    String id;
    @ElementCollection
    List<String> products;
    @ElementCollection
    List<Long> quantities;

    @ElementCollection
    List<Double> prices;

    @ElementCollection
    List<String> orders;

    Double total_price;
    String customerID;
    String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public OrderEntity(){

    }

    public OrderEntity(String id, List<String> products, List<Long> quantities, List<Double> prices,
                       List<String> orderIDs, Double total_price, String customerID, String state) {
        this.id = id;
        this.products = products;
        this.quantities = quantities;
        this.prices = prices;
        this.orders = orderIDs;
        this.total_price = total_price;
        this.customerID = customerID;
        this.state = state;
    }

    public List<String> getOrders() {return orders; }

    public void setOrders(List<String> orders) {this.orders = orders;}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<String> getProducts() {
        return products;
    }
    public void setProducts(List<String> products) {
        this.products = products;
    }
    public List<Long> getQuantities() {
        return quantities;
    }
    public void setQuantities(List<Long> quantities) {
        this.quantities = quantities;
    }
    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    public Double getTotal_price() {
        return total_price;
    }
    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }


}
