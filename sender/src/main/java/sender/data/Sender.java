package sender.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import library.Product;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Random;

@Component
public class Sender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier("productExchange")
    private DirectExchange directExchange;

    @Scheduled(fixedDelay = 10000, initialDelay = 500)
    public void send() {

        Gson gson = new GsonBuilder().create();
        Product product = SampleDataProvider.createProductsList().get(new Random().nextInt(2));
        //String jsonProduct = gson.toJson(product);

        //System.out.println(" [x] Sent '" + jsonProduct + "'");

        Message message = MessageBuilder.withBody(readFile()).setHeader("ContentType", "image/jpeg").build();
        template.convertAndSend(directExchange.getName(), product.getCategory().name(), message);

        System.out.println(" [x] Sent '" + message.getBody().length + " bytes");

    }

    private byte[] readFile() {
        try {
            ClassPathResource pathResource = new ClassPathResource("img/ferrari.jpg");
            return FileCopyUtils.copyToByteArray(pathResource.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
