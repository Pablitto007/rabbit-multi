package sender.configuration;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class SenderConfiguration {

    @Autowired
    private SenderProperties senderProperties;

    @Bean
    @Qualifier("productExchange")
    public DirectExchange directExchange(){
        return new DirectExchange(senderProperties.getExchangeName());
    }
}
