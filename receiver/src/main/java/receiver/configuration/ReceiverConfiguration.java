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
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(DirectExchange exchange, Queue autoDeleteQueue1) {
        return BindingBuilder
                .bind(autoDeleteQueue1)
                .to(exchange)
                .with(Product.Category.SALE.name());
    }

    @Bean
    public Binding binding2(DirectExchange exchange, Queue autoDeleteQueue2) {
        return BindingBuilder
                .bind(autoDeleteQueue2)
                .to(exchange)
                .with(Product.Category.NORMAL.name());
    }
}
