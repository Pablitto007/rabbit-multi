package receiver.configuration;

import library.Product;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class ReceiverConfiguration {

    @Autowired
    private ReceiverProperties receiverProperties;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(receiverProperties.getExchangeName());
    }

    @Bean
    public Queue normalQueue() {
        return new Queue(receiverProperties.getQueueNormal());
    }

    @Bean
    public Queue saleQueue() {
        return new Queue(receiverProperties.getQueueSale());
    }

    @Bean
    public Binding binding1(DirectExchange exchange, Queue saleQueue) {
        return BindingBuilder
                .bind(saleQueue)
                .to(exchange)
                .with(Product.Category.SALE.name());
    }

    @Bean
    public Binding binding2(DirectExchange exchange, Queue normalQueue) {
        return BindingBuilder
                .bind(normalQueue)
                .to(exchange)
                .with(Product.Category.NORMAL.name());
    }
}
