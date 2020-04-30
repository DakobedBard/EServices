package kafka.streams.interactive.query.services.processors;


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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class OrdersProcessingService {
    public static class OrdersProcessor {
        @Bean
        public BiFunction<KStream<String, Order>, KTable<String, Product>, KStream<String, Order>> ordersprocess() {

            return (orderStream, productTable) ->{
                orderStream.foreach(new ForeachAction() {
                    @Override
                    public void apply(Object key, Object value) {
                        System.out.print("THe key value of the orders stream is .. ");
                        System.out.println(key + ": " + value);
                    }
                });
                productTable.toStream().foreach(new ForeachAction() {
                    @Override
                    public void apply(Object key, Object value) {
                        System.out.print("THe key value of othe product is .. ");
                        System.out.println(key + ": " + value);
                    }
                });;
                return orderStream;

            };
        }
    }
}
