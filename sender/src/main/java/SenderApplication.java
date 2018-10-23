import receiver.configuration.SenderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@SpringBootApplication(scanBasePackages={"receiver.configuration", "sender"})
@EnableScheduling
public class SenderApplication implements CommandLineRunner{

    private static Logger logger = LoggerFactory.getLogger(SenderApplication.class);

    @Autowired
    SenderProperties senderProperties;

    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Optional<String> exName = Optional.ofNullable(senderProperties.getExchangeName());
        String message = exName.orElse("xxx exchange name is null xxx");
        logger.warn(message);
    }
}
