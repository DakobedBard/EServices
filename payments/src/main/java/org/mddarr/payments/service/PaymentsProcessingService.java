//package org.mddarr.payments.service;
//
//import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
//import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.kstream.Joined;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KTable;
//import org.mddarr.orders.event.dto.Order;
//import org.mddarr.orders.event.dto.OrderState;
//import org.mddarr.orders.event.dto.ValidatedOrder;
//import org.mddarr.products.Product;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.Map;
//import java.util.function.BiFunction;
//
//
//@Service
//public class PaymentsProcessingService {
////    public class OrdersProcessor {
////        @Bean
////        public BiFunction<KStream<String, Order>, KTable<String, Product>, KStream<String, ValidatedOrder>> ordersprocess() {
////
////            return (orderStream, productTable) -> {
////
////                final Map<String, String> serdeConfig = Collections.singletonMap(
////                        AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
//////
////                final SpecificAvroSerde<Order> orderSerde = new SpecificAvroSerde<>();
////                orderSerde.configure(serdeConfig, false);
////
////                final SpecificAvroSerde<Product> keyProductSerde = new SpecificAvroSerde();
////                keyProductSerde.configure(serdeConfig, true);
////
////                final SpecificAvroSerde<Product> valueProductSerde = new SpecificAvroSerde<>();
////                valueProductSerde.configure(serdeConfig, false);
////
////                final SpecificAvroSerde<ValidatedOrder> validatedOrderSpecificAvroSerde = new SpecificAvroSerde<>();
////                validatedOrderSpecificAvroSerde.configure(serdeConfig, false);
////
////                // join the orders with product
////                final KStream<String, Order> ordersByProductId =
////                        orderStream.map((key, value) -> KeyValue.pair(value.getProductID(), value));
////
////                final KStream<String, ValidatedOrder> joinedProducts = ordersByProductId
////                        .leftJoin(productTable, (order, product) -> {
////                            System.out.println("debugging this shit");
////                            System.out.println("The product ID is " + product.getId() + " and the quantity is " + order.getQuantity() + "and the stock is " + product.getStock());
////                            return new ValidatedOrder(order.getId(), "b", OrderState.OUT_OF_STOCK, 5l, 5.3);
////                        }, Joined.with(Serdes.String(), orderSerde, valueProductSerde));
////                return joinedProducts;
////            };
////        }
////    }
//}