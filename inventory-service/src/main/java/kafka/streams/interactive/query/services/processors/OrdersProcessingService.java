package kafka.streams.interactive.query.services.processors;


import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.mddarr.orders.event.dto.Order;
import org.mddarr.orders.event.dto.OrderState;
import org.mddarr.products.Product;
import org.mddarr.products.PurchaseCount;
import org.mddarr.products.PurchaseEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class OrdersProcessingService {
    public class OrdersProcessor {
        @Bean
        public BiFunction<KStream<String, Order>, KTable<String, Product>, KStream<String, Order>> ordersprocess() {

            return (orderStream, productTable) ->{
                final Map<String, String> serdeConfig = Collections.singletonMap(
                        AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

                final SpecificAvroSerde<PurchaseEvent> purchaseEventSerde = new SpecificAvroSerde<>();
                purchaseEventSerde.configure(serdeConfig, false);

                final SpecificAvroSerde<Product> keyProductSerde = new SpecificAvroSerde<>();
                keyProductSerde.configure(serdeConfig, true);

                final SpecificAvroSerde<Product> valueProductSerde = new SpecificAvroSerde<>();
                valueProductSerde.configure(serdeConfig, false);

                final SpecificAvroSerde<PurchaseCount> productPurchaseCountSerde = new SpecificAvroSerde<>();
                productPurchaseCountSerde.configure(serdeConfig, false);
//                orderStream.foreach(new ForeachAction() {
//                    @Override
//                    public void apply(Object key, Object value) {
//                        System.out.print("THe key value of the orders stream is .. ");
//                        System.out.println(key + ": " + value);
//                    }
//                });
//                productTable.toStream().foreach(new ForeachAction() {
//                    @Override
//                    public void apply(Object key, Object value) {
//                        System.out.print("THe key value of othe product is .. ");
//                        System.out.println(key + ": " + value);
//                    }
//                });

                // join the orders with product
                final KStream<String, Order> ordersByProductId =
                        orderStream.map((key, value) -> KeyValue.pair(value.getProductID(), value));

//                final KStream<String, OrdersProduct> productPurchases = ordersByProductId.leftJoin(productTable,new OrdersProductJoiner());
                return orderStream;
            };
        }
    }
    public class OrdersProductJoiner implements ValueJoiner<Order, Product, OrdersProduct> {
        public OrdersProduct apply(Order order, Product product) {
            return new OrdersProduct(order.getId(), order.getProductID(), order.getQuantity(), product.getStock(), order.getPrice());
        }
    }
    public class OrdersProduct{
        String orderID;
        String productID;
        Long quantity;
        Double price;
        OrderState state;

        public OrdersProduct(String orderID, String productID, Long quantity, Long stock, Double price) {
            this.orderID = orderID;
            this.productID = productID;
            this.quantity = quantity;
            this.price = price;
            state = quantity > stock ? OrderState.OUT_OF_STOCK : OrderState.STOCKED;
        }

        public String getOrderID() { return orderID;}
        public void setOrderID(String orderID) { this.orderID = orderID;}
        public String getProductID() { return productID;}
        public void setProductID(String productID) {this.productID = productID; }
        public Long getQuantity() {return quantity;}
        public void setQuantity(Long quantity) { this.quantity = quantity;}
    }
}
