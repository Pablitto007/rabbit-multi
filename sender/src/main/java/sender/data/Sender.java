package sender.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import library.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
@AllArgsConstructor
public class Sender {

    private RabbitTemplate template;
    @Qualifier("productExchange")
    private DirectExchange directExchange;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

        Gson gson = new GsonBuilder().create();
        Product product = SampleDataProvider.createProductsList().get(new Random().nextInt(2));
        String jsonProduct = gson.toJson(product);
        template.convertAndSend(directExchange.getName(), product.getCategory().name(),  jsonProduct);
        log.info("[x] Sent {} " , jsonProduct);

    }
}
