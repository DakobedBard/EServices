package org.mddarr.orders.beans;

import org.springframework.context.annotation.Bean;

import java.util.List;

public class OrderDTO {
    List<String> products;
    List<Long> quantities;
    List<Double> prices;
    String customerID;

    public List<String> getProducts() { return products; }
    public void setProducts(List<String> products) { this.products = products; }
    public List<Long> getQuantities() {  return quantities;}
    public void setQuantities(List<Long> quantities) {this.quantities = quantities; }

    public String getCustomerID() {return customerID; }
    public void setCustomerID(String customerID) {this.customerID = customerID;}

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    public OrderDTO(){

    }

    public OrderDTO(List<String> products, List<Long> quantities, List<Double> prices, String customerID) {
        this.products = products;
        this.quantities = quantities;
        this.prices = prices;
        this.customerID = customerID;
    }
}
