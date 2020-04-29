package kafka.streams.interactive.query.services;


import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.mddarr.orders.event.dto.Order;
import org.mddarr.products.Product;
import org.mddarr.products.PurchaseCount;
import org.mddarr.products.PurchaseEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class OrdersProcessingService {
    public static class OrdersProcessor {
        @Bean
        public Function<KStream<String, Order>, KStream<String, Order>> ordersprocess() {
            return orders -> orders;
        }
    }
}
//        @Bean
//        public BiFunction<KStream<String, Order>, KTable<String, Product>, KStream<String, Order>> ordersprocess() {
//            return (purchaseStream, productTable) -> {
//                // create and configure the SpecificAvroSerdes required in this example
//                final Map<String, String> serdeConfig = Collections.singletonMap(
//                        AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
//
//                final SpecificAvroSerde<Order> orderEventSerde = new SpecificAvroSerde<>();
//                orderEventSerde.configure(serdeConfig, false);
//
//                //  Key value Serde for Products
//                final SpecificAvroSerde<Product> keyProductSerde = new SpecificAvroSerde<>();
//                keyProductSerde.configure(serdeConfig, true);
//
//                final SpecificAvroSerde<Product> valueProductSerde = new SpecificAvroSerde<>();
//                valueProductSerde.configure(serdeConfig, false);
//
//
//
//                final SpecificAvroSerde<Order> orderSerde = new SpecificAvroSerde<>();
//                orderSerde.configure(serdeConfig, false);
//
//                final KStream<String, PurchaseEvent> purchasesByProductId =
//                        purchaseStream.map((key, value) -> KeyValue.pair(value.getId(), value));
//
//                // join the purchases with product as we will use it later for charting
//                final KStream<String, Product> productPurchases = purchasesByProductId
//                        .leftJoin(productTable, (value1, product) -> product,
//                                Joined.with(Serdes.String(), purchaseEventSerde, valueProductSerde));
//                // create a state store to track product purchase counts
//                final KStream<String, Long> productPurchaseCounts = purchasesByProductId.groupBy((k,v) -> k).count().toStream();
//
//                return productPurchaseCounts;
//            };
//
//        }
//    }
//
//}
