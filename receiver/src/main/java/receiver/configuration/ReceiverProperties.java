package receiver.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbit.config")
@Data
public class ReceiverProperties {
    private String exchangeName;
    private String queueSale;
    private String queueNormal;

}
