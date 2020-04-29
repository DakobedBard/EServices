//package kafka.streams.interactive.query.services;
//
//
//import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
//import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.common.utils.Bytes;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.kstream.*;
//import org.apache.kafka.streams.state.KeyValueStore;
//import org.mddarr.products.Product;
//import org.mddarr.products.PurchaseCount;
//import org.mddarr.products.PurchaseEvent;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.Input;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.Map;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//
//@Service
//
//public class PurchaseService {
//
//    private static final String PRODUCT_PURCHASE_COUNT_STORE = "product-purchase-count";
//
//    @Bean
//    public BiFunction<KStream<String, PurchaseEvent>, KTable<String, Product>, KStream<String, Long>> purchasesprocess() {
//        return (purchaseStream, productTable) -> {
//            // create and configure the SpecificAvroSerdes required in this example
//            final Map<String, String> serdeConfig = Collections.singletonMap(
//                    AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
//
//            final SpecificAvroSerde<PurchaseEvent> purchaseEventSerde = new SpecificAvroSerde<>();
//            purchaseEventSerde.configure(serdeConfig, false);
//
//            final SpecificAvroSerde<Product> keyProductSerde = new SpecificAvroSerde<>();
//            keyProductSerde.configure(serdeConfig, true);
//
//            final SpecificAvroSerde<Product> valueProductSerde = new SpecificAvroSerde<>();
//            valueProductSerde.configure(serdeConfig, false);
//
//            final SpecificAvroSerde<PurchaseCount> productPurchaseCountSerde = new SpecificAvroSerde<>();
//            productPurchaseCountSerde.configure(serdeConfig, false);
//
//            final KStream<String, PurchaseEvent> purchasesByProductId =
//                    purchaseStream.map((key, value) -> KeyValue.pair(value.getProductId(), value));
//
//            // join the purchases with product as we will use it later for charting
//            final KStream<String, Product> productPurchases = purchasesByProductId
//                    .leftJoin(productTable, (value1, product) -> product,
//                            Joined.with(Serdes.String(), purchaseEventSerde, valueProductSerde));
//            // create a state store to track product purchase counts
//            final KStream<String, Long> productPurchaseCounts = purchasesByProductId.groupBy((k,v) -> k).count().toStream();
//
//            return productPurchaseCounts;
//        };
//
//    }
//
//
//
////
////    @EnableBinding(PurchaseInterface.class)
////    public static class PurchaseCountAnalysis {
////        @StreamListener
////        public void processPurchases(
////                @Input(PurchaseInterface.) KStream<String, PurchaseEvent> ordersStream, @Input("product-feed") KTable<String, Product> customers){
////
////
////        }
////    }
////
////
////    interface PurchaseInterface {
////
////        @Input("purchase-events")
////        KStream<?, PurchaseEvent> input1();
////        @Input("product-feed")
////        KTable<String, Product> input2();
////
////    }
//
//
//}
//////        @StreamListener
//////        @SuppressWarnings("unchecked")
//////        public void purchase_process(@Input("purchase-events") KStream<String, PurchaseEvent> purchaseEventKStream, @Input("product-feed") KTable<String, Product> productKTable){
//////
//////        }
////    }
////
////
////    interface PurchaseCountInterface {
////
////        @Input("purchase-events")
////        KStream<?, ?> purchase_input();
////        @Input("product-feed")
////        KTable<?, ?> product_input();
////
////    }
////}
