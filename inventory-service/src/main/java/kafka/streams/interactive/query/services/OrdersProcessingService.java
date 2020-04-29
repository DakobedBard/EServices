package kafka.streams.interactive.query.services;


import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.mddarr.orders.event.dto.Order;
import org.mddarr.products.Product;
import org.mddarr.products.PurchaseCount;
import org.mddarr.products.PurchaseEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class OrdersProcessingService {
    public static class OrdersProcessor {
        @Bean
        public Function<KStream<String, Order>,  KStream<String, Order>> ordersprocess() {

            Predicate<String, Product> isEnglish = (k, v) -> {
                return v.getBrand().equals("Nike");
            };
            Predicate<String, Product> isFrench = (k, v) -> v.getBrand().equals("Addidas");
            Predicate<String, Product> isSpanish = (k, v) -> v.getBrand().equals("Under Armour");


            return (orderStream) ->{



                orderStream.foreach(new ForeachAction() {
                    @Override
                    public void apply(Object key, Object value) {
                        System.out.print("THe key value is .. ");
                        System.out.println(key + ": " + value);
                    }
                });
                return orderStream;
            };

        }
    }
}
