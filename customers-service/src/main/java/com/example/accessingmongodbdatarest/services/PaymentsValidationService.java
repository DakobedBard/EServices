package com.example.accessingmongodbdatarest.services;

import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.mddarr.orders.event.dto.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PaymentsValidationService {
    public static class PaymentsProcessor {

        @Bean
        public Function<KStream<String, Order>,  KStream<String, Order>> paymentsprocess() {

            return (orderStream) ->{
                orderStream.foreach(new ForeachAction() {
                    @Override
                    public void apply(Object key, Object value) {
                        System.out.print("The validated order value is  .. ");
                        System.out.println(key + ": " + value);
                    }
                });
                return orderStream;
            };
        }
    }
}
