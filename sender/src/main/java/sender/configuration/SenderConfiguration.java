package sender.configuration;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@EnableScheduling
@Configuration
@AllArgsConstructor
public class SenderConfiguration {

    private final SenderProperties senderProperties;

    @Bean
    @Qualifier("productExchange")
    public DirectExchange directExchange(){
        return new DirectExchange(senderProperties.getExchangeName());
    }

    @Bean
    public InitializingBean prepareQueues(AmqpAdmin amqpAdmin) {
        return () -> senderProperties.getQueuesNames().stream()
                .map(Queue::new)
                .forEach(amqpAdmin::declareQueue);
    }
}
